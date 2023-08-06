package use_cases.add_edit_expenses_use_case;


/**
 * An interface ExpenseOB containing methods implemented in ExpenseP.
 */
public interface ExpenseOB {
    /**
     * Returns a ExpenseOD notifying success.
     * @return ExpenseOD with success message.
     */
    ExpenseOD success(ExpenseOD expenseOD);

    /**
     * Returns a ExpenseOD notifying fail.
     * @return ExpenseOD with fail message.
     */
    ExpenseOD fail(ExpenseOD Fails);
}
