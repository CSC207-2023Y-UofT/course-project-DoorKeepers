package use_cases.add_edit_category_use_case;

import entities.Category;
import entities.EntityException;
import entities.MonthlyStorage;
import views.add_edit_category_views.CategoryP;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CategoryUCI implements CategoryIB {
    final CategoryOB categoryOB;
    private CategoryOD categoryOD;

    public CategoryUCI(CategoryP categoryP) {
        this.categoryOB = categoryP;
        this.categoryOD = null;
    }

    public static boolean isValidDouble(Object value){
        try{
            Double.parseDouble(String.valueOf(value));
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }

    @Override
    public CategoryOD addCategoryInMonth(CategoryID categoryID_add) throws EntityException {
        MonthlyStorage month = categoryID_add.getSession().getMonthlyData(categoryID_add.getMonthID());

        try{
            if(isValidDouble(categoryID_add.getValue())){
                double value_double = Double.parseDouble(String.valueOf(categoryID_add.getValue()));
                categoryID_add.setValue(value_double);
            }
            if ((double)categoryID_add.getValue() < 0) {
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }
            Category category = new Category(categoryID_add.getName(), (double) categoryID_add.getValue());
            month.addCategory(category);
            this.categoryOD = new CategoryOD(category);

        } catch (NumberFormatException e) {
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
            Category c = findCategory(category_list, categoryID_edit.getOld_category().getName());

            c.setName(categoryID_edit.getName());
            if(isValidDouble(categoryID_edit.getValue())){
                double value_double = Double.parseDouble(String.valueOf(categoryID_edit.getValue()));
                categoryID_edit.setValue(value_double);
            }
            if ((double)categoryID_edit.getValue() < 0) {
                categoryOB.fail("Category budget can't be less than $0. Please try again!");
            }
            this.categoryOD = new CategoryOD(c);
        } catch (NoSuchElementException e){
            categoryOB.fail("There is no such category in the current month. Please add a new category or select an existing category!");
        } catch (ClassCastException e) {
            categoryOB.fail("Category budget needs to be a number. Please try again!");
        }
        return categoryOB.success_edit(categoryOD);
    }

    @Override
    public Category findCategory(ArrayList<Category> monthCategoryData, String name){
        for (Category c : monthCategoryData){
            if (Objects.equals(c.getName(), name)){
                return c;}
        }
        return null;
    }
}