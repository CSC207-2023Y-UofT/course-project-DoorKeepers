package use_cases.add_edit_expenses_use_case;

import entities.EntityException;

/**
 * ExpenseIB passes in user input of expense information in the form of ExpenseID.
 * Implemented by ExpenseUCI.
 */

public interface ExpenseIB {
    /**
     * Describes abstract addExpenseInMonth() that is override in ExpenseUCI to format detailed responses for attempts to add an Expense.
     * @param expenseIDAdd ExpenseID required for adding a new expense to the designated monthID MonthlyStorage Object.
     * @return String indicating fail/success add attempt.
     * @throws EntityException thrown when the new  input is invalid.
     */
    ExpenseOD addExpenseInMonth(ExpenseID expenseIDAdd) throws EntityException;

    /**
     * Describes abstract editExpenseInMonth() that is override in ExpenseUCI to format detailed responses for attempts to edit an Expense.
     * @param expenseIDEdit ExpenseID required for editing an existing Expense Object in designated monthID MonthlyStorage Object.
     * @return String indicating fail/success edit attempt.
     * @throws EntityException thrown when the new expense input is invalid.
     */
    ExpenseOD editExpenseInMonth(ExpenseID expenseIDEdit) throws EntityException;
}

