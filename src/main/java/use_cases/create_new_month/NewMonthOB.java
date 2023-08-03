package use_cases.create_new_month;

/**
 * The output boundary interface for creating a mew MonthlyStorage.
 * The presenter class for the Create New Month use case
 * implements this interface, and returns NewMonthOD object.
 */
public interface NewMonthOB {

    /**
     * Pass and returns MonthMenuOD containing output data.
     * @param output output to be used in NewMonthV to open MonthMenuV
     * @return NewMonthOD object that contains output data
     */
    NewMonthOD createOutput(NewMonthOD output);
}
