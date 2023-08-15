package use_cases.add_edit_expenses_use_case;

import entities.EntityException;

/**
 * ExpenseInputBoundary passes in user input of expense information in the form of ExpenseInputData.
 * Implemented by ExpenseUseCaseInteractor.
 */

public interface ExpenseInputBoundary {
    /**
     * Describes abstract addExpenseInMonth() that is override in ExpenseUseCaseInteractor to format detailed responses for attempts to add an Expense.
     * @param expenseInputDataAdd ExpenseInputData required for adding a new expense to the designated monthID MonthlyStorage Object.
     * @return String indicating fail/success add attempt.
     * @throws EntityException thrown when the new  input is invalid.
     */
    ExpenseOutputData addExpenseInMonth(ExpenseInputData expenseInputDataAdd) throws EntityException;

    /**
     * Describes abstract editExpenseInMonth() that is override in ExpenseUseCaseInteractor to format detailed responses for attempts to edit an Expense.
     * @param expenseInputDataEdit ExpenseInputData required for editing an existing Expense Object in designated monthID MonthlyStorage Object.
     * @return String indicating fail/success edit attempt.
     * @throws EntityException thrown when the new expense input is invalid.
     */
    ExpenseOutputData editExpenseInMonth(ExpenseInputData expenseInputDataEdit) throws EntityException;
}

