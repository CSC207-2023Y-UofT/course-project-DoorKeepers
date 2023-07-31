package use_cases.add_edit_category_use_case;

/**
 * An interface Category_Output_Boundary containing methods implemented in Category_Presenter.
 */
public interface CategoryOB {
    /**
     * Returns same Category_Output_Data with given Category_Output_Data when category is successfully added.
     * @param categoryOD_add CategoryOD Object that produced from adding the category
     * @return CategoryOD Object Passes Category Object.
     */
    CategoryOD success_add(CategoryOD categoryOD_add);
    String fail(String error);
    /**
     * Throws RunTimeException when fails and
     * @param categoryOD_edit
     * @return
     */
    CategoryOD success_edit(CategoryOD categoryOD_edit);

}
