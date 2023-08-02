package use_cases.add_edit_category_use_case;

/**
 * An interface Category_Output_Boundary containing methods implemented in Category_Presenter.
 */
public interface CategoryOB {
    /**
     * Returns a CategoryOD notifying success add.
     * @return String success add message.
     */
    CategoryOD success_add();

    /**
     * Returns a CategoryOD containing a String formatted error message.
     * @param error Detailed error message for user.
     * @return String Error message.
     */
    CategoryOD fail(String error);

    /**
     * Returns a CategoryOD notifying success edit.
     * @return String success edit message.
     */
    CategoryOD success_edit();
}
