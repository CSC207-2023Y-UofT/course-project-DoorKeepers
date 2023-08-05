package use_cases.create_new_month;

/**
 * The output data class for storing view output. The interactor
 * class for creating a new MonthlyStorage creates an object of
 * this class, and pass it into the output boundary. The output
 * boundary returns this type of object, and is then formatted
 * and used in the Create New Month view.
 */
public class NewMonthOD {
    private String warning;
    private final boolean successful;

    /**
     * Constructs a MonthMenuOD holding output data.
     * @param successful boolean indicating whether create new MonthlyStorage was successful
     */
    public NewMonthOD(boolean successful) {
        this.successful = successful;
    }

    /**
     * Constructs an NewMonthOD holding error message.
     * @param warning error message to show in the view
     * @param successful boolean indicating whether create new MonthlyStorage was successful
     */
    public NewMonthOD(String warning, boolean successful){
        this.warning = warning;
        this.successful = successful;
    }

    /**
     * Gets the error message stored.
     * @return warning message to be outputted
     */
    public String getWarning() {
        return warning;
    }

    /**
     * Indicates if new MonthlyStorage is created and added to SessionStorage.
     * If false, NewMonthOD also stores a warning message.
     * @return true if new MonthlyStorage is created and added to SessionStorage
     */
    public boolean isSuccessful() {
        return successful;
    }
}
