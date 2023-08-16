package use_cases.add_edit_category_use_case;

import entities.EntityException;

/**
 * CategoryInputBoundary passes in user input of category information in the form of CategoryInputData.
 * Implemented by CategoryUseCaseInteractor.
 */
public interface CategoryInputBoundary {
    /**
     * Returns String success message in the form of a CategoryOutputData object when a category is successfully added,
     * returns String fail messages that are context specific when failed.
     *
     * @param categoryInputDataAdd CategoryInputData required for adding a new category to the designated monthID MonthlyStorage Object.
     * @return CategoryOutputData indicating fail/success add attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOutputData addCategoryInMonth(CategoryInputData categoryInputDataAdd) throws EntityException;

    /**
     * Returns String success message in the form of a CategoryOutputData object when a category is successfully edited,
     * returns String fail messages that are context specific when failed.
     *
     * @param categoryInputDataEdit CategoryInputData required for editing an existing Category Object in designated monthID MonthlyStorage Object.
     * @return CategoryOutputData indicating fail/success edit attempt.
     * @throws EntityException thrown when the new category input is invalid.
     */
    CategoryOutputData editCategoryInMonth(CategoryInputData categoryInputDataEdit) throws EntityException;

}
