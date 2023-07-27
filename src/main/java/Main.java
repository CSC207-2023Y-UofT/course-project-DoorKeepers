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
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        // Program session
        SessionStorage session = new SessionStorage();

        // Building menu views
        SessionLoadMenuV sessionLoadMenu = new SessionLoadMenuV();
        MainMenuV mainMenuV = new MainMenuV();

        // Create and set controllers
        sessionLoadMenu.setController(new SessionLoadC(new SessionLoadUCI(new FileSessionStorage(), new SessionLoadP(sessionLoadMenu, mainMenuV), session)));

        // Add screens
        screens.add(sessionLoadMenu);
        screens.add(mainMenuV);

        // Open program UI
        application.pack();
        application.setVisible(true);
    }
}
