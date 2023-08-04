package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The CategoryUCI adds/creates new category, edits an existing category, and updates MonthlyStorage.categoryData.
 * Implements CategoryIB.
 * Creates CategoryOD objects related to specific use case fail/success conditions.
 */

public class CategoryUCI implements CategoryIB {
    final CategoryOB categoryOB;

    /**
     * Constructs CategoryUCI.
     * @param categoryP presenter that is related to the use case.
     */
    public CategoryUCI(CategoryOB categoryP) {
        this.categoryOB = categoryP;
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
     * Attempts to add a category with information from CategoryID and returns a CategoryOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition below. (Explained in comments.)
     * NOTE: There are actually two different EntityException thrown.
     * One is from the SessionStorage Object when checking if monthID is in session.
     *  (Although we know MonthlyStorage with monthID is always in the SessionStorage, it will be caught at views/add_edit_category_views/AddCategoryV.java).
     * The second one is from the creation of a Category Object implementing addCategory() from MonthlyStorage, and is caught in the current implementation!
     *
     * @param categoryIDAdd CategoryID required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOD String message indicating success/fail add attempt.
     * @throws EntityException thrown when categoryIDAdd has invalid category information.
     */
    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryIDAdd)throws EntityException{
        MonthlyStorage month = categoryIDAdd.getSession().getMonthlyData(categoryIDAdd.getMonthID());
        try{
            double valueDouble = toDouble(categoryIDAdd.getValue());
            Category newCategory = new Category(categoryIDAdd.getName(), valueDouble);

            if (newCategory.getBudget() < 0) {
                // Category budget less than 0: User tries to add a new budget value that is a negative number.
                CategoryOD categoryODFailAdd = new CategoryOD("Category budget can't be less than $0. Please try again!");
                return categoryOB.fail(categoryODFailAdd);
            }
            month.addCategory(newCategory);

            CategoryOD categoryODSuccessAdd = new CategoryOD("You have added a new category!");
            return categoryOB.success(categoryODSuccessAdd);

        } catch(NumberFormatException|NullPointerException e){
            //NumberFormatException: User tries to add a new budget value that can not be converted to a double.
            CategoryOD categoryODFailAdd = new CategoryOD("Category budget is needs to be a number. Please try again!");
            return categoryOB.fail(categoryODFailAdd);

        } catch (EntityException e) {
            //EntityException: User tries to add an invalid Category name but failed. (See entities/EntityException.java)
            CategoryOD categoryODFailAdd = new CategoryOD("There is already a category with this new name in this month.");
            return categoryOB.fail(categoryODFailAdd);
        }
    }
    /**
     * Overrides method in CategoryIB.
     * Attempts to edit a category with information from CategoryID and returns a CategoryOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition below. (Explained in comments.)
     * @param categoryIDEdit CategoryID required for editing a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOD String message indicating success/fail add attempt.
     * @throws EntityException (Although we know MonthlyStorage with monthID is always in the SessionStorage,
     *                          it will be caught at views/add_edit_category_views/EditCategoryV.java).
     */
    @Override
    public CategoryOD editCategoryInMonth(CategoryID categoryIDEdit) throws EntityException{
        MonthlyStorage month = categoryIDEdit.getSession().getMonthlyData(categoryIDEdit.getMonthID());
        try {
            ArrayList<Category> categoryList = month.getCategoryData();
            double valueDouble = toDouble(categoryIDEdit.getValue());
            if (valueDouble < 0) {
                //Category budget less than 0: User tries to edit a budget value with input that is a negative number.
                CategoryOD categoryODFailEdit = new CategoryOD("Category budget can't be less than $0. Please try again!");
                return categoryOB.fail(categoryODFailEdit);
            }

            Category existingCategory = findCategory(categoryList, categoryIDEdit.getOldCategory());

            for (Category category1 : categoryList) {
                if (Objects.equals(categoryIDEdit.getName(), categoryIDEdit.getOldCategory())){
                    categoryList.remove(findCategory(categoryList,categoryIDEdit.getName()));
                }
                if (category1.getName().equals(categoryIDEdit.getName())) {
                    //Repeated name: User tries to edit category name to another name that exists in the month.
                    CategoryOD categoryODFailEdit = new CategoryOD("There is already a category with this new name in this month.");
                    return categoryOB.fail(categoryODFailEdit);
                }
            }

            existingCategory.setName(categoryIDEdit.getName());
            categoryIDEdit.setValue(valueDouble);
            existingCategory.setBudget(valueDouble);

            CategoryOD categoryODSuccessEdit = new CategoryOD("You have edited a category!");
            return categoryOB.success(categoryODSuccessEdit);

        } catch (NoSuchElementException e) {
            //NoSuchElementException: User tries to edit a category that does not exist.
            CategoryOD categoryODFailEdit = new CategoryOD("There is no such category in the current month. Please add a new category or select existing category!");
            return categoryOB.fail(categoryODFailEdit);
        } catch(NumberFormatException e){
            //NumberFormatException: User tries to edit a budget value with input that can not be converted to a double.
            CategoryOD categoryODFailEdit = new CategoryOD("Category budget needs to be a number. Please try again!");
            return categoryOB.fail(categoryODFailEdit);
        }
    }

    /**
     * Helper method returns a Category from a given category list of a MonthlyStorage Object.
     * @param monthCategoryData An ArrayList of categories.
     * @param name Category name.
     * @return Category with given String name.
     */
    @Override
    public Category findCategory(ArrayList<Category> monthCategoryData, String name)throws NoSuchElementException{
        for (Category c : monthCategoryData){
            if (Objects.equals(c.getName(), name)){
                return c;
            }
        }
        throw new NoSuchElementException();
    }
}