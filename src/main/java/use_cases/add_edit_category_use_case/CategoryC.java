package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.SessionStorage;

public class CategoryC {
    final CategoryIB input;

    public CategoryC(CategoryIB CategoryIB){
        this.input = CategoryIB;
    }
    public CategoryOD addCategoryInMonth(String name, Object value, int monthID, SessionStorage session) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session);
        return input.addCategoryInMonth(categoryID);
    }

    public CategoryOD editCategoryInMonth(String name, Object value, int monthID, SessionStorage session, Category old_category) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session, old_category);
        return input.editCategoryInMonth(categoryID);
    }

//    public CategoryOD pass(int monthID, SessionStorage curr_session){
//        CategoryID categoryID = new CategoryID(monthID, curr_session);
//        return input.addCategoryInMonth(categoryID);
//    }

}
