package use_cases.add_edit_category_use_case;

/**
 * An interface CategoryOB containing methods implemented in CategoryP.
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
