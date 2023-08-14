package use_cases.create_new_month;

import entities.*;

import java.util.ArrayList;

/**
 * The use case interactor class for creating a new MonthlyStorage.
 * This class implements the NewMonthIB interface. The controller
 * class calls this class to get the NewMonthOD object.
 */
public class NewMonthUCI implements NewMonthIB {
    private final NewMonthOB outputBoundary;

    /**
     * Construct a NewMonthUCI.
     * @param outputBoundary NewMonthOB related to using output
     */
    public NewMonthUCI(NewMonthOB outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Pass in and use NewMonthID containing input data to add new MonthlyStorage to session
     * and to create output data.
     * @param input input passed in from the controller class
     * @return NewMonthOD object that contains output data
     */
    @Override
    public NewMonthOD createOutput(NewMonthID input) {
        SessionStorage session = input.getSession();

        // Create and retrieve recurring expenses to new MonthlyStorage
        MonthlyStorage newMonth;
        try {
            newMonth = createNewMonth(session, input.getMonthID(), input.getBudgetValue());
        } catch (EntityException e){
            // Should not occur as recurData will not have expenses of the same name
            return outputBoundary.createOutput(new NewMonthOD(
                    "An error has occurred. Please reload the program.",false));
        }

        // Add MonthlyStorage to session
        try {
            session.addMonth(newMonth);
            return outputBoundary.createOutput(new NewMonthOD(true));
        } catch (EntityException e){
            return outputBoundary.createOutput(new NewMonthOD(
                    "This month already exist in this session.",false));
        }
    }

    /**
     * Create a new MonthlyStorage object.
     * @param session the SessionStorage to store the new MonthlyStorage
     * @param monthID the monthID of the new MonthlyStorage
     * @param budgetValue the budget for the new MonthlyStorage
     * @return new MonthlyStorage object
     * @throws EntityException if an error in the program causes two Expenese in recurData to have the same name
     */
    private MonthlyStorage createNewMonth(SessionStorage session, int monthID, double budgetValue) throws EntityException {
        ArrayList<Expense> recurData= session.getRecurData();
        MonthlyStorage newMonth = new MonthlyStorage(monthID, budgetValue);
        Category other = newMonth.getCategoryData().get(0);
        for (Expense expense : recurData){
            newMonth.addExpense(new Expense(expense.getName(),other, expense.getValue()));
        }
        return newMonth;
    }
}
