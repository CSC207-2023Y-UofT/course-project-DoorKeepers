import entities.SessionStorage;
import use_cases.session_load.SessionLoadUCI;
import views.file_session_storage.FileSessionStorage;
import views.main_menu.MainMenuV;
import views.session_load.SessionLoadC;
import views.session_load.SessionLoadP;
import views.session_load.SessionLoadMenuV;

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
        MainMenuV mainMenu = new MainMenuV();

        // Load Session Menu
        SessionLoadUCI sessionLoadUCI = new SessionLoadUCI(new FileSessionStorage(), new SessionLoadP(), session);
        SessionLoadC sessionLoadController = new SessionLoadC(sessionLoadUCI);
        SessionLoadMenuV sessionLoadMenu = new SessionLoadMenuV(sessionLoadController, mainMenu);

        // Add screens
        screens.add(sessionLoadMenu);
        screens.add(mainMenu);

        // Open program UI
        application.pack();
        application.setVisible(true);
    }
}
