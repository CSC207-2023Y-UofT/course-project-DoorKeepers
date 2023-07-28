package use_cases.add_edit_category_use_case;

import entities.Category;

public class CategoryOD {
    Category category;

    public CategoryOD(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

