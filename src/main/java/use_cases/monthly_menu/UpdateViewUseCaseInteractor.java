package use_cases.monthly_menu;

import entities.*;

import java.util.ArrayList;

/**
 * The use case interactor class for creating the Month Menu output.
 * This class implements the UpdateViewInputBoundary interface. The controller
 * class calls this class to get the MonthMenuOutputData object.
 */
public class UpdateViewUseCaseInteractor implements UpdateViewInputBoundary {
    private final MonthMenuOutputBoundary outputBoundary;

    /**
     * Construct a UpdateViewUseCaseInteractor.
     *
     * @param outputBoundary MonthMenuOutputBoundary related to using output
     */
    public UpdateViewUseCaseInteractor(MonthMenuOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Pass in and use UpdateViewInputData containing input data to create output data.
     *
     * @param input input passed in from the controller class
     * @return MonthMenuOutputData object that contains output data
     */
    @Override
    public MonthMenuOutputData createOutput(UpdateViewInputData input) {
        // Get input data
        SessionStorage session = input.getSession();
        int monthID = input.getMonthID();

        try {
            MonthlyStorage monthData = session.getMonthlyData(monthID); // throws EntityException
            ArrayList<Expense> expenseData = monthData.getExpenseData();
            ArrayList<Category> categoryData = monthData.getCategoryData();
            double monthlyBudget = monthData.getMonthlyBudget();

            return outputBoundary.createOutput(new MonthMenuOutputData(expenseData, categoryData, monthlyBudget, true));
        } catch (EntityException e) { //set String warning as output if EntityException is caught
            return outputBoundary.createOutput(new MonthMenuOutputData(
                    "An error has occurred. Please reload the program.", false));
        }
    }
}
