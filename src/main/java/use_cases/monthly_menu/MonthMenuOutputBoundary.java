package use_cases.monthly_menu;

/**
 * The output boundary interface for creating the Month Menu.
 * The presenter class for creating the Month Menu view
 * implements this interface, and returns MonthMenuOutputData object.
 */
public interface MonthMenuOutputBoundary {

    /**
     * Pass and returns MonthMenuOutputData containing output data.
     *
     * @param output output to be shown in MonthMenuView
     * @return MonthMenuOutputData object that contains output data
     */
    MonthMenuOutputData createOutput(MonthMenuOutputData output);
}
