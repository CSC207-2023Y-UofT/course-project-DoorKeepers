package use_cases.monthly_menu;

import entities.SessionStorage;

/**
 * The input data class for storing Month Menu use case inputs.
 * The controller class of MonthMenu use case creates an object
 * of this class, and pass it to the Month Menu interactor.
 */
public class UpdateViewInputData {
    private final SessionStorage session;
    private final int monthID;

    /**
     * Constructs an UpdateViewInputData holding input data.
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     */
    public UpdateViewInputData(SessionStorage session, int monthID){
        this.session = session;
        this.monthID = monthID;
    }

    /**
     * Gets the session stored.
     * @return SessionStorage input used in interactor
     */
    public SessionStorage getSession() {
        return session;
    }

    /**
     * Gets the monthID stored.
     * @return int ID of MonthlyStorage accessed in interactor
     */
    public int getMonthID() {
        return monthID;
    }
}
