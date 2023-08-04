package use_cases.create_new_month;

import entities.*;

import java.util.ArrayList;

/**
 * The use case interactor class for creating a new MonthlyStorage.
 * This class implements the NewMonthIB interface. The controller
 * class calls this class to get the NewMonthOD object.
 */
public class NewMonthUCI implements NewMonthIB {
    final NewMonthOB outputBoundary;

    /**
     * Construct a NewMonthUCI.
     * @param outputBoundary NewMonthOB related to using output
     */
    public NewMonthUCI(NewMonthOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Pass in and use NewMonthID containing input data to create output data.
     * @param input input passed in from the controller class
     * @return NewMonthOD object that contains output data
     */
    @Override
    public NewMonthOD createOutput(NewMonthID input) {
        SessionStorage session = input.getSession();
        int monthID = input.getMonthID();
        double budgetValue = input.getBudgetValue();

        // Check that no MonthlyStorage in this session has the same monthID
        if (checkMonthInSession(session, monthID)){
            return outputBoundary.createOutput(new NewMonthOD(
                    "This month already exist in this session.",false));
        }

        // Create new MonthlyStorage, retrieve recurring expenses, and add MonthlyStorage to session
        try {
            ArrayList<Expense> recurData= session.getRecurData();
            MonthlyStorage newMonth = new MonthlyStorage(monthID, budgetValue);
            Category other = newMonth.getCategoryData().get(0);
            for (Expense expense : recurData){
                newMonth.addExpense(new Expense(expense.getName(),other, expense.getValue()));
            }
            session.addMonth(newMonth);
            return outputBoundary.createOutput(new NewMonthOD(true));
        } catch (EntityException e){
            // Should not occur as recurData will not have expenses of the same name
            return outputBoundary.createOutput(new NewMonthOD(
                    "An error has occurred. Please reload the program.",false));
        }
    }

    private static boolean checkMonthInSession(SessionStorage session, int monthID){
        ArrayList<MonthlyStorage> allMonthArrayList = session.getAllMonthlyData();
        MonthlyStorage[] allMonthArray = new MonthlyStorage[allMonthArrayList.size()];
        allMonthArrayList.toArray(allMonthArray);

        for (MonthlyStorage monthlyStorage : allMonthArray){
            if (monthlyStorage.getMonthID()==monthID){
                return true;
            }
        }
        return false;
    }
}
