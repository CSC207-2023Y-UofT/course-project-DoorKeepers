package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;

import java.util.ArrayList;

/**
 * Category_Input_Boundary passes in user input of category information in the form of CategoryID.
 */
public interface CategoryIB {
    /**
     * Returns a Category object in the form of a Category_OD object when a category is successfully added,
     * returns context specific fail messages when failed.
     * @param categoryID_add Category_Input_Data required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOD Object The category that is successfully added.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOD addCategoryInMonth(CategoryID categoryID_add) throws EntityException;
    /**
     * Returns a Category object in the form of a Category_OD object when a category is successfully edited,
     * returns context specific fail messages when failed.
     * @param categoryID_edit Category_Input_Data required for editing an existing Category Object in designated monthID MonthlyStorage Object.
     * @return CategoryOD Object The category that is successfully edited.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOD editCategoryInMonth(CategoryID categoryID_edit) throws EntityException;
    /**
     * Helper method that returns a category with String name from a list of categories.
     * @param monthCategoryData An ArrayList of categories.
     * @param name Category name.
     * @return Category The category with String name.
     */
    Category findCategory(ArrayList<Category> monthCategoryData, String name);
}
