package use_case.add_edit_expenses_use_case;

import entities.EntityException;

/**
 * ExpenseIB passes in user input of expense information in the form of ExpenseID.
 */
public interface ExpenseIB {
    /**
     * Returns String success message in the form of a ExpenseOD object when an expense is successfully added,
     * returns String fail messages that are context specific when failed.
     * @param expenseIDAdd ExpenseID required for adding a new expense to the designated monthID MonthlyStorage Object.
     * @return String indicating fail/success add attempt.
     * @throws EntityException thrown when the new  input is invalid.
     */
    ExpenseOD addExpenseInMonth(ExpenseID expenseIDAdd) throws EntityException;

    /**
     * Returns String success message in the form of a ExpenseOD object when an expense is successfully edited,
     * returns String fail messages that are context specific when failed.
     * @param expenseIDEdit ExpenseID required for editing an existing Expense Object in designated monthID MonthlyStorage Object.
     * @return String indicating fail/success edit attempt.
     * @throws EntityException thrown when the new expense input is invalid.
     */
    ExpenseOD editExpenseInMonth(ExpenseID expenseIDEdit) throws EntityException;
}

