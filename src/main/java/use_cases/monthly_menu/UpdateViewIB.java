package use_cases.monthly_menu;

/**
 * The input boundary interface for creating the Month Menu.
 * The interactor class for getting the Month Menu output
 * implements this interface, and returns MonthMenuOD object.
 */
public interface UpdateViewIB {

    /**
     * Pass in and use UpdateViewID containing input data to create output data.
     * @param input input passed in from the controller class
     * @return MonthMenuOD object that contains output data
     */
    MonthMenuOD createOutput(UpdateViewID input);
}
