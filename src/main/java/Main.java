import entities.SessionStorage;
import use_cases.main_menu.SessionSaveUseCaseInteractor;
import use_cases.session_load.SessionLoadUseCaseInteractor;
import views.file_session_storage.FileSessionStorage;
import views.main_menu.MainMenuC;
import views.main_menu.MainMenuP;
import views.main_menu.MainMenuV;
import views.session_load.SessionLoadController;
import views.session_load.SessionLoadMenuView;
import views.session_load.SessionLoadPresenter;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window
        JFrame application = new JFrame("Expenditure Tracking");
        application.setMinimumSize(new Dimension(500, 300));
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Program session
        SessionStorage session = new SessionStorage();

        // Building menus
        // Main Menu
        SessionSaveUseCaseInteractor sessionSaveUseCaseInteractor = new SessionSaveUseCaseInteractor(new FileSessionStorage(), new MainMenuP());
        MainMenuC mainMenuController = new MainMenuC(sessionSaveUseCaseInteractor);
        MainMenuV mainMenu = new MainMenuV(mainMenuController);

        // Load Session Menu
        SessionLoadUseCaseInteractor sessionLoadUseCaseInteractor = new SessionLoadUseCaseInteractor(new FileSessionStorage(), new SessionLoadPresenter(), session);
        SessionLoadController sessionLoadController = new SessionLoadController(sessionLoadUseCaseInteractor);
        SessionLoadMenuView sessionLoadMenu = new SessionLoadMenuView(sessionLoadController, mainMenu);

        // Add screens
        screens.add(sessionLoadMenu);
        screens.add(mainMenu);

        // Open program UI
        application.pack();
        application.setVisible(true);
    }
}
