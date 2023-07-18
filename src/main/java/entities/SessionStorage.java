package entities;

import java.util.ArrayList;

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

    public void setMonthlyData(MonthlyStorage month){
        this.monthlyData.add(month);
    }

    public void setRecurData(Expense recurExpense){
        this.recurData.add(recurExpense);
    }

    public MonthlyStorage getMonthlyData(int currentMonth) throws EntityException {
        for (MonthlyStorage m: this.monthlyData) {
            if (m.getCurrentMonth() == currentMonth) {
                return m;
            }
        }
        throw new EntityException("That is not a valid currentMonth for this SessionStorage.");
    }

    public ArrayList<Expense> getRecurData(){
        return this.recurData;
    }
}
