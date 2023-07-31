package use_cases.add_edit_category_use_case;

import entities.Category;

/**
 * Category_Output_Data passes a Category object from programme when the category is successfully added/edited.
 * User will have to re-enter the programme if wish to add or edit a category.(Hence no setCategory())
 */
public class CategoryOD {
    Category category;

    public CategoryOD(Category category) {
        this.category = category;
    }
    public Category getCategory() {
        return category;
    }
}

