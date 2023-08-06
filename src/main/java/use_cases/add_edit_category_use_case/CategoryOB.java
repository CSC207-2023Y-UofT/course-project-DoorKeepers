package use_cases.add_edit_category_use_case;

/**
 * An interface CategoryOB passes CategoryOD created in success or fail attempts.
 * Implemented in CategoryP.
 */
public interface CategoryOB {
    /**
     * Returns a CategoryOD notifying success.
     * @param categoryOD the CategoryOD to return
     * @return CategoryOD with success message.
     */
    CategoryOD success(CategoryOD categoryOD);

    /**
     * Returns a CategoryOD notifying fail.
     * @param Fails the CategoryOD to return
     * @return CategoryOD with fail message.
     */
    CategoryOD fail(CategoryOD Fails);
}