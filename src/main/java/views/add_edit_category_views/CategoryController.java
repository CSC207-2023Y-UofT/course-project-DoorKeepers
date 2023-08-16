package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryInputBoundary;
import use_cases.add_edit_category_use_case.CategoryInputData;
import use_cases.add_edit_category_use_case.CategoryOutputData;

/**
 * CategoryController passes in user input information in the form of CategoryInputData.
 * The CategoryInputBoundary calls methods in CategoryUseCaseInteractor to perform the use cases.
 * Returns a CategoryOutputData Object for classes AddCategoryView() and EditCategoryView().
 */
public class CategoryController {
    private final CategoryInputBoundary input;

    /**
     * Constructs CategoryController with a CategoryInputBoundary.
     *
     * @param categoryInputBoundary a CategoryInputBoundary.
     */
    public CategoryController(CategoryInputBoundary categoryInputBoundary) {
        this.input = categoryInputBoundary;
    }

    /**
     * Returns CategoryOutputData Object when the category is successfully edited to the designated month with monthId in the working session.
     *
     * @param name        user input String category name
     * @param value       user input Object category budget
     * @param monthID     int representing current month
     * @param session     SessionStorage current session
     * @param oldCategory Category existing category selected by user in edit use case;
     *                    assigned null in add use case(implementation in AddCategoryView).
     * @return CategoryOutputData for success edit
     * @throws EntityException thrown when edit category attempt fails.
     */
    public CategoryOutputData categoryInMonth(String name, Object value, int monthID, SessionStorage session, String oldCategory) throws EntityException {
        CategoryInputData categoryInputData = new CategoryInputData(name, value, monthID, session, oldCategory);
        if (oldCategory == null) {
            return input.addCategoryInMonth(categoryInputData);
        } else {
            return input.editCategoryInMonth(categoryInputData);
        }
    }
}
