package use_cases.monthly_menu;

/**
 * The output boundary interface for creating the Month Menu.
 * The presenter class for creating the Month Menu view
 * implements this interface, and returns MonthMenuOD object.
 */
public interface MonthMenuOB {

    /**
     * Pass and returns MonthMenuOD containing output data.
     * @param output output to be shown in MonthMenuView
     */
    MonthMenuOD createOutput(MonthMenuOD output);
}
