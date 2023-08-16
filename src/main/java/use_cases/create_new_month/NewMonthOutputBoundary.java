package use_cases.create_new_month;

/**
 * The output boundary interface for creating a mew MonthlyStorage.
 * The presenter class for the Create New Month use case
 * implements this interface, and returns NewMonthOutputData object.
 */
public interface NewMonthOutputBoundary {

    /**
     * Pass in and returns MonthMenuOutputData containing output data.
     *
     * @param output output to be used in NewMonthView to open MonthMenuView
     * @return NewMonthOutputData object that contains output data
     */
    NewMonthOutputData createOutput(NewMonthOutputData output);
}
