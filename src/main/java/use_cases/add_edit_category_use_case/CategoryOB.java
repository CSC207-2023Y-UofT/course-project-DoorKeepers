package use_cases.add_edit_category_use_case;

public interface CategoryOB {
    CategoryOD success_add(CategoryOD categoryOD_add);
    void fail(String error);
    CategoryOD success_edit(CategoryOD categoryOD_edit);

}
