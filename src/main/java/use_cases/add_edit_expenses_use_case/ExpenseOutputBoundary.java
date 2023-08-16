package use_cases.add_edit_expenses_use_case;


/**
 * An interface ExpenseOutputBoundary containing methods implemented in ExpensePresenter.
 */
public interface ExpenseOutputBoundary {
    /**
     * Returns a ExpenseOutputData notifying success.
     *
     * @param expenseOutputData the output data to be returned
     * @return ExpenseOutputData with success message.
     */
    ExpenseOutputData success(ExpenseOutputData expenseOutputData);

    /**
     * Returns a ExpenseOutputData notifying fail.
     *
     * @param Fails the output data to return
     * @return ExpenseOutputData with fail message.
     */
    ExpenseOutputData fail(ExpenseOutputData Fails);
}
