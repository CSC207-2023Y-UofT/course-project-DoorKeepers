package use_cases.generate_summary_use_case;

import entities.EntityException;

/**
 * An interface that the GenerateSummaryUseCaseInteractor interactor implements to maintain the dependency inversion principle. It
 * contains the method used to generate a new graphical summary.
 */
public interface GenerateSummaryInputBoundary {

    /**
     * Generates the information needed for the graphical representation of the MonthlyStorage data associated with
     * monthID in the currentSession.
     *
     * @param inputData a GenerateSummaryInputData object holding the SessionStorage and monthID needed to retrieve data
     * @return a GenerateSummaryOutputData object holding the information needed to plot the graphs.
     * @throws EntityException if there is no MonthID corresponding to a month in a monthly storage. This error being
     *                         raised is a sign that there is something broken in the way that the MonthlyStorage objects are stored in the
     *                         SessionStorage, and is not something the user can fix.
     */
    GenerateSummaryOutputData generateNewSummary(GenerateSummaryInputData inputData) throws EntityException;
}
