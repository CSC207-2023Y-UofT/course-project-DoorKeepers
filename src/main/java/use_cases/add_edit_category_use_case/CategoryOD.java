package use_cases.add_edit_category_use_case;

/**
 * CategoryOD passes a String message (from CategoryP) resulting from the success or fail add/edit category attempt.
 */
public class CategoryOD {
    String message;

    public CategoryOD(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

