package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryIB;
import use_cases.add_edit_category_use_case.CategoryID;
import use_cases.add_edit_category_use_case.CategoryOD;

/**
 * CategoryC connects the user interface and the programme.
 * Returns a CategoryOD Object wh
 */
public class CategoryC {
    /**
     * Category_Controller passes in user input and produces a Category_Output_Data Object according to a use case.
     */
    final CategoryIB input;

    /**
     * Constructs CategoryC with a CategoryIB.
     * @param CategoryIB a CategoryIB.
     */
    public CategoryC(CategoryIB CategoryIB){
        this.input = CategoryIB;
    }

    /**
     * Returns CategoryOD Object when the category is successfully edited to the designated month with monthId in the working session.
     * @param name user input String category name
     * @param value user input Object category budget
     * @param monthID int representing current month
     * @param session SessionStorage current session
     * @param oldCategory Category existing category selected by user
     * @return Category_OD for success edit
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
