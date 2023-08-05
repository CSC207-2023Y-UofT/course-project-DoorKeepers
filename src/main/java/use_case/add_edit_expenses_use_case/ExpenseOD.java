package use_case.add_edit_expenses_use_case;

/**
 * ExpenseOD passes a String message (from ExpenseP) resulting from the success or fail add/edit Expense attempt.
 */
public class ExpenseOD {
    String message;


    public ExpenseOD(String message) {
            this.message = message;}
    public String getMessage() {
        return message;
    }
}
