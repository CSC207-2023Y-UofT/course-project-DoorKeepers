package views.add_edit_category_views;

import use_cases.add_edit_category_use_case.CategoryOB;
import use_cases.add_edit_category_use_case.CategoryOD;

/**
 * CategoryP notifies success/fail to add or edit category, implements CategoryOB.
 * Returns CategoryOD when execution is successful.
 * Returns String error message when failed.
 */
public class CategoryP implements CategoryOB {
    /**
     * Returns a CategoryOD notifying success.
     * @return CategoryOD with success message.
     */
    @Override
    public CategoryOD success(CategoryOD categoryOD) {
        return categoryOD;
    }
    /**
     * Returns a CategoryOD notifying fail.
     * @return CategoryOD with fail message.
     */
    @Override
    public CategoryOD fail(CategoryOD Fails) {
        return Fails;
    }
}

