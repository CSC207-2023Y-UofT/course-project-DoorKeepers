package views.add_edit_epense_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_expenses_use_case.ExpenseInputBoundary;
import use_cases.add_edit_expenses_use_case.ExpenseInputData;
import use_cases.add_edit_expenses_use_case.ExpenseOutputData;


public class ExpenseController {
    /**
     * Expense_Controller passes in user input and produces an Expense_Output_Data Object according to a use case.
     */
    final ExpenseInputBoundary input;

    /**
     * Constructs ExpenseController with a ExpenseInputBoundary.
     * @param ExpenseInputBoundary a ExpenseInputBoundary.
     */
    public ExpenseController(ExpenseInputBoundary ExpenseInputBoundary){
        this.input = ExpenseInputBoundary;
    }

    /**
     * Returns ExpenseOutputData Object when the expense is successfully edited to the designated month with monthId in the working session.
     * @param name user input String expense name
     * @param value user input Object expense budget
     * @param monthID int representing current month
     * @param session SessionStorage current session
     * @param oldExpense Expense existing expense selected by user
     * @return Expense_OD for success edit
     * @throws EntityException thrown when edit expense attempt fails.
     */
    public ExpenseOutputData expenseInMonth(String name, Object value, String category, boolean isRecurring, int monthID, SessionStorage session, String oldExpense) throws EntityException {
        ExpenseInputData expenseInputData = new ExpenseInputData(name, value, category, isRecurring, monthID, session, oldExpense);
        if(oldExpense == null){
            return input.addExpenseInMonth(expenseInputData);
        }
        else {
            return input.editExpenseInMonth(expenseInputData);}
    }
}
