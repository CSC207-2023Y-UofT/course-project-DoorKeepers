package use_cases.monthly_menu;

import entities.Category;
import entities.Expense;

import java.util.ArrayList;

/**
 * The output data class for storing view output. The interactor
 * class for getting the Month Menu output creates an object of
 * this class, and pass it into the output boundary. The output
 * boundary returns this type of object, and is then formatted
 * and shown in the Month Menu view.
 */
public class MonthMenuOD {
    ArrayList<Expense> expenseData;
    ArrayList<Category> categoryData;
    String warning;

    /**
     * Constructs an MonthMenuOD holding output data.
     * @param expenseData ArrayList of Expense stored in a MonthlyStorage
     * @param categoryData ArrayList of Category stored in a MonthlyStorage
     */
    public MonthMenuOD(ArrayList<Expense> expenseData, ArrayList<Category> categoryData){
        this.expenseData = expenseData;
        this.categoryData = categoryData;
    }

    /**
     * Constructs an MonthMenuOD holding error message.
     * @param warning error message to show in the view
     */
    public MonthMenuOD(String warning){
        this.warning = warning;
    }

    /**
     * Gets the expenseData stored.
     * @return expenseData to be outputted
     */
    public ArrayList<Expense> getExpenseData() {
        return expenseData;
    }

    /**
     * Gets the categoryData stored.
     * @return categoryData to be outputted
     */
    public ArrayList<Category> getCategoryData() {
        return categoryData;
    }

    /**
     * Gets the error message stored.
     * @return warning message to be outputted
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Sets specified ArrayList of Expense to expenseData variable.
     * @param expenseData ArrayList of Expense stored in a MonthlyStorage
     */
    public void setExpenseData(ArrayList<Expense> expenseData) {
        this.expenseData = expenseData;
    }

    /**
     * Sets specified ArrayList of Category to categoryData variable.
     * @param categoryData ArrayList of Category stored in a MonthlyStorage
     */
    public void setCategoryData(ArrayList<Category> categoryData) {
        this.categoryData = categoryData;
    }

    /**
     * Sets specified String to warning variable.
     * @param warning error message to show in the view
     */
    public void setWarning(String warning) {
        this.warning = warning;
    }
}
