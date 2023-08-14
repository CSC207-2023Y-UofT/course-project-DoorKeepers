package use_cases.add_edit_category_use_case;

import entities.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The CategoryUCI adds/creates new category, edits an existing category, and updates MonthlyStorage.categoryData.
 * Implements CategoryIB.
 * Creates CategoryOD objects related to specific use case fail/success conditions.
 */
public class CategoryUCI implements CategoryIB {
    private final CategoryOB categoryOB;
    private final CategoryFactory categoryFactory;
    private MonthlyStorage month;


    /**
     * Constructs CategoryUCI. A CategoryFactory is constructed.
     * @param categoryP presenter that is related to the use case.
     */
    public CategoryUCI(CategoryOB categoryP) {
        this.categoryOB = categoryP;
        this.categoryFactory = new CategoryFactory();
    }

    /**
     * Helper method returns a double to check if the input Object is a valid double.
     * @param value a user input
     * @return double converted from value
     */
    private double toDouble(Object value) throws NumberFormatException, NullPointerException{
            return Double.parseDouble(String.valueOf(value));
    }

    /**
     * Overrides method in CategoryIB.
     * Attempts to add a category with information from CategoryID and returns a CategoryOD
     * indicating whether fail/success after execution.
     * CategoryFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below. (Explained in comments.)
     * NOTE: There are actually two different EntityException thrown.
     * One is from the SessionStorage Object when checking if monthID is in session.
     *  (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     *  it will be caught at views/add_edit_category_views/AddCategoryV.java).
     * The second one is from the creation of a Category Object implementing addCategory() from MonthlyStorage,
     * and is caught in the current implementation!
     *
     * @param categoryIDAdd CategoryID required for adding a new category to designated monthID MonthlyStorage Object.
     * @return CategoryOD String message indicating success/fail add attempt.
     */
    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryIDAdd){
        try{
            this.month = categoryIDAdd.getSession().getMonthlyData(categoryIDAdd.getMonthID());
            double valueDouble = toDouble(categoryIDAdd.getValue());

            if (valueDouble < 0) {
                // Category budget less than 0: User tries to add a new budget value that is a negative number.
                CategoryOD categoryODFailAdd = new CategoryOD("Category budget can't be less than $0. " +
                        "Please try again!");
                return categoryOB.fail(categoryODFailAdd);}
            month.addCategory((Category) categoryFactory.createMonthObject(setCategoryCreatorInfo(categoryIDAdd)));
            CategoryOD categoryODSuccessAdd = new CategoryOD("You have added a new category!");
            return categoryOB.success(categoryODSuccessAdd);

        } catch(NumberFormatException|NullPointerException e){
            //NumberFormatException|NullPointerException: User tries to add a new budget value that can not be converted to a double.
            CategoryOD categoryODFailAdd = new CategoryOD("Category budget needs to be a number. Please try again!");
            return categoryOB.fail(categoryODFailAdd);
        } catch (EntityException e) {
            //EntityException: User tries to add an invalid Category name but failed. (See entities/EntityException.java)
            CategoryOD categoryODFailAdd = new CategoryOD("There is already a category with this new name " +
                    "in this month.");
            return categoryOB.fail(categoryODFailAdd);
        }
    }
    /**
     * Overrides method in CategoryIB.
     * Attempts to edit a category with information from CategoryID and returns a CategoryOD
     * indicating whether fail/success after execution.
     * CategoryFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below. (Explained in comments.)
     * @param categoryIDEdit CategoryID required for editing a new category to designated monthID MonthlyStorage Object.
     * @return CategoryOD String message indicating success/fail add attempt.
     * @throws EntityException (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     *                          it will be caught at views/add_edit_category_views/EditCategoryV.java).
     */
    @Override
    public CategoryOD editCategoryInMonth(CategoryID categoryIDEdit) throws EntityException{

        try {
            this.month = categoryIDEdit.getSession().getMonthlyData(categoryIDEdit.getMonthID());
            ArrayList<Category> monthCategoryList = month.getCategoryData();
            double valueDouble = toDouble(categoryIDEdit.getValue());

            if (valueDouble < 0) {
                //Category budget less than 0: User tries to edit a budget value with input that is a negative number.
                CategoryOD categoryODFailEdit = new CategoryOD("Category budget can't be less than $0. " +
                        "Please try again!");
                return categoryOB.fail(categoryODFailEdit);}

            if(!Objects.equals(categoryIDEdit.getName(), categoryIDEdit.getOldCategory())){
                for (Category category1 : monthCategoryList) {
                if (category1.getName().equals(categoryIDEdit.getName())) {
                    //Repeated name: User tries to edit category name to another name that exists in the month.
                    CategoryOD categoryODFailEdit = new CategoryOD("There is already a category with this new name " +
                            "in this month.");
                    return categoryOB.fail(categoryODFailEdit);}}}

            categoryFactory.editMonthObject(setCategoryEditorInfo(categoryIDEdit));
            CategoryOD categoryODSuccessEdit = new CategoryOD("You have edited a category!");
            return categoryOB.success(categoryODSuccessEdit);

        } catch (NoSuchElementException e) {
            //NoSuchElementException: User tries to edit a category that does not exist.
            CategoryOD categoryODFailEdit = new CategoryOD("There is no such category in the current month. " +
                    "Please add a new category or select existing category!");
            return categoryOB.fail(categoryODFailEdit);
        } catch(NumberFormatException|NullPointerException e){
            //NumberFormatException|NullPointerException: User tries to edit a budget value
            // with input that can not be converted to a double.
            CategoryOD categoryODFailEdit = new CategoryOD("Category budget needs to be a number. Please try again!");
            return categoryOB.fail(categoryODFailEdit);
        }
    }

    /**
     * Helper method returns a Category from a given category list of a MonthlyStorage Object.
     * @param monthCategoryData An ArrayList of categories.
     * @param name Category name.
     * @return Category with given String name.
     * @throws NoSuchElementException thrown when Category with String name doesn't exist in monthCategoryData.
     */
    public Category findCategory(ArrayList<Category> monthCategoryData, String name)throws NoSuchElementException{
        for (Category c : monthCategoryData){
            if (Objects.equals(c.getName(), name)){
                return c;}}
        throw new NoSuchElementException();}
    /**
     * Sets information needed to create a CategoryCreatorInputData to call createMonthObject method in CategoryFactory
     * @return CategoryCreatorInputData MonthObjectFactoryInputData Object specifically used in CategoryFactory
     * for the createMonthObject method.
     */
    private CategoryCreatorInputData setCategoryCreatorInfo(CategoryID categoryIDAdd){
        CategoryCreatorInputData categoryCreatorInputData = new CategoryCreatorInputData();
        categoryCreatorInputData.setName(categoryIDAdd.getName());
        categoryCreatorInputData.setBudget(toDouble(categoryIDAdd.getValue()));
        return categoryCreatorInputData;}
    /**
     * Sets information needed to edit a CategoryEditorInputData to call editMonthObject method in CategoryFactory
     * @return CategoryEditorInputData MonthObjectFactoryInputData Object specifically used in CategoryFactory
     * for the editMonthObject method.
     */
    private CategoryEditorInputData setCategoryEditorInfo(CategoryID categoryIDEdit){
        CategoryEditorInputData categoryEditorInputData = new CategoryEditorInputData();
        categoryEditorInputData.setName(categoryIDEdit.getName());
        categoryEditorInputData.setBudget(toDouble(categoryIDEdit.getValue()));
        categoryEditorInputData.setCategory(findCategory(month.getCategoryData(), categoryIDEdit.getOldCategory()));
        return categoryEditorInputData;}
}