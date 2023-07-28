package views.session_load;

import use_cases.session_load.SessionLoadException;
import use_cases.session_load.SessionLoadOD;
import views.main_menu.MainMenuV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionLoadMenuV extends JPanel implements SessionLoadMenuVB, ActionListener {
    private final SessionLoadC controller;
    private final MainMenuV mainMenuV;


    public SessionLoadMenuV(SessionLoadC controller, MainMenuV mainMenuV) {
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
     * @param message an error message to display
     */
    @Override
    public void displayError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Closes this menu by setting its visibility to false
     */
    @Override
    public void close() {
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent eve) {
        SessionLoadOD outputData;

        switch (eve.getActionCommand()) {
            case "Create new session":
                outputData = this.controller.loadNew();
                this.close();
                this.mainMenuV.openMainMenu(outputData.getMessage(), outputData.getSession());
                break;
            case "Load session from a file":
                // Implementation of file chooser inspired from
                // https://www.geeksforgeeks.org/java-swing-jfilechooser/
                JFileChooser j = new JFileChooser();
                if (j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        outputData = this.controller.loadFile(j.getSelectedFile().getAbsolutePath());
                        this.close();
                        this.mainMenuV.openMainMenu(outputData.getMessage(), outputData.getSession());
                    } catch (SessionLoadException e) {
                        this.displayError(e.getMessage());
                    }
                }
                break;
        }
    }
}