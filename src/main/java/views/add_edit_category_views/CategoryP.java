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
     * @return CategoryOD from success add attempt
     */
    @Override
    public CategoryOD success_add() {
        String success_add = "You have added a new category!";
        return new CategoryOD(success_add);
    }
    /**
     * Overrides fail() from Category_OB.
     * @param error Detailed error message for user.
     * @return String description of error from user.
     */
    @Override
    public CategoryOD fail(String error){
        return new CategoryOD(error);
    }
    /**
     * Overrides success_edit from Category_OB.
     * @return CategoryOD from success edit attempt
     */
    @Override
    public CategoryOD success_edit() {
        String success_edit = "You have edited a category!";
        return new CategoryOD(success_edit);
    }
}

