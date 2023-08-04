package views.create_new_month;

import entities.SessionStorage;
import use_cases.create_new_month.NewMonthIB;
import use_cases.create_new_month.NewMonthID;
import use_cases.create_new_month.NewMonthOD;

/**
 * The controller class for creating new MonthlyStorage. The view class
 * calls this class to get the NewMonthOD object. This class generates the
 * input data class and calls the input boundary interface to get the
 * NewMonthOD object.
 */
public class NewMonthC {
    final NewMonthIB inputBoundary;

    /**
     * Construct the controller class.
     * @param inputBoundary the NewMonthIB containing the method to create new MonthlyStorage
     */
    public NewMonthC(NewMonthIB inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Create and pass the NewMonthID object to the inputBoundary method call.
     * @param session the SessionStorage to store the new MonthlyStorage
     * @param monthID the monthID of the new MonthlyStorage
     * @param budgetValue the budget for the new MonthlyStorage
     * @return NewMonthOD object that contains output data
     */
    NewMonthOD getOutput(SessionStorage session, int monthID, double budgetValue) {
        NewMonthID inputData = new NewMonthID(session, monthID, budgetValue);
        return inputBoundary.createOutput(inputData);
    }
}
