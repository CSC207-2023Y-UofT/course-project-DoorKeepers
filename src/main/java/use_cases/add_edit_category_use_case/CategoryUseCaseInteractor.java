package use_cases.add_edit_category_use_case;

import entities.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The CategoryUseCaseInteractor adds/creates new category, edits an existing category, and updates MonthlyStorage.categoryData.
 * Implements CategoryInputBoundary.
 * Creates CategoryOutputData objects related to specific use case fail/success conditions.
 */
public class CategoryUseCaseInteractor implements CategoryInputBoundary {
    private final CategoryOutputBoundary categoryOutputBoundary;
    private final MonthObjectFactory categoryFactory;
    private MonthlyStorage month;


    /**
     * Constructs CategoryUseCaseInteractor. A CategoryFactory is constructed.
     *
     * @param categoryP presenter that is related to the use case.
     */
    public CategoryUseCaseInteractor(CategoryOutputBoundary categoryP) {
        this.categoryOutputBoundary = categoryP;
        this.categoryFactory = new CategoryFactory();
    }

    /**
     * Helper method returns a double to check if the input Object is a valid double.
     *
     * @param value a user input
     * @return double converted from value
     */
    private double toDouble(Object value) throws NumberFormatException, NullPointerException {
        return Double.parseDouble(String.valueOf(value));
    }

    /**
     * Overrides method in CategoryInputBoundary.
     * Attempts to add a category with information from CategoryInputData and returns a CategoryOutputData
     * indicating whether fail/success after execution.
     * CategoryFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below. (Explained in comments.)
     * NOTE: There are actually two different EntityException thrown.
     * One is from the SessionStorage Object when checking if monthID is in session.
     * (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     * it will be caught at views/add_edit_category_views/AddCategoryView.java).
     * The second one is from the creation of a Category Object implementing addCategory() from MonthlyStorage,
     * and is caught in the current implementation!
     *
     * @param categoryInputDataAdd CategoryInputData required for adding a new category to designated monthID MonthlyStorage Object.
     * @return CategoryOutputData String message indicating success/fail add attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    @Override
    public CategoryOutputData addCategoryInMonth(CategoryInputData categoryInputDataAdd) throws EntityException {
        this.month = categoryInputDataAdd.getSession().getMonthlyData(categoryInputDataAdd.getMonthID());
        try {
            double valueDouble = toDouble(categoryInputDataAdd.getValue());

            if (valueDouble < 0) {
                // Category budget less than 0: User tries to add a new budget value that is a negative number.
                CategoryOutputData categoryOutputDataFailAdd = new CategoryOutputData("Category budget can't be less than $0. " +
                        "Please try again!");
                return categoryOutputBoundary.fail(categoryOutputDataFailAdd);
            }
            Category newCategory = (Category) categoryFactory.createMonthObject(setCategoryCreatorFactoryInputData(categoryInputDataAdd));
            month.addCategory(newCategory);
            CategoryOutputData categoryOutputDataSuccessAdd = new CategoryOutputData("You have added a new category!");
            return categoryOutputBoundary.success(categoryOutputDataSuccessAdd);

        } catch (NumberFormatException | NullPointerException e) {
            //NumberFormatException|NullPointerException: User tries to add a new budget value that can not be converted to a double.
            CategoryOutputData categoryOutputDataFailAdd = new CategoryOutputData("Category budget needs to be a number. Please try again!");
            return categoryOutputBoundary.fail(categoryOutputDataFailAdd);
        } catch (EntityException e) {
            //EntityException: User tries to add an invalid Category name but failed. (See entities/EntityException.java)
            CategoryOutputData categoryOutputDataFailAdd = new CategoryOutputData("There is already a category with this new name " +
                    "in this month.");
            return categoryOutputBoundary.fail(categoryOutputDataFailAdd);
        }
    }

    /**
     * Overrides method in CategoryInputBoundary.
     * Attempts to edit a category with information from CategoryInputData and returns a CategoryOutputData
     * indicating whether fail/success after execution.
     * CategoryFactory methods are implemented to better adhere to Liskov's Substitution Principle.
     * Provides detailed fail messages according to each condition below. (Explained in comments.)
     *
     * @param categoryInputDataEdit CategoryInputData required for editing a new category to designated monthID MonthlyStorage Object.
     * @return CategoryOutputData String message indicating success/fail add attempt.
     * @throws EntityException (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     *                         it will be caught at views/add_edit_category_views/EditCategoryView.java).
     */
    @Override
    public CategoryOutputData editCategoryInMonth(CategoryInputData categoryInputDataEdit) throws EntityException {

        try {
            this.month = categoryInputDataEdit.getSession().getMonthlyData(categoryInputDataEdit.getMonthID());
            ArrayList<Category> monthCategoryList = month.getCategoryData();
            double valueDouble = toDouble(categoryInputDataEdit.getValue());

            if (valueDouble < 0) {
                //Category budget less than 0: User tries to edit a budget value with input that is a negative number.
                CategoryOutputData categoryOutputDataFailEdit = new CategoryOutputData("Category budget can't be less than $0. " +
                        "Please try again!");
                return categoryOutputBoundary.fail(categoryOutputDataFailEdit);
            }

            if (!Objects.equals(categoryInputDataEdit.getName(), categoryInputDataEdit.getOldCategory())) {
                for (Category category1 : monthCategoryList) {
                    if (category1.getName().equals(categoryInputDataEdit.getName())) {
                        //Repeated name: User tries to edit category name to another name that exists in the month.
                        CategoryOutputData categoryOutputDataFailEdit = new CategoryOutputData("There is already a category with this new name " +
                                "in this month.");
                        return categoryOutputBoundary.fail(categoryOutputDataFailEdit);
                    }
                }
            }

            categoryFactory.editMonthObject(setCategoryEditorFactoryInputData(categoryInputDataEdit));
            CategoryOutputData categoryOutputDataSuccessEdit = new CategoryOutputData("You have edited a category!");
            return categoryOutputBoundary.success(categoryOutputDataSuccessEdit);

        } catch (NoSuchElementException e) {
            //NoSuchElementException: User tries to edit a category that does not exist.
            CategoryOutputData categoryOutputDataFailEdit = new CategoryOutputData("There is no such category in the current month. " +
                    "Please add a new category or select existing category!");
            return categoryOutputBoundary.fail(categoryOutputDataFailEdit);
        } catch (NumberFormatException | NullPointerException e) {
            //NumberFormatException|NullPointerException: User tries to edit a budget value
            // with input that can not be converted to a double.
            CategoryOutputData categoryOutputDataFailEdit = new CategoryOutputData("Category budget needs to be a number. Please try again!");
            return categoryOutputBoundary.fail(categoryOutputDataFailEdit);
        }
    }

