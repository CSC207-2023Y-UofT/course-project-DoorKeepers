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

    public MonthMenuOD createOutput(UpdateViewID input){
        SessionStorage session = input.getSession();
        int monthID = input.getMonthID();

        try{
            MonthlyStorage monthData = session.getMonthlyData(monthID);
            ArrayList<Expense> expenseData= monthData.getExpenseData();
            ArrayList<Category> categoryData= monthData.getCategoryData();

            return outputBoundary.createOutput(new MonthMenuOD(expenseData,categoryData));
        }
        catch(EntityException e){
            return outputBoundary.createOutput(new MonthMenuOD("Something went wrong, please try again."));
        }
    }
}
