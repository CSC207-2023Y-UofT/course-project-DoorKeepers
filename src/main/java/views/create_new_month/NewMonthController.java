package views.create_new_month;

import entities.SessionStorage;
import use_cases.create_new_month.NewMonthInputBoundary;
import use_cases.create_new_month.NewMonthInputData;
import use_cases.create_new_month.NewMonthOutputData;

/**
 * The controller class for creating new MonthlyStorage. The view class
 * calls this class to get the NewMonthOutputData object. This class generates the
 * input data class and calls the input boundary interface to get the
 * NewMonthOutputData object.
 */
public class NewMonthController {
    private final NewMonthInputBoundary inputBoundary;

    /**
     * Construct the controller class.
     * @param inputBoundary the NewMonthInputBoundary containing the method to create new MonthlyStorage
     */
    public NewMonthController(NewMonthInputBoundary inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Create and pass the NewMonthInputData object to the inputBoundary method call.
     * @param session the SessionStorage to store the new MonthlyStorage
     * @param monthID the monthID of the new MonthlyStorage
     * @param budgetValue the budget for the new MonthlyStorage
     * @return NewMonthOutputData object that contains output data
     */
    NewMonthOutputData getOutput(SessionStorage session, int monthID, double budgetValue) {
        NewMonthInputData inputData = new NewMonthInputData(session, monthID, budgetValue);
        return inputBoundary.createOutput(inputData);
    }
}
