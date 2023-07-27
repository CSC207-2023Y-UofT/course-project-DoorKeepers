package views.main_menu;

import entities.SessionStorage;
import views.session_load.SessionLoadMainMenuVB;

import javax.swing.*;
import java.awt.*;

public class MainMenuV extends JPanel implements SessionLoadMainMenuVB {
    public MainMenuV() {
        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.setVisible(false);
    }

    /**
     * Makes the main menu visible which displays the session data passed
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