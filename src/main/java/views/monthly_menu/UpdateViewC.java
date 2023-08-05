package views.monthly_menu;

import entities.SessionStorage;
import use_cases.monthly_menu.MonthMenuOD;
import use_cases.monthly_menu.UpdateViewIB;
import use_cases.monthly_menu.UpdateViewID;

/**
 * The controller class for getting the Month Menu output. The view class
 * calls this class to get the MonthMenuOD object. This class generates the
 * input data class and calls the input boundary interface to get the
 * MonthMenuOD object.
 */
public class UpdateViewC {
    final UpdateViewIB inputBoundary;

    /**
     * Construct the controller class.
     * @param inputBoundary the UpdateViewIB containing the method to create output data
     */
    public UpdateViewC(UpdateViewIB inputBoundary){
        this.inputBoundary = inputBoundary;
    }

    /**
     * Create and pass the UpdateViewID object to the inputBoundary method call.
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     * @return MonthMenuOD object that contains output data
     */
    MonthMenuOD getOutput(SessionStorage session, int monthID){
        UpdateViewID inputData = new UpdateViewID(session, monthID);
        return inputBoundary.createOutput(inputData);
    }
}
