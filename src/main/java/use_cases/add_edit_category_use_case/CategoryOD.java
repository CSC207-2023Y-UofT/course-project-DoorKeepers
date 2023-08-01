package use_cases.add_edit_category_use_case;

/**
 * Category_Output_Data passes  from programme when the category is successfully added/edited.
 * User will have to re-enter the programme if wish to add or edit a category.(Hence no setCategory())
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

