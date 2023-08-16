package use_cases.add_edit_category_use_case;

/**
 * An interface CategoryOutputBoundary passes CategoryOutputData created in success or fail attempts.
 * Implemented in CategoryPresenter.
 */
public interface CategoryOutputBoundary {
    /**
     * Returns a CategoryOutputData notifying success.
     *
     * @param categoryOutputData the CategoryOutputData to return
     * @return CategoryOutputData with success message.
     */
    CategoryOutputData success(CategoryOutputData categoryOutputData);

    /**
     * Returns a CategoryOutputData notifying fail.
     *
     * @param Fails the CategoryOutputData to return
     * @return CategoryOutputData with fail message.
     */
    CategoryOutputData fail(CategoryOutputData Fails);
}