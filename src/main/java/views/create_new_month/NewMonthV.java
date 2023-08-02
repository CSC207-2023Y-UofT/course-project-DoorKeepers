package views.create_new_month;

import entities.SessionStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewMonthV implements ActionListener {
    NewMonthC controller;
    SessionStorage session;
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

        // Create frame
        JFrame frame = new JFrame("Creat a new Month");
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource()==submitButton){
            int yearInt = Integer.parseInt(year.getText());
            int monthID = (yearInt * 100) + selectedMonth;
            int budgetValue = Integer.parseInt(budget.getText());
            //TODO: use output
            controller.getOutput(session,monthID, budgetValue);
        } else {
            this.selectedMonth = month.getSelectedIndex()+1;
        }
    }
}
