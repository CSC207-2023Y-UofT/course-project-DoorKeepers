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
     * Returns a CategoryOD notifying success add.
     * @return String success add message.
     */
    @Override
    public CategoryOD success_add() {
        String successAdd = "You have added a new category!";
        return new CategoryOD(successAdd);
    }

    /**
     * Returns a CategoryOD containing a String formatted error message.
     * @param error Detailed error message for user.
     * @return String Error message.
     */
    @Override
    public CategoryOD fail(String error){
        return new CategoryOD(error);
    }

    /**
     * Returns a CategoryOD notifying success edit.
     * @return String success edit message.
     */
    @Override
    public CategoryOD success_edit() {
        String successEdit = "You have edited a category!";
        return new CategoryOD(successEdit);
    }
}

