package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Category_Use_Case_Interactor 1. adds/creates a new category and 2. edits an existing category.
 * Updates MonthlyStorage:
 * 1. adding a new Category to MonthlyStorage.categoryData with MonthlyStorage.addCategory()
 * 2.
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

    public static double toDouble(Object value){
        return Double.parseDouble(String.valueOf(value));
    }
    /**
     * Overrides method in Category_IB.
     * Attempts to add a category with information from CategoryID and returns a CategoryOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below:
     *  1. NumberFormatException: User tries to add a new budget value that can not be converted to a double.
     *  2. Category budget less than 0: User tries to add a new budget value that is a negative number.
     *  3. EntityException: User tries to add an invalid Category name but failed. (See entities/EntityException.java)
     *
     * @param categoryID_add Category_Input_Data required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return Category_Output_Data category.
     * @throws EntityException thrown when categoryID_add has invalid category information.
     */
    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryID_add) throws EntityException {
        MonthlyStorage month = categoryID_add.getSession().getMonthlyData(categoryID_add.getMonthID());
        try{
            double value_double = toDouble(categoryID_add.getValue());
            Category c = new Category(categoryID_add.getName(), value_double);

            if (c.getBudget() < 0) {
                // 2. Category budget less than 0 fail
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }

            month.addCategory(c);

        } catch(NumberFormatException e){
            //1. NumberFormatException fail
            categoryOB.fail("Category budget is needs to be a number. Please try again!");
        } catch (EntityException e) {
            //3. EntityException fail
            categoryOB.fail("There is already a category with this new name in this month.");

        }
        return categoryOB.success_add();
    }
    /**
     * Overrides method in Category_IB.
     * Attempts to edit a category with information from CategoryID and returns a CategoryOD indicating whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below:
     *  1. Repeated Name: User tries to edit category name to another name that exists in the month.
     *  2. NoSuchElementException: User tries to edit a category that does not exist.
     *  3. NumberFormatException: User tries to edit a budget value with input that can not be converted to a double.
     *  4. Category budget less than 0: User tries to edit a budget value with input that is a negative number.
     *
     * @param categoryID_edit Category_Input_Data required for editing a new category to the designated monthID MonthlyStorage Object.
     * @return Category_Output_Data category.
     */
    @Override
    public CategoryOD editCategoryInMonth(CategoryID categoryID_edit) throws EntityException {
        MonthlyStorage month = categoryID_edit.getSession().getMonthlyData(categoryID_edit.getMonthID());
        ArrayList<Category> category_list = month.getCategoryData();
        try {
            for (Category e : category_list) {
                if (e.getName().equals(categoryID_edit.getName())) {
                    //1. Repeated name fail
                    categoryOB.fail("There is already a category with this new name in this month.");
                }
            }

            Category c = findCategory(category_list, categoryID_edit.getOld_category().getName());

            double value_double = toDouble(categoryID_edit.getValue());
            if (value_double < 0) {
                //4. Category budget less than 0 fail
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }

            c.setName(categoryID_edit.getName());

            categoryID_edit.setValue(value_double);
            c.setBudget(value_double);

        } catch (NoSuchElementException e) {
            //2. NoSuchElementException fail
            categoryOB.fail("There is no such category in the current month. Please add a new category or select existing category!");
        } catch(NumberFormatException e){
            //3. NumberFormatException fail
            categoryOB.fail("Category budget needs to be a number. Please try again!");

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
    public Category findCategory(ArrayList<Category> monthCategoryData, String name){
        for (Category c : monthCategoryData){
            if (Objects.equals(c.getName(), name)){
                return c;
            }
        }
        throw new NoSuchElementException();
    }
}