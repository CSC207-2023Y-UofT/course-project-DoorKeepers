package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that will store all the user data for a month. Each month has an associated monthID, budget, list of
 * Category objects, and list of Expense objects.
 */
public class MonthlyStorage implements Serializable {

    private final int monthID;
    private final double monthlyBudget;
    private final ArrayList<Category> categoryData;
    private final ArrayList<Expense> expenseData;

    /**
     * Creates a new MonthlyStorage with the given data.
     * @param currentMonth integer representing the id of the MonthlyStorage
     * @param monthlyBudget budget of the MonthlyStorage
     */
    public MonthlyStorage(int currentMonth, double monthlyBudget){
        this.monthID = currentMonth;
        this.monthlyBudget = monthlyBudget;

        Category other = new Category("Other", 0);
        this.categoryData = new ArrayList<>();
        this.categoryData.add(other);
        this.expenseData = new ArrayList<>();
    }

    /**
     * Gets the monthID for this month.
     * @return the monthID associated with this MonthlyStorage
     */
    public int getMonthID(){
        return this.monthID;
    }

    /**
     * Gets the monthlyBudget for this month.
     * @return the monthlyBudget associated with this MonthlyStorage
     */
    public double getMonthlyBudget(){
        return this.monthlyBudget;
    }

    /**
     * Gets the list of categories for this month.
     * @return the categoryData associated with this MonthlyStorage
     */
    public ArrayList<Category> getCategoryData(){
        return this.categoryData;
    }

    /**
     * Gets the list of expenses for this month.
     * @return the expenseData associated with this MonthlyStorage
     */
    public ArrayList<Expense> getExpenseData(){
        return this.expenseData;
    }

    /**
     * Adds a new Category to this month.
     * @param category a Category object to add
     * @throws EntityException if this Category is already in MonthlyStorage
     */
    public void addCategory(Category category) throws EntityException {
        for (Category c: this.categoryData){
            if (c.equals(category)){
                throw new EntityException("There is already a Category with that name in this MonthlyStorage.");
            }
        }
        this.categoryData.add(category);
    }

    /**
     * Adds a new Expense to this month.
     * @param expense an Expense object to add
     * @throws EntityException if this Expense is already in MonthlyStorage
     */
    public void addExpense(Expense expense) throws EntityException{
        for (Expense e: this.expenseData){
            if (e.equals(expense)){
                throw new EntityException("There is already an Expense with that name in this MonthlyStorage.");
            }
        }
        this.expenseData.add(expense);
    }

    /**
     * Deletes a Category from this month.
     * @param categoryName a String referring to the Category object to delete
     */
    public void deleteCategory(String categoryName) {
        this.categoryData.removeIf(c -> Objects.equals(c.getName(), categoryName));
    }

    /**
     * Deletes an Expense from this month.
     * @param expenseName a String referring to the Expense object to delete
     */
    public void deleteExpense(String expenseName) {
        this.expenseData.removeIf(e -> Objects.equals(e.getName(), expenseName));
    }

    /**
     * Check if this MonthlyStorage is equal to Object
     * @param obj Any instance of Object
     * @return True if equals, False is not equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }

        MonthlyStorage other = (MonthlyStorage) obj;
        return Objects.equals(this.getMonthID(), other.getMonthID())
                && Objects.equals(this.getMonthlyBudget(), other.getMonthlyBudget())
                && Objects.equals(this.getCategoryData(), other.getCategoryData())
                && Objects.equals(this.getExpenseData(), other.getExpenseData());
    }
}
