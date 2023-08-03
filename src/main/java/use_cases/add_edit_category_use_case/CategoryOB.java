package use_cases.add_edit_category_use_case;

/**
 * An interface CategoryOB containing methods implemented in CategoryP.
 */
public interface CategoryOB {
    /**
     * Returns a CategoryOD notifying success.
     * @return CategoryOD with success message.
     */
    CategoryOD success(CategoryOD categoryOD);

    /**
     * Returns a CategoryOD notifying fail.
     * @return CategoryOD with fail message.
     */
    CategoryOD fail(CategoryOD Fails);
}