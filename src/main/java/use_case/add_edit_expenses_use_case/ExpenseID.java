package use_case.add_edit_expenses_use_case;

import entities.SessionStorage;
/**
 * ExpenseID contains all user inputs.
 * Created in ExpenseC and used in ExpenseUCI to perform addExpenseInMonth() and editExpenseInMonth().
 */
public class ExpenseID {
    private final String name;
    private Object value;

    private String oldCategory;
    private boolean isRecurringExpense;
    private final int monthID;
    private final SessionStorage session;
    private final String oldExpense;

    /**
     * Constructs ExpenseID for adding/editing an existing Expense Object.
     * @param name Expense name
     * @param value Expense budget
     * @param oldCategory String Category name representing an existing Category in which the Expense belongs to.
     * @param isRecurringExpense boolean indicating whether an Expense is recurring.
     * @param monthID An int representing the month which the Expense Object belongs to.
     * @param session The current session which the MonthlyStorage Object belongs to.
     * @param oldExpense String Expense name representing an existing Expense in the MonthlyStorage Object the user wish to edit.
     */
    public ExpenseID(String name, Object value, String oldCategory, boolean isRecurringExpense, int monthID, SessionStorage session, String oldExpense) {
        this.name = name;
        this.value = value;
        this.oldCategory = oldCategory;
        this.isRecurringExpense = isRecurringExpense;
        this.monthID = monthID;
        this.session = session;
        this.oldExpense = oldExpense;
    }
    public String getName(){return name;}
    public Object getValue(){return value;}
    public void setValue(double value){this.value = value;}
    public String getOldCategory(){return oldCategory;}
    public boolean getIsRecurringExpense() {return isRecurringExpense;}
    public int getMonthID(){return monthID;}
    public SessionStorage getSession(){return session;}
    public String getOldExpense(){return oldExpense;}
}
