package use_cases.generate_summary_use_case;

import entities.SessionStorage;

/**
 * A class that holds the input data for use by the interactor. This includes a SessionStorage object pertaining to the
 * current session and the monthID of the current month.
 */
public class GenerateSummaryInputData {

    private final SessionStorage currentSession;
    private final int monthID;

    /**
     * Creates a new instance of GenerateSummaryInputData.
     *
     * @param currentSession a SessionStorage object containing all information about the current session
     * @param monthID        an int corresponding to the monthID of the current month
     */
    public GenerateSummaryInputData(SessionStorage currentSession, int monthID) {
        this.currentSession = currentSession;
        this.monthID = monthID;
    }

    /**
     * Gets the monthID of this GenerateSummaryInputData.
     *
     * @return the monthID of this GenerateSummaryInputData
     */
    public int getMonthID() {
        return this.monthID;
    }

    /**
     * Gets the currentSession of this GenerateSummaryInputData.
     *
     * @return the currentSession of this GenerateSummaryInputData
     */
    public SessionStorage getCurrentSession() {
        return this.currentSession;
    }
}