    /**
     * Helper method returns a Category from a given category list of a MonthlyStorage Object.
     *
     * @param monthCategoryData An ArrayList of categories.
     * @param name              Category name.
     * @return Category with given String name.
     * @throws NoSuchElementException thrown when Category with String name doesn't exist in monthCategoryData.
     */
    public Category findCategory(ArrayList<Category> monthCategoryData, String name) throws NoSuchElementException {
        for (Category c : monthCategoryData) {
            if (Objects.equals(c.getName(), name)) {
                return c;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * Sets information needed to create a CategoryCreatorInputData to call createMonthObject method in CategoryFactory
     *
     * @return CategoryCreatorInputData MonthObjectFactoryInputData Object specifically used in CategoryFactory
     * for the createMonthObject method.
     */
    private MonthObjectFactoryInputData setCategoryCreatorFactoryInputData(CategoryInputData categoryInputDataAdd) {
        CategoryCreatorInputData categoryCreatorInputData = new CategoryCreatorInputData();
        categoryCreatorInputData.setName(categoryInputDataAdd.getName());
        categoryCreatorInputData.setBudget(toDouble(categoryInputDataAdd.getValue()));
        return categoryCreatorInputData;
    }

    /**
     * Sets information needed to edit a CategoryEditorInputData to call editMonthObject method in CategoryFactory
     *
     * @return CategoryEditorInputData MonthObjectFactoryInputData Object specifically used in CategoryFactory
     * for the editMonthObject method.
     */
    private MonthObjectFactoryInputData setCategoryEditorFactoryInputData(CategoryInputData categoryInputDataEdit) {
        CategoryEditorInputData categoryEditorInputData = new CategoryEditorInputData();
        categoryEditorInputData.setName(categoryInputDataEdit.getName());
        categoryEditorInputData.setBudget(toDouble(categoryInputDataEdit.getValue()));
        categoryEditorInputData.setCategory(findCategory(month.getCategoryData(), categoryInputDataEdit.getOldCategory()));
        return categoryEditorInputData;
    }
}