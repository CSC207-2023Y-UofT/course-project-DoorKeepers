package views.generate_summary_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.generate_summary_use_case.GenerateSummaryIB;
import use_cases.generate_summary_use_case.GenerateSummaryID;
import use_cases.generate_summary_use_case.GenerateSummaryOD;

/**
 * A class that creates the GenerateSummaryID input data and calls the GenerateSummaryUCI interactor's method to
 * generate the graphical summary.
 */
public class GenerateSummaryC {

    final GenerateSummaryIB interactor;

    /**
     * Creates a new GenerateSummaryC with the provided interactor.
     * @param interactor instance of interactor that will be called in the generate method
     */
    public GenerateSummaryC(GenerateSummaryIB interactor) {
        this.interactor = interactor;
    }

    /**
     * Creates an input data object and calls the interactor's generateNewSummary method to generate the graphical
     * summary.
     * @param currentSession a SessionStorage object containing all information about the current session
     * @param monthID an int corresponding to the monthID of the current month
     * @return a JPanel holding the graphs
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     * raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     * SessionStorage, and is not something the user can fix.
     */
    public GenerateSummaryOD generate(SessionStorage currentSession, int monthID) throws EntityException {
        GenerateSummaryID inputData = new GenerateSummaryID(currentSession, monthID);
        return interactor.generateNewSummary(inputData);
    }
}
