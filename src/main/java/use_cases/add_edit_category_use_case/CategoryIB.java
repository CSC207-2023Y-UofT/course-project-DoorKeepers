package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;

import java.util.ArrayList;

/**
 * CategoryIB passes in user input of category information in the form of CategoryID.
 */
public interface CategoryIB {
    /**
     * Returns String success message in the form of a CategoryOD object when a category is successfully added,
     * returns String fail messages that are context specific when failed.
     * @param categoryIDAdd CategoryID required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return String indicating fail/success add attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOD addCategoryInMonth(CategoryID categoryIDAdd) throws EntityException;

    /**
     * Returns String success message in the form of a CategoryOD object when a category is successfully edited,
     * returns String fail messages that are context specific when failed.
     * @param categoryIDEdit CategoryID required for editing an existing Category Object in designated monthID MonthlyStorage Object.
     * @return String indicating fail/success edit attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOD editCategoryInMonth(CategoryID categoryIDEdit) throws EntityException;
    /**
     * Helper method that returns a category with String name from a list of categories.
     * @param monthCategoryData An ArrayList of categories.
     * @param name Category name.
     * @return Category The category with String name.
     */
    Category findCategory(ArrayList<Category> monthCategoryData, String name);
}
