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
    private ArrayList<Expense> expenseData;
    private ArrayList<Category> categoryData;
    private String warning;
    boolean successful;

    /**
     * Constructs an MonthMenuOD holding output data.
     * @param expenseData ArrayList of Expense stored in a MonthlyStorage
     * @param categoryData ArrayList of Category stored in a MonthlyStorage
     * @param successful boolean indicating whether access to Expense/Category list was successful
     */
    public MonthMenuOD(ArrayList<Expense> expenseData, ArrayList<Category> categoryData, boolean successful){
        this.expenseData = expenseData;
        this.categoryData = categoryData;
        this.successful = successful;
    }

    /**
     * Constructs an MonthMenuOD holding error message.
     * @param warning error message to show in the view
     * @param successful boolean indicating whether access to Expense/Category list was successful
     */
    public MonthMenuOD(String warning, boolean successful){
        this.warning = warning;
        this.successful = successful;
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
     * @return true if access to Expense/Category list was successful
     */
    public boolean isSuccessful() {
        return successful;
    }
}
