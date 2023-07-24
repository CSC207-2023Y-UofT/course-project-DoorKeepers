package use_cases.monthly_menu;

import entities.*;
import views.monthly_menu.MonthMenuP;

import java.util.ArrayList;

/**
 * The UpdateViewUCI
 */
public class UpdateViewUCI implements UpdateViewIB{
    final MonthMenuOB outputBoundary;

    public UpdateViewUCI(){
        this.outputBoundary = new MonthMenuP();
    }

    public MonthMenuOD creatOutput(UpdateViewID input){
        SessionStorage session = input.getSession();
        int monthID = input.getMonthID();

        try{
            MonthlyStorage monthData = session.getMonthlyData(monthID);

            Object[] expenseData = monthData.getExpenseData().toArray();
            Object[][] expenseList = new Object[expenseData.length][2];
            for (int i=0; i<expenseData.length; i++) {
                Expense expense = (Expense) expenseData[i];
                expenseList[i][0] = expense.getName();
                expenseList[i][1] = expense.getValue();
            }

            Object[] categoryData = monthData.getCategoryData().toArray();
            Object[][] categoryList = new Object[categoryData.length][2];
            for (int i=0; i<categoryData.length; i++) {
                Category category = (Category) categoryData[i];
                categoryList[i][0] = category.getName();
                categoryList[i][1] = category.getBudget();
            }
            return outputBoundary.createSuccessView(new MonthMenuOD(expenseList,categoryList));
        }
        catch(EntityException e){
            return outputBoundary.createFailView("Something went wrong, please try again.");
        }
    }

}
