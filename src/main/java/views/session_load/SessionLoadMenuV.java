package views.session_load;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SessionLoadMenuV extends JPanel implements SessionLoadMenuVB, ActionListener {
    private SessionLoadC controller;

    public SessionLoadMenuV() {
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
     * Sets the controller for this View
     * @param controller the controller to be set
     */
    public void setController(SessionLoadC controller) {
        this.controller = controller;
    }

    /**
     * Displays an error message in a popup
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
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Create new session":
                this.controller.loadNew();
                break;
            case "Load session from a file":
                JFileChooser j = new JFileChooser();
                if (j.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    this.controller.loadFile(j.getSelectedFile().getAbsolutePath());
                }
                break;
            default:
                System.out.println(e.getActionCommand());
        }
    }
}