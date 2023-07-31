package views.add_edit_category_views;

import use_cases.add_edit_category_use_case.CategoryOB;
import use_cases.add_edit_category_use_case.CategoryOD;

/**
 * Category_Presenter notifies success/fail to add or edit category, implements CategoryOB.
 * Returns Category_OD when execution is successful.
 * Returns String error message when failed.
 */
public class CategoryP implements CategoryOB {
    /**
     * Overrides success_add from Category_OB.
     * @param categoryOD_add CategoryOD Object produced from adding the category
     * @return CategoryOD from success add attempt
     */
    @Override
    public CategoryOD success_add(CategoryOD categoryOD_add) {
        // category(OD) update in month
        return categoryOD_add;
    }
    /**
     * Overrides fail() from Category_OB.
     * @param error Detailed error message for user.
     * @return String description of error from user.
     */
    @Override
    public String fail(String error){
        return error;
    }
    /**
     * Overrides success_edit from Category_OB.
     * @param categoryOD_edit CategoryOD Object produced from editing a category
     * @return CategoryOD from success edit attempt
     */
    @Override
    public CategoryOD success_edit(CategoryOD categoryOD_edit) {
        return categoryOD_edit;
    }
}

