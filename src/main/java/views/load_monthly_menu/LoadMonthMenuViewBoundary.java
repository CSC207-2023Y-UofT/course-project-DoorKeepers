package views.load_monthly_menu;

import entities.SessionStorage;

public interface LoadMonthMenuViewBoundary {
    /**
     * Load Month Menu and notify user if opening Month Menu of a new MonthlyStorage created.
     *
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     * @param message notify user when new MonthlyStorage is created, otherwise null
     */
    void loadMonthMenu(SessionStorage session, int monthID, String message);
}
