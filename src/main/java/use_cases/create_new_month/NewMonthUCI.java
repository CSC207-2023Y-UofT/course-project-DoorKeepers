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
        int budgetValue = input.getBudgetValue();

        try {
            ArrayList<Expense> recurData= session.getRecurData();
            MonthlyStorage newMonth = new MonthlyStorage(monthID, budgetValue);
            Category other = newMonth.getCategoryData().get(0);
            for (Expense expense : recurData){
                newMonth.addExpense(new Expense(expense.getName(),other, expense.getValue()));
            }
            //TODO: make sure new month has different ID from already stored months
            session.addMonth(newMonth);
            return outputBoundary.createOutput(new NewMonthOD(true));
        } catch (EntityException e){
            return outputBoundary.createOutput(new NewMonthOD(
                    "Something went wrong, please try again.",false));
        }
    }
}
