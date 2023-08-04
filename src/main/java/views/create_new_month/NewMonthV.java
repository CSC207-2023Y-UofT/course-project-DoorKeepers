package views.create_new_month;

import entities.SessionStorage;
import use_cases.create_new_month.NewMonthOD;
import use_cases.monthly_menu.MonthMenuOB;
import use_cases.monthly_menu.UpdateViewIB;
import use_cases.monthly_menu.UpdateViewUCI;
import views.load_monthly_menu.LoadMonthMenuVB;
import views.monthly_menu.MonthMenuP;
import views.monthly_menu.MonthMenuV;
import views.monthly_menu.UpdateViewC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The view class for creating new MonthlyStorage. This class implements the
 * ActionListener interface. This class calls the controller class to get the
 * NewMonthOD object, and use the output to set up success and fail view.
 */
public class NewMonthV implements ActionListener, LoadMonthMenuVB {
    NewMonthC controller;
    SessionStorage session;
    JFrame frame = new JFrame("Creat a new Month");
    JTextField year = new JTextField(9);
    JComboBox<String> month = new JComboBox<>(new String[]{"","January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"});
    JTextField budget = new JTextField(9);
    JButton submitButton = new JButton("Submit");
    int selectedMonth = 0;


    /**
     * Set up the window for user input. User is required to input the year,
     * select the month, and input the budget for the month.
     * Code inspired from <a href="https://youtu.be/Kmgo00avvEw">here</a>.
     * @param controller the controller class to get output data
     * @param session the SessionStorage to store the new MonthlyStorage
     */
    public NewMonthV (NewMonthC controller, SessionStorage session){
        this.controller = controller;
        this.session = session;

        // Add action listener to drop down and button
        month.addActionListener(this);
        submitButton.addActionListener(this);

        // Create layout
        JPanel layout = new JPanel();
        layout.setLayout(new BoxLayout(layout, BoxLayout.Y_AXIS));

        // Create components
        JPanel yearMonthPanel = new JPanel(new GridLayout(0,2,0,10));
        yearMonthPanel.add(new JLabel("Year"));
        yearMonthPanel.add(year);
        yearMonthPanel.add(new JLabel("Month"));
        yearMonthPanel.add(month);
        yearMonthPanel.add(new JLabel("Budget"));
        yearMonthPanel.add(budget);
        JPanel buttonPanel = new JPanel();
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(submitButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(35, 0, 0, 0));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add all components to layout
        layout.add(yearMonthPanel);
        layout.add(buttonPanel);

        // Set up frame
        layout.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
        frame.setContentPane(layout);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * React to drop down selection and button click that result in ActionEvent.
     * Code inspired from <a href="https://youtu.be/Kmgo00avvEw">here</a>.
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource()==submitButton){
            // Check that user has inputted
            if (Objects.equals(year.getText(), "")||Objects.equals(budget.getText(), "")){
                JOptionPane.showMessageDialog(frame,"Please fill in text fields.");
            } else {
                //call helper method when there is input
                getOutput();
            }
        } else if (evt.getSource()==month) {
            // Get integer representation of selected Month
            this.selectedMonth = month.getSelectedIndex();
        }
    }

    /**
     * Helper method to all controller method when user input is valid.
     */
    private void getOutput(){
        try {
            // Change user input to required number types
            int yearInt = Integer.parseInt(year.getText());
            int monthID = (yearInt * 100) + selectedMonth;
            double budgetValue = Double.parseDouble(budget.getText());

            if (yearInt < 1900 || yearInt > 2100){
                JOptionPane.showMessageDialog(frame, "Input a valid year.");
            } else if (selectedMonth == 0) {
                JOptionPane.showMessageDialog(frame,"Please select a Month.");
            } else if (budgetValue <= 0){
                JOptionPane.showMessageDialog(frame, "Budget must be more than 0.");
            } else {
                // Create new MonthlyStorage and get output
                NewMonthOD output = controller.getOutput(session, monthID, budgetValue);
                if (output.isSuccessful()){
                    loadMonthMenu(session,monthID,"Month created successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, output.getWarning());
                }
            }
        } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(frame,"Please input valid numbers.");
        }
    }

    /**
     * Load Month Menu and notify user.
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     * @param message notify user that Month Menu is updated
     */
    @Override
    public void loadMonthMenu(SessionStorage session, int monthID, String message) {
        frame.setVisible(false);

        // Construct MonthMenuV
        MonthMenuOB monthMenuOutputBoundary = new MonthMenuP();
        UpdateViewIB updateViewInteractor = new UpdateViewUCI(monthMenuOutputBoundary);
        UpdateViewC updateViewControl = new UpdateViewC(updateViewInteractor);
        MonthMenuV monthMenu = new MonthMenuV(updateViewControl,session,monthID);

        // Open Month Menu
        monthMenu.openMonthMenu(message);
    }
}
