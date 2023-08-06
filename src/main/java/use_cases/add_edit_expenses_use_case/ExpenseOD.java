package use_cases.add_edit_expenses_use_case;

/**
 * ExpenseOD passes a String message (from ExpenseP) resulting from the success or fail add/edit Expense attempt.
 */
public class ExpenseOD {
    String message;

    /**
     * Constructs ExpenseOD by passing a String context-specific message generated in ExpenseUCI.
     * @param message String explaining to user with details to their add/edit Expense attempts
     */
    public ExpenseOD(String message) {
            this.message = message;}

    /**
     * Gets message from ExpenseOD that describes fails/success of the use case.
     * @return String with detailed descriptions about the use case
     */
    public String getMessage() {
        return message;
    }
}
