package views.main_menu;

import entities.SessionStorage;
import views.session_load.SessionLoadMainMenuVB;

import javax.swing.*;
import java.awt.*;

/**
 * The main menu screen. TODO: It contains a button to save the session, a button
 * to create a new month, and also allows the user to open the month menu for any of the
 * existing months in the current session
 */
public class MainMenuV extends JPanel implements SessionLoadMainMenuVB {
    public MainMenuV() {
        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.setVisible(false);
    }

    /**
     * Makes the main menu visible which displays the session data passed and displays a message in a popup
     * @param message a message to display in a popup after opening the menu
     * @param session the session data to display
     */
    @Override
    public void openMainMenu(String message, SessionStorage session) {
        this.setVisible(true);
        JOptionPane.showMessageDialog(this, message);
        System.out.println(session);
    }
}