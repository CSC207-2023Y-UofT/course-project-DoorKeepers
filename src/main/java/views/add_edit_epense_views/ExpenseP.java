package views.add_edit_epense_views;


import use_cases.add_edit_expenses_use_case.ExpenseOB;
import use_cases.add_edit_expenses_use_case.ExpenseOD;

/**
 * ExpenseP notifies success/fail to add or edit expense, implements ExpenseOB.
 * Returns ExpenseOD when use case is finished.
 */
public class ExpenseP implements ExpenseOB {
    /**
     * Returns a ExpenseOD notifying success.
     * @return ExpenseOD with success message.
     */
    @Override
    public ExpenseOD success(ExpenseOD expenseOD) {
        return expenseOD;
    }
    /**
     * Returns a ExpenseOD notifying fail.
     * @return ExpenseOD with fail message.
     */
    @Override
    public ExpenseOD fail(ExpenseOD Fails) {
        return Fails;
    }
}
