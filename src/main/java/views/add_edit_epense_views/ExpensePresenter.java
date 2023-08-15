package views.add_edit_epense_views;


import use_cases.add_edit_expenses_use_case.ExpenseOutputBoundary;
import use_cases.add_edit_expenses_use_case.ExpenseOutputData;

/**
 * ExpensePresenter notifies success/fail to add or edit expense, implements ExpenseOutputBoundary.
 * Returns ExpenseOutputData when use case is finished.
 */
public class ExpensePresenter implements ExpenseOutputBoundary {
    /**
     * Returns a ExpenseOutputData notifying success.
     * @return ExpenseOutputData with success message.
     */
    @Override
    public ExpenseOutputData success(ExpenseOutputData expenseOutputData) {
        return expenseOutputData;
    }
    /**
     * Returns a ExpenseOutputData notifying fail.
     * @return ExpenseOutputData with fail message.
     */
    @Override
    public ExpenseOutputData fail(ExpenseOutputData Fails) {
        return Fails;
    }
}

