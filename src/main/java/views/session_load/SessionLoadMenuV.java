package views.session_load;

import entities.SessionStorage;
import use_cases.session_load.SessionLoadException;
import use_cases.session_load.SessionLoadOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The session load menu screen. It contains two buttons, one for creating and loading a new empty
 * session, and one for loading an existing session from a file. It reports any errors through a popup
 * and sends the user to the main menu after successfully loading a file.
 */
public class SessionLoadMenuV extends JPanel implements ActionListener {
    private final SessionLoadC controller;
    private final SessionLoadMainMenuVB mainMenuV;


    public SessionLoadMenuV(SessionLoadC controller, SessionLoadMainMenuVB mainMenuV) {
        this.controller = controller;
        this.mainMenuV = mainMenuV;

        JLabel title = new JLabel("Load session");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        JButton load_new = new JButton("Create new session");
        JButton load_file = new JButton("Load session from a file");

        load_new.addActionListener(this);
        load_file.addActionListener(this);

        buttons.add(load_new);
        buttons.add(load_file);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    /**
     * Displays an error message in a popup
     *
     * @param message a String containing an error message to display
     */
    private void displayError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Closes this menu by setting its visibility to false and opens the main menu
     *
     * @param message a String containing a success message to display
     * @param session a SessionStorage object with the loaded session that will be displayed in the main menu
     */
    private void displaySuccess(String message, SessionStorage session) {
        this.setVisible(false);
        this.mainMenuV.openMainMenu(message, session);
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        SessionLoadOD outputData;

        switch (eve.getActionCommand()) {
            case "Create new session":
                outputData = this.controller.loadNew();
                this.displaySuccess(outputData.getMessage(), outputData.getSession());
                break;
            case "Load session from a file":
                // Implementation of file chooser inspired from
                // https://www.geeksforgeeks.org/java-swing-jfilechooser/
                JFileChooser j = new JFileChooser();
                if (j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        outputData = this.controller.loadFile(j.getSelectedFile().getAbsolutePath());
                        this.displaySuccess(outputData.getMessage(), outputData.getSession());
                    } catch (SessionLoadException e) {
                        this.displayError(e.getMessage());
                    }
                }
                break;
        }
    }
}