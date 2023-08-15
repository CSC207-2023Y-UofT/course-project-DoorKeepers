package views.add_edit_category_views;

import use_cases.add_edit_category_use_case.CategoryOutputBoundary;
import use_cases.add_edit_category_use_case.CategoryOutputData;

/**
 * CategoryPresenter notifies success/fail to add or edit category, implements CategoryOutputBoundary.
 * Returns CategoryOutputData when use case is finished.
 */
public class CategoryPresenter implements CategoryOutputBoundary {
    /**
     * Returns a CategoryOutputData notifying success.
     * @return CategoryOutputData with success message.
     */
    @Override
    public CategoryOutputData success(CategoryOutputData categoryOutputData) {
        return categoryOutputData;
    }
    /**
     * Returns a CategoryOutputData notifying fail.
     * @return CategoryOutputData with fail message.
     */
    @Override
    public CategoryOutputData fail(CategoryOutputData Fails) {
        return Fails;
    }
}

