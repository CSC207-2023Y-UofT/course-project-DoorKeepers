package use_cases.add_edit_expenses_use_case;

import entities.SessionStorage;

/**
 * ExpenseInputData contains all user inputs.
 * Created in ExpenseController and used in ExpenseUseCaseInteractor to perform addExpenseInMonth() and editExpenseInMonth().
 */
public class ExpenseInputData {
    private final String name;
    private final Object value;
    private final String category;
    private final boolean isRecurringExpense;
    private final int monthID;
    private final SessionStorage session;
    private final String oldExpense;

    /**
     * Constructs ExpenseInputData for adding/editing an existing Expense Object.
     *
     * @param name               Expense name
     * @param value              Expense budget
     * @param category           String Category name representing an existing Category in which the Expense belongs to.
     * @param isRecurringExpense boolean indicating whether an Expense is recurring.
     * @param monthID            An int representing the month which the Expense Object belongs to.
     * @param session            The current session which the MonthlyStorage Object belongs to.
     * @param oldExpense         String Expense name representing an existing Expense in the MonthlyStorage Object the user wish to edit.
     */

    public ExpenseInputData(String name, Object value, String category, boolean isRecurringExpense, int monthID, SessionStorage session, String oldExpense) {
        this.name = name;
        this.value = value;
        this.category = category;
        this.isRecurringExpense = isRecurringExpense;
        this.monthID = monthID;
        this.session = session;
        this.oldExpense = oldExpense;
    }

    /**
     * Gets String Expense name.
     *
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Object Expense value.
     *
     * @return Object value from user input
     */
    public Object getValue() {
        return value;
    }

    /**
     * Gets String Category name that this Expense belongs to (before add/edit expense occurs).
     *
     * @return String Category name associated with the Expense in this use case.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets boolean indicating if this Expense is a recurring expense.
     *
     * @return boolean isRecurring whether user checks the isRecurring checkbox in AddExpenseView/EditExpenseView
     */
    public boolean getIsRecurringExpense() {
        return isRecurringExpense;
    }

    /**
     * Gets int representing current month in working session.
     *
     * @return int monthID passed in from previous feature.
     */
    public int getMonthID() {
        return monthID;
    }

    /**
     * Gets SessionStorage of the current session.
     *
     * @return SessionStorage passed in from previous feature.
     */
    public SessionStorage getSession() {
        return session;
    }

    /**
     * Gets String old Expense name when a user tries to edit the expense (assigned null for add Expense use case, occurs in AddExpenseView).
     *
     * @return String Expense name of the old expense a user selected to edit.
     * (Or @return null)
     */
    public String getOldExpense() {
        return oldExpense;
    }
}
