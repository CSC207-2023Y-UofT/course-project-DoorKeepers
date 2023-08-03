package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The CategoryUCI adds/creates a new category, edits an existing category, and updates MonthlyStorage.categoryData.
 * Contains a helper method isValidDouble() and findCategory().
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
    public static double toDouble(Object value) throws NumberFormatException, NullPointerException{
            return Double.parseDouble(String.valueOf(value));
    }
    /**
     * Overrides method in CategoryIB.
     * Attempts to add a category with information from CategoryID and returns a CategoryOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below:
     *  1. NumberFormatException: User tries to add a new budget value that can not be converted to a double.
     *  2. Category budget less than 0: User tries to add a new budget value that is a negative number.
     *  3. EntityException: User tries to add an invalid Category name but failed. (See entities/EntityException.java)
     *
     * @param categoryIDAdd CategoryID required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOD String message indicating success/fail add attempt.
     * @throws EntityException thrown when categoryIDAdd has invalid category information.
     */
    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryIDAdd) throws EntityException {
        MonthlyStorage month = categoryIDAdd.getSession().getMonthlyData(categoryIDAdd.getMonthID());
        try{
            double valueDouble = toDouble(categoryIDAdd.getValue());
            Category newCategory = new Category(categoryIDAdd.getName(), valueDouble);

            if (newCategory.getBudget() < 0) {
                // 2. Category budget less than 0 fail
                return categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }
            month.addCategory(newCategory);

        } catch(NumberFormatException|NullPointerException e){
            //1. NumberFormatException/NullPointerException fail
            return categoryOB.fail("Category budget is needs to be a number. Please try again!");

        } catch (EntityException e) {
            //3. EntityException fail
            return categoryOB.fail("There is already a category with this new name in this month.");

        }
        return categoryOB.success_add();

    }
    /**
     * Overrides method in CategoryIB.
     * Attempts to edit a category with information from CategoryID and returns a CategoryOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below:
     *  1. Repeated Name: User tries to edit category name to another name that exists in the month.
     *  2. NoSuchElementException: User tries to edit a category that does not exist.
     *  3. NumberFormatException: User tries to edit a budget value with input that can not be converted to a double.
     *  4. Category budget less than 0: User tries to edit a budget value with input that is a negative number.
     *
     * @param categoryIDEdit CategoryID required for editing a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOD String message indicating success/fail add attempt.
     */
    @Override
    public CategoryOD editCategoryInMonth(CategoryID categoryIDEdit) throws EntityException {
        MonthlyStorage month = categoryIDEdit.getSession().getMonthlyData(categoryIDEdit.getMonthID());
        ArrayList<Category> categoryList = month.getCategoryData();


        try {
            double valueDouble = toDouble(categoryIDEdit.getValue());
            if (valueDouble < 0) {
                //4. Category budget less than 0 fail
                return categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }

            Category existingCategory = findCategory(categoryList, categoryIDEdit.getOldCategory());

            for (Category category1 : categoryList) {
                if (Objects.equals(categoryIDEdit.getName(), categoryIDEdit.getOldCategory())){
                    categoryList.remove(findCategory(categoryList,categoryIDEdit.getName()));
                }
                if (category1.getName().equals(categoryIDEdit.getName())) {
                    //1. Repeated name fail
                    return categoryOB.fail("There is already a category with this new name in this month.");
                }
            }

            existingCategory.setName(categoryIDEdit.getName());
            categoryIDEdit.setValue(valueDouble);
            existingCategory.setBudget(valueDouble);

        } catch (NoSuchElementException e) {
            //2. NoSuchElementException fail
            return categoryOB.fail("There is no such category in the current month. Please add a new category or select existing category!");
        } catch(NumberFormatException e){
            //3. NumberFormatException fail
            return categoryOB.fail("Category budget needs to be a number. Please try again!");

        }
        return categoryOB.success_edit();
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