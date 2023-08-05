package views.add_edit_epense_views;


import use_case.add_edit_expenses_use_case.ExpenseOB;
import use_case.add_edit_expenses_use_case.ExpenseOD;
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

