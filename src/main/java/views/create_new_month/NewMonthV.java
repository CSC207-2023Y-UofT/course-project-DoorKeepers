package views.create_new_month;

import entities.SessionStorage;
import use_cases.create_new_month.NewMonthOD;
import use_cases.monthly_menu.MonthMenuOB;
import use_cases.monthly_menu.UpdateViewIB;
import use_cases.monthly_menu.UpdateViewUCI;
import views.monthly_menu.MonthMenuP;
import views.monthly_menu.MonthMenuV;
import views.monthly_menu.UpdateViewC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class NewMonthV implements ActionListener, LoadNewMonthVB{
    NewMonthC controller;
    SessionStorage session;
    JFrame frame = new JFrame("Creat a new Month");
    JTextField year = new JTextField(9);
    JComboBox<String> month = new JComboBox<>(new String[]{"January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"});
    JTextField budget = new JTextField(9);
    JButton submitButton = new JButton("Submit");
    int selectedMonth = 0;


    public NewMonthV (NewMonthC controller, SessionStorage session){
        this.controller = controller;
        this.session = session;

        // Add action listener
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

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource()==submitButton){
            if (Objects.equals(year.getText(), "")||Objects.equals(budget.getText(), "")){
                JOptionPane.showMessageDialog(frame,"Please fill in text fields.");
            } else {
                getOutput();
            }
        } else if (evt.getSource()==month) {
            this.selectedMonth = month.getSelectedIndex()+1;
        }
    }

    private void getOutput(){
        try {
            int yearInt = Integer.parseInt(year.getText());
            int monthID = (yearInt * 100) + selectedMonth;
            double budgetValue = Double.parseDouble(budget.getText());

            NewMonthOD output = controller.getOutput(session, monthID, budgetValue);
            if (output.isSuccessful()){
                loadMonthMenu(monthID,"Month created successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, output.getWarning());
            }
        } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(frame,"Please input valid numbers.");
        }
    }

    /**
     * Open Month Menu and notify user.
     * @param message notify user that Month Menu is updated
     */
    @Override
    public void loadMonthMenu(int monthID, String message) {
        frame.setVisible(false);
        MonthMenuOB monthMenuOutputBoundary = new MonthMenuP();
        UpdateViewIB updateViewInteractor = new UpdateViewUCI(monthMenuOutputBoundary);
        UpdateViewC updateViewControl = new UpdateViewC(updateViewInteractor);
        MonthMenuV monthMenu = new MonthMenuV(updateViewControl,session,monthID);
        monthMenu.openMonthMenu(message);
    }
}
