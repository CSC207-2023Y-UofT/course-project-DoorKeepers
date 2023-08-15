package use_cases.monthly_menu;

/**
 * The input boundary interface for creating the Month Menu.
 * The interactor class for getting the Month Menu output
 * implements this interface, and returns MonthMenuOutputData object.
 */
public interface UpdateViewInputBoundary {

    /**
     * Pass in and use UpdateViewInputData containing input data to create output data.
     * @param input input passed in from the controller class
     * @return MonthMenuOutputData object that contains output data
     */
    MonthMenuOutputData createOutput(UpdateViewInputData input);
}
