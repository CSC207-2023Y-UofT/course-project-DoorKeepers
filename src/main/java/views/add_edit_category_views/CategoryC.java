package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryIB;
import use_cases.add_edit_category_use_case.CategoryID;
import use_cases.add_edit_category_use_case.CategoryOD;

/**
 * CategoryC passes in user input information in the form of CategoryID.
 * The CategoryIB calls methods in CategoryUCI to perform the use cases.
 * Returns a CategoryOD Object for classes AddCategoryV() and EditCategoryV().
 */
public class CategoryC {
    /**
     * CategoryC passes in user input and produces a Category_Output_Data Object according to a use case.
     */
    final CategoryIB input;

    /**
     * Constructs CategoryC with a CategoryIB.
     * @param categoryIB a CategoryIB.
     */
    public CategoryC(CategoryIB categoryIB){
        this.input = categoryIB;
    }

    /**
     * Returns CategoryOD Object when the category is successfully edited to the designated month with monthId in the working session.
     * @param name user input String category name
     * @param value user input Object category budget
     * @param monthID int representing current month
     * @param session SessionStorage current session
     * @param oldCategory Category existing category selected by user in edit use case;
     *                    assigned null in add use case(implementation in AddCategoryV).
     * @return CategoryOD for success edit
     * @throws EntityException thrown when edit category attempt fails.
     */
    public CategoryOD categoryInMonth(String name, Object value, int monthID, SessionStorage session, String oldCategory) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session, oldCategory);
        if(oldCategory == null){
            return input.addCategoryInMonth(categoryID);
        }
        else {return input.editCategoryInMonth(categoryID);}
    }
}
