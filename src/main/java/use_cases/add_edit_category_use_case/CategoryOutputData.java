package use_cases.add_edit_category_use_case;

/**
 * CategoryOutputData passes a String message (from CategoryPresenter) resulting from the success or fail add/edit category attempt.
 */
public class CategoryOutputData {
    private final String message;

    public CategoryOutputData(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

