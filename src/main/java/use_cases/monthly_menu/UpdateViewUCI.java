package use_cases.monthly_menu;

import entities.EntityException;
import entities.Expense;
import entities.MonthlyStorage;
import entities.SessionStorage;
import views.monthly_menu.MonthMenuP;

import java.util.ArrayList;

/**
 * The UpdateViewUCI
 */
public class UpdateViewUCI {
    private Object[][] expenseList;
    private Object[][] categoryList;

    public UpdateViewUCI(SessionStorage session, int monthID){
        // create expenseList and categoryList
        try{
            MonthlyStorage monthData = session.getMonthlyData(monthID);

            ArrayList<Expense> expenseData = monthData.getExpenseData();
            int expenseNum = expenseData.size();
            Object[][] expenses = new Object[expenseNum][2];
        }
        catch(EntityException e){
            // exception should not occur?
        }
    }

    public void creatOutput(){

    }

}
