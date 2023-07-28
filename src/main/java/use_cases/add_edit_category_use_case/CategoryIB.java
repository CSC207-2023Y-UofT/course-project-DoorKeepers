package use_cases.add_edit_category_use_case;

import entities.EntityException;

public interface CategoryIB {
    CategoryOD addCategoryInMonth(CategoryID categoryC_add) throws EntityException;
    CategoryOD editCategoryInMonth(CategoryID categoryC_edit) throws EntityException;
}
