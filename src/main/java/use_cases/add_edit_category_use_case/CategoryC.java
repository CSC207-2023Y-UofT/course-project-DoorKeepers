package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.SessionStorage;

public class CategoryC {
    final CategoryIB input;

    public CategoryC(CategoryIB CategoryIB){this.input = CategoryIB;}
    CategoryOD addCategory(String name, double value, int monthID, SessionStorage session) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session);
        return input.addCategoryInMonth(categoryID);
    }

    CategoryOD editCategory(String name, double value, int monthID, SessionStorage session, Category old_category) throws EntityException {
        CategoryID categoryID = new CategoryID(name, value, monthID, session, old_category);
        return input.editCategoryInMonth(categoryID);
    }

}
