package views.generate_summary_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.generate_summary_use_case.GenerateSummaryInputBoundary;
import use_cases.generate_summary_use_case.GenerateSummaryInputData;
import use_cases.generate_summary_use_case.GenerateSummaryOutputData;

/**
 * A class that creates the GenerateSummaryInputData input data and calls the GenerateSummaryUseCaseInteractor interactor's method to
 * generate the graphical summary.
 */
public class GenerateSummaryController {

    final GenerateSummaryInputBoundary interactor;

    /**
     * Creates a new GenerateSummaryController with the provided interactor.
     * @param interactor instance of interactor that will be called in the generate method
     */
    public GenerateSummaryController(GenerateSummaryInputBoundary interactor) {
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
    public GenerateSummaryOutputData generate(SessionStorage currentSession, int monthID) throws EntityException {
        GenerateSummaryInputData inputData = new GenerateSummaryInputData(currentSession, monthID);
        return interactor.generateNewSummary(inputData);
    }
}
