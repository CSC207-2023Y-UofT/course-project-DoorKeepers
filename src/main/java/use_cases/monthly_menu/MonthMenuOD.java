package use_cases.monthly_menu;

import entities.Category;
import entities.Expense;

import java.util.ArrayList;

public class MonthMenuOD {
    ArrayList<Expense> expenseData;
    ArrayList<Category> categoryData;
    String warning;

    public MonthMenuOD(ArrayList<Expense> expenseData, ArrayList<Category> categoryData){
        this.expenseData = expenseData;
        this.categoryData = categoryData;
    }

    public MonthMenuOD(String warning){
        this.warning = warning;
    }

    public ArrayList<Expense> getExpenseData() {
        return expenseData;
    }

    public ArrayList<Category> getCategoryData() {
        return categoryData;
    }

    public String getWarning() {
        return warning;
    }

    public void setExpenseData(ArrayList<Expense> expenseData) {
        this.expenseData = expenseData;
    }

    public void setCategoryData(ArrayList<Category> categoryData) {
        this.categoryData = categoryData;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }
}
