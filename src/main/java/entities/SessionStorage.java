package entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 */
public class SessionStorage {

    private final ArrayList<MonthlyStorage> monthlyData;
    private final ArrayList<Expense> recurData;

    /**
     * Creates a new SessionStorage and assigns empty values to both its instance attributes
     */
    public SessionStorage(){
        this.monthlyData = new ArrayList<>();
        this.recurData = new ArrayList<>();
    }

    /**
     * Adds a new month to the monthlyData
     * @param month a MonthlyStorage object
     */
    public void addMonth(MonthlyStorage month){
        this.monthlyData.add(month);
    }

    /**
     * Adds a new expense to the Session's recurExpense array
     * @param expense an Expense object
     */
    public void addRecurExpense(Expense expense){
        this.recurData.add(expense);
    }

    /**
     * Deletes an Expense from this month
     * @param expense_name a String containing the name of the Expense to delete
     */
    public void deleteRecurExpense(String expense_name) {
        this.recurData.removeIf(e -> Objects.equals(e.getName(), expense_name));
    }

    /**
     * Returns a MonthlyData object given the currentMonth
     * @param monthID an integer representing the MonthlyStorage object
     * @return a MonthlyStorage object with currentMonth attribute equal to currentMonth parameter
     * @throws EntityException if currentMonth parameter is not the currentMonth of any MonthlyStorage object
     */
    public MonthlyStorage getMonthlyData(int monthID) throws EntityException {
        for (MonthlyStorage m: this.monthlyData) {
            if (m.getMonthID() == monthID) {
                return m;
            }
        }
        throw new EntityException("That is not a valid currentMonth for this SessionStorage.");
    }

    /**
     * Returns the list of recurring expenses in this Session
     * @return list of recurring expenses
     */
    public ArrayList<Expense> getRecurData(){
        return this.recurData;
    }
}
