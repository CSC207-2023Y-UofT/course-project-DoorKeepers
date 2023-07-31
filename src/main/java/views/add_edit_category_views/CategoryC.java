package views.add_edit_category_views;

import entities.Category;
import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryIB;
import use_cases.add_edit_category_use_case.CategoryID;
import use_cases.add_edit_category_use_case.CategoryOD;

public class CategoryC {
    /**
     * Category_Controller passes in user input and produces a Category_Output_Data Object for a use case.
     */
    final CategoryIB input;

    /**
     * Constructs Category_Controller with a Category_Input_Boundary Object.
     * @param CategoryIB a Category_Input_Boundary Object.
     */
    public CategoryC(CategoryIB CategoryIB){
        this.input = CategoryIB;
    }

    /**
     * Returns Category_Output_Data Object when a category is successfully added to the designated month with monthId in the working session.
     * @param name user input String category name
     * @param value user input Object category budget
     * @param monthID int representing current month
     * @param session SessionStorage current session
     * @return Category_OD for success add
     * @throws EntityException thrown when add category attempt fails.
     */
    public CategoryOD addCategoryInMonth(String name, Object value, int monthID, SessionStorage session) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session);
        return input.addCategoryInMonth(categoryID);
    }
    /**
     * Returns Category_Output_Data Object when the category is successfully edited to the designated month with monthId in the working session.
     * @param name user input String category name
     * @param value user input Object category budget
     * @param monthID int representing current month
     * @param session SessionStorage current session
     * @param old_category Category existing category selected by user
     * @return Category_OD for success edit
     * @throws EntityException thrown when edit category attempt fails.
     */
    public CategoryOD editCategoryInMonth(String name, Object value, int monthID, SessionStorage session, Category old_category) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session, old_category);
        return input.editCategoryInMonth(categoryID);
    }
}
