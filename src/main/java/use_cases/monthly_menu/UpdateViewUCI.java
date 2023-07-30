package use_cases.monthly_menu;

import entities.*;

import java.util.ArrayList;

/**
 * The use case interactor class for creating the Month Menu output.
 * This class implements the UpdateViewIB interface. The controller
 * class calls this class to get the MonthMenuOD object.
 */
public class UpdateViewUCI implements UpdateViewIB{
    final MonthMenuOB outputBoundary;

    public UpdateViewUCI(MonthMenuOB outputBoundary){
        this.outputBoundary = outputBoundary;
    }

    /**
     * Pass in and use UpdateViewID containing input data to create output data.
     * @param input input passed in from the controller class
     * @return MonthMenuOD object that contains output data
     */
    @Override
    public MonthMenuOD createOutput(UpdateViewID input){
        // Get input data
        SessionStorage session = input.getSession();
        int monthID = input.getMonthID();

        try{
            MonthlyStorage monthData = session.getMonthlyData(monthID); // throws EntityException
            ArrayList<Expense> expenseData= monthData.getExpenseData();
            ArrayList<Category> categoryData= monthData.getCategoryData();

            return outputBoundary.createOutput(new MonthMenuOD(expenseData,categoryData));
        }
        catch(EntityException e){ //set String warning as output if EntityException is caught
            return outputBoundary.createOutput(new MonthMenuOD("Something went wrong, please try again."));
        }
    }
}
