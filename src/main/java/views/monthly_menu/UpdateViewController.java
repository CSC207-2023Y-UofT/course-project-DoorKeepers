package views.monthly_menu;

import entities.SessionStorage;
import use_cases.monthly_menu.MonthMenuOutputData;
import use_cases.monthly_menu.UpdateViewInputBoundary;
import use_cases.monthly_menu.UpdateViewInputData;

/**
 * The controller class for getting the Month Menu output. The view class
 * calls this class to get the MonthMenuOutputData object. This class generates the
 * input data class and calls the input boundary interface to get the
 * MonthMenuOutputData object.
 */
public class UpdateViewController {
    final UpdateViewInputBoundary inputBoundary;

    /**
     * Construct the controller class.
     *
     * @param inputBoundary the UpdateViewInputBoundary containing the method to create output data
     */
    public UpdateViewController(UpdateViewInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Create and pass the UpdateViewInputData object to the inputBoundary method call.
     *
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     * @return MonthMenuOutputData object that contains output data
     */
    MonthMenuOutputData getOutput(SessionStorage session, int monthID) {
        UpdateViewInputData inputData = new UpdateViewInputData(session, monthID);
        return inputBoundary.createOutput(inputData);
    }
}
