package views.add_edit_category_views;

import use_cases.add_edit_category_use_case.CategoryOB;
import use_cases.add_edit_category_use_case.CategoryOD;

public class CategoryP implements CategoryOB {

    @Override
    public CategoryOD success_add(CategoryOD categoryOD_add) {
        // category(OD) update in month
        return categoryOD_add;
    }


    public String fail(String error){
        return error;
    }

    @Override
    public CategoryOD success_edit(CategoryOD categoryOD_edit) {
        return categoryOD_edit;
    }

}

