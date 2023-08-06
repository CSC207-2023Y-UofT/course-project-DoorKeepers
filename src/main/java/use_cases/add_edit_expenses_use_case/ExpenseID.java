package use_cases.add_edit_expenses_use_case;

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

    /**
     * Gets String Expense name.
     * @return String name
     */
    public String getName(){return name;}

    /**
     * Gets Object Expense value.
     * @return Object value from user input
     */
    public Object getValue(){return value;}

    /**
     * Sets Double Expense value. (Casting occurs in ExpenseUCI)
     * @param value double passed in from ExpenseUCI to cast user input into a valid Expense attribute.
     */
    public void setValue(double value){this.value = value;}

    /**
     * Gets String Category name that this Expense belongs to (before add/edit expense occurs).
     * @return String Category name associated with the Expense in this use case.
     */
    public String getOldCategory(){return oldCategory;}

    /**
     * Gets boolean indicating if this Expense is a recurring expense.
     * @return boolean isRecurring whether user checks the isRecurring checkbox in AddExpenseV/EditExpenseV
     */
    public boolean getIsRecurringExpense() {return isRecurringExpense;}

    /**
     * Gets int representing current month in working session.
     * @return int monthID passed in from previous feature.
     */
    public int getMonthID(){return monthID;}

    /**
     * Gets SessionStorage of the current session.
     * @return SessionStorage passed in from previous feature.
     */
    public SessionStorage getSession(){return session;}

    /**
     * Gets String old Expense name when a user tries to edit the expense (assigned null for add Expense use case, occurs in AddExpenseV).
     * @return String Expense name of the old expense a user selected to edit.
     * (Or @return null)
     */
    public String getOldExpense(){return oldExpense;}
}
