package use_cases.add_edit_category_use_case;

import entities.EntityException;

/**
 * CategoryIB passes in user input of category information in the form of CategoryID.
 * Implemented by CategoryUCI.
 */
public interface CategoryIB {
    /**
     * Returns String success message in the form of a CategoryOD object when a category is successfully added,
     * returns String fail messages that are context specific when failed.
     * @param categoryIDAdd CategoryID required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOD indicating fail/success add attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOD addCategoryInMonth(CategoryID categoryIDAdd) throws EntityException;

    /**
     * Returns String success message in the form of a CategoryOD object when a category is successfully edited,
     * returns String fail messages that are context specific when failed.
     * @param categoryIDEdit CategoryID required for editing an existing Category Object in designated monthID MonthlyStorage Object.
     * @return CategoryOD indicating fail/success edit attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOD editCategoryInMonth(CategoryID categoryIDEdit) throws EntityException;

}
