package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;

import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class CategoryUCI implements CategoryIB {
    final CategoryOB categoryOB;
    private CategoryOD categoryOD;

    public CategoryUCI(CategoryP categoryP) {
        this.categoryOB = categoryP;
        this.categoryOD = null;
    }

    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryID_add) throws EntityException {
        MonthlyStorage month = categoryID_add.getSession().getMonthlyData(categoryID_add.getMonthID());

        try {
            categoryID_add.setValue((double) categoryID_add.getValue());
            if ((double) categoryID_add.getValue() < 0) {
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }
            Category category = new Category(categoryID_add.getName(), (double) categoryID_add.getValue());
            month.addCategory(category);
            this.categoryOD = new CategoryOD(category);

        } catch (ClassCastException e) {
            categoryOB.fail("Category budget needs to be a number. Please try again!");
        } catch (EntityException e) {
            categoryOB.fail("Fail to add new category. Please try again!");
        }

        return categoryOB.success_add(categoryOD);

    }

    @Override
    public CategoryOD editCategoryInMonth(CategoryID categoryID_edit) throws EntityException {
        MonthlyStorage month = categoryID_edit.getSession().getMonthlyData(categoryID_edit.getMonthID());
        ArrayList<Category> category_list = month.getCategoryData();
        try {
            categoryID_edit.setValue((double) categoryID_edit.getValue());
            if ((double) categoryID_edit.getValue() < 0) {
                categoryOB.fail("Category budget can't be less than $0. Please try again!");

                for (Category category :
                        category_list) {
                    if (category.getName().equals(categoryID_edit.getName())) {
                        category.setName(categoryID_edit.getName());
                        category.setBudget((double) categoryID_edit.getValue());
                    }
                    this.categoryOD = new CategoryOD(category);
                }
            }
        } catch (ClassCastException e) {
            categoryOB.fail("Category budget needs to be a number. Please try again!");
        }
        return categoryOB.success_edit(categoryOD);
    }
}