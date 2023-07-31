package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;
import views.add_edit_category_views.CategoryP;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Category_Use_Case_Interactor 1. adds/creates a new category and 2. edits an existing category. (Updates MonthlyStorage?)
 * Contains a helper method isValidDouble() and findCategory().
 */

public class CategoryUCI implements CategoryIB {
    final CategoryOB categoryOB;
    private CategoryOD categoryOD;

    /**
     * Constructs CategoryUCI.
     * @param categoryP presenter that is related to the use case.
     */

    public CategoryUCI(CategoryP categoryP) {
        this.categoryOB = categoryP;
        this.categoryOD = null;
    }

    /**
     * Helper method returns a boolean to check if the input Object is a valid double.
     * @param value a user input
     * @return boolean true if value is a valid double, vice versa
     */

    public static boolean isValidDouble(Object value){
        try{
            Double.parseDouble(String.valueOf(value));
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    /**
     * Overrides method in Category_IB.
     * Attempts to add a category with information from CategoryID and returns whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below
     *  1. NumberFormatException: User tries to add a new budget value that can not be converted to a double.
     *  2. Category budget less than 0: User tries to add a new budget value that is a negative number.
     *  3. EntityException: User tries to add an invalid Category but failed. (See entities/EntityException.java)
     *
     * @param categoryID_add Category_Input_Data required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return Category_Output_Data category.
     * @throws EntityException thrown when categoryID_add has invalid category information.
     */
    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryID_add) throws EntityException {
        MonthlyStorage month = categoryID_add.getSession().getMonthlyData(categoryID_add.getMonthID());

        try{
            if(isValidDouble(categoryID_add.getValue())){
                double value_double = Double.parseDouble(String.valueOf(categoryID_add.getValue()));
                categoryID_add.setValue(value_double);
            }
            if ((double)categoryID_add.getValue() < 0) {
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }
            Category category = new Category(categoryID_add.getName(), (double) categoryID_add.getValue());
            month.addCategory(category);
            this.categoryOD = new CategoryOD(category);

        } catch (NumberFormatException e) {
            // 1. Invalid category budget input from user.
            categoryOB.fail("Category budget needs to be a number. Please try again!");
        } catch (EntityException e) {
            categoryOB.fail("Fail to add new category. Please try again!");
        }
        return categoryOB.success_add(categoryOD);
    }
    /**
     * Overrides method in Category_IB.
     * Attempts to edit a category with information from CategoryID and returns whether fail/success after execution.
     * Provides detailed fail messages according to each condition listed below
     *  1. NumberFormatException: User tries to edit a budget value with input that can not be converted to a double.
     *  2. Category budget less than 0: User tries to edit a budget value with input that is a negative number.
     *  3. EntityException: User tries to edit a Category with invalid input but failed. (See entities/EntityException.java)
     *
     * @param categoryID_edit Category_Input_Data required for editing a new category to the designated monthID MonthlyStorage Object.
     * @return Category_Output_Data category.
     * @throws EntityException thrown when categoryID_add has invalid category information.
     */
    @Override
    public CategoryOD editCategoryInMonth(CategoryID categoryID_edit) throws EntityException {
        MonthlyStorage month = categoryID_edit.getSession().getMonthlyData(categoryID_edit.getMonthID());
        ArrayList<Category> category_list = month.getCategoryData();
        try {
            Category c = findCategory(category_list, categoryID_edit.getOld_category().getName());

            c.setName(categoryID_edit.getName());
            if(isValidDouble(categoryID_edit.getValue())){
                double value_double = Double.parseDouble(String.valueOf(categoryID_edit.getValue()));
                categoryID_edit.setValue(value_double);
            }
            if ((double)categoryID_edit.getValue() < 0) {
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }
            this.categoryOD = new CategoryOD(c);
        } catch (NoSuchElementException e){
            categoryOB.fail("There is no such category in the current month. Please add a new category or select an existing category!");
        } catch (ClassCastException e) {
            categoryOB.fail("Category budget needs to be a number. Please try again!");
        }
        return categoryOB.success_edit(categoryOD);
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
        return null;
    }
}