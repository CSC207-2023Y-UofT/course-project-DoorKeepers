package use_cases.add_edit_category_use_case;

public class CategoryP implements CategoryOB {
    public String success = "You have created a new category! ";

    @Override
    public CategoryOD success_add(CategoryOD categoryOD_add) {
        // category(OD) update in month
        return categoryOD_add;
    }


    public void fail(String error){throw new RuntimeException(error);
    }

    @Override
    public CategoryOD success_edit(CategoryOD categoryOD_edit) {
        return categoryOD_edit;
    }

}

