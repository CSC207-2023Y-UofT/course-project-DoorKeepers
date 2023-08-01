package entities;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that will store all the user data for a session. This includes a list of MonthlyStorage objects to store the
 * data for several months, and a list of recurring Expense objects that apply to every month.
 */
public class SessionStorage {

    private final ArrayList<MonthlyStorage> monthlyData;
    private final ArrayList<Expense> recurData;

    /**
     * Creates a new SessionStorage and assigns empty values to both its instance attributes.
     */
    public SessionStorage(){
        this.monthlyData = new ArrayList<>();
        this.recurData = new ArrayList<>();
    }

    /**
     * Adds a new month to the monthlyData.
     * @param month a MonthlyStorage object to add
     */
    public void addMonth(MonthlyStorage month){
        this.monthlyData.add(month);
    }

    /**
     * Adds a new expense to the Session's recurExpense array.
     * @param expense an Expense object to add
     */
    public void addRecurExpense(Expense expense){
        this.recurData.add(expense);
    }

    /**
     * Deletes an Expense from this month.
     * @param expense_name a String containing the name of the Expense to delete
     */
    public void deleteRecurExpense(String expense_name) {
        this.recurData.removeIf(e -> Objects.equals(e.getName(), expense_name));
    }

    /**
     * Returns the MonthlyData object associated with the given monthID.
     * @param monthID an integer representing the MonthlyStorage object
     * @return a MonthlyStorage object with monthID attribute equal to monthID parameter
     * @throws EntityException if monthID parameter is not the monthID of any MonthlyStorage object
     */
    public MonthlyStorage getMonthlyData(int monthID) throws EntityException {
        for (MonthlyStorage m: this.monthlyData) {
            if (m.getMonthID() == monthID) {
                return m;
            }
        }
        throw new EntityException("That is not a valid monthID for this SessionStorage.");
    }

    /**
     * Returns the list of recurring expenses in this Session.
     * @return list of recurring Expense
     */
    public ArrayList<Expense> getRecurData(){
        return this.recurData;
    }
}
