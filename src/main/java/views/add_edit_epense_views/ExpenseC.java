package views.add_edit_epense_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_expenses_use_case.ExpenseIB;
import use_cases.add_edit_expenses_use_case.ExpenseID;
import use_cases.add_edit_expenses_use_case.ExpenseOD;


public class ExpenseC {
    /**
     * Expense_Controller passes in user input and produces an Expense_Output_Data Object according to a use case.
     */
    final ExpenseIB input;

    /**
     * Constructs ExpenseC with a ExpenseIB.
     * @param ExpenseIB a ExpenseIB.
     */
    public ExpenseC(ExpenseIB ExpenseIB){
        this.input = ExpenseIB;
    }

    /**
     * Returns ExpenseOD Object when the expense is successfully edited to the designated month with monthId in the working session.
     * @param name user input String expense name
     * @param value user input Object expense budget
     * @param monthID int representing current month
     * @param session SessionStorage current session
     * @param oldExpense Expense existing expense selected by user
     * @return Expense_OD for success edit
     * @throws EntityException thrown when edit expense attempt fails.
     */
    public ExpenseOD expenseInMonth(String name, Object value, String category, boolean isRecurring, int monthID, SessionStorage session, String oldExpense) throws EntityException {
        ExpenseID expenseID = new ExpenseID(name, value, category, isRecurring, monthID, session, oldExpense);
        if(oldExpense == null){
            return input.addExpenseInMonth(expenseID);
        }
        else {
            return input.editExpenseInMonth(expenseID);}
    }
}
