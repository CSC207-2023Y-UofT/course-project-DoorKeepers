package use_cases.add_edit_category_use_case;

/**
 * An interface Category_Output_Boundary containing methods implemented in Category_Presenter.
 */
public interface CategoryOB {
    /**
     * Returns same Category_Output_Data with given Category_Output_Data when category is successfully added.
     * @return CategoryOD Object Passes Category Object.
     */

    CategoryOD success_add();

    /**
     * Returns String formatted error message.
     * @param error Detailed error message for user.
     * @return String Error message.
     */
    CategoryOD fail(String error);
    /**
     * Returns same Category_Output_Data with given Category_Output_Data when category is successfully edited.
     * @return CategoryOD Object Passes Category Object.
     */

    CategoryOD success_edit();
}
