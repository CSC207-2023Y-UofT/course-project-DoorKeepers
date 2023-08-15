package use_cases.add_edit_expenses_use_case;

/**
 * ExpenseOutputData passes a String message (from ExpensePresenter) resulting from the success or fail add/edit Expense attempt.
 */
public class ExpenseOutputData {
    private final String message;

    /**
     * Constructs ExpenseOutputData by passing a String context-specific message generated in ExpenseUseCaseInteractor.
     * @param message String explaining to user with details to their add/edit Expense attempts
     */
    public ExpenseOutputData(String message) {
            this.message = message;}

    /**
     * Gets message from ExpenseOutputData that describes fails/success of the use case.
     * @return String with detailed descriptions about the use case
     */
    public String getMessage() {
        return message;
    }
}
