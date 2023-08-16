package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class that will store all the user data for a session. This includes a list of MonthlyStorage objects to store the
 * data for several months, and a list of recurring Expense objects that apply to every month.
 */
public class SessionStorage implements Serializable {

    private final ArrayList<MonthlyStorage> monthlyData;
    private final ArrayList<Expense> recurData;

    /**
     * Creates a new SessionStorage and assigns empty values to both its instance attributes.
     */
    public SessionStorage() {
        this.monthlyData = new ArrayList<>();
        this.recurData = new ArrayList<>();
    }

    /**
     * Adds a new month to monthlyData.
     *
     * @param month a MonthlyStorage object to add
     * @throws EntityException if this MonthlyStorage is already in SessionStorage
     */
    public void addMonth(MonthlyStorage month) throws EntityException {
        for (MonthlyStorage m : this.monthlyData) {
            if (m.equals(month)) {
                throw new EntityException("There is already a MonthlyStorage with that name in this SessionStorage.");
            }
        }
        this.monthlyData.add(month);
    }

    /**
     * Adds a new expense to the Session's recurExpense array.
     *
     * @param expense an Expense object to add
     */
    public void addRecurExpense(Expense expense) {
        this.recurData.add(expense);
    }

    /**
     * Deletes an Expense from this month.
     *
     * @param expenseName a String containing the name of the Expense to delete
     */
    public void deleteRecurExpense(String expenseName) {
        this.recurData.removeIf(e -> Objects.equals(e.getName(), expenseName));
    }

    /**
     * Returns the MonthlyData object associated with the given monthID.
     *
     * @param monthID an integer representing the MonthlyStorage object
     * @return a MonthlyStorage object with monthID attribute equal to monthID parameter
     * @throws EntityException if monthID parameter is not the monthID of any MonthlyStorage object
     */
    public MonthlyStorage getMonthlyData(int monthID) throws EntityException {
        for (MonthlyStorage m : this.monthlyData) {
            if (m.getMonthID() == monthID) {
                return m;
            }
        }
        throw new EntityException("That is not a valid monthID for this SessionStorage.");
    }

    /**
     * Returns the list of the MonthlyData objects stored in this Session
     *
     * @return list of all MonthlyData objects
     */
    public ArrayList<MonthlyStorage> getAllMonthlyData() {
        return this.monthlyData;
    }

    /**
     * Returns the list of recurring expenses in this Session
     *
     * @return list of recurring expenses
     */
    public ArrayList<Expense> getRecurData() {
        return this.recurData;
    }

    /**
     * Copies all the contents from another SessionStorage object into this one.
     * This method can be used to mutate the entire contents of a SessionStorage object in-place.
     *
     * @param other the SessionStorage object to copy the data from
     */
    public void copyDataFrom(SessionStorage other) {
        this.monthlyData.clear();
        this.monthlyData.addAll(other.monthlyData);
        this.recurData.clear();
        this.recurData.addAll(other.recurData);
    }

    /**
     * Check if this SessionStorage is equal to Object
     *
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
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        SessionStorage other = (SessionStorage) obj;
        return Objects.equals(this.getRecurData(), other.getRecurData())
                && Objects.equals(this.getAllMonthlyData(), other.getAllMonthlyData());
    }
}
