package use_cases.add_edit_category_use_case;

/**
 * An interface Category_Output_Boundary containing methods implemented in Category_Presenter.
 */
public interface CategoryOB {
    /**
     * Returns same Category_Output_Data with given Category_Output_Data when category is successfully added.
     * @param categoryOD_add CategoryOD Object produced from adding the category
     * @return CategoryOD Object Passes Category Object.
     */
    CategoryOD success_add(CategoryOD categoryOD_add);

    /**
     * Returns String formatted error message.
     * @param error Detailed error message for user.
     * @return String Error message.
     */
    String fail(String error);
    /**
     * Returns same Category_Output_Data with given Category_Output_Data when category is successfully edited.
     * @param categoryOD_edit CategoryOD Object produced from editing a category
     * @return CategoryOD Object Passes Category Object.
     */
    CategoryOD success_edit(CategoryOD categoryOD_edit);

}
