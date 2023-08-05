package views.add_edit_epense_views;

import entities.EntityException;
import entities.SessionStorage;
import use_case.add_edit_expenses_use_case.ExpenseOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class EditExpenseV extends Component implements ActionListener {
    ExpenseC controller;
    JComboBox<String> expenseCombo;
    JComboBox<String> categoryCombo;
    JTextField nameInput;
    JTextField valueInput;
    String selectedCategory;
    String selectedExpense;
    JCheckBox isRecurringCheckBox;
    boolean isRecurring;
    int monthID;
    SessionStorage currSession;

    public EditExpenseV(ExpenseC controller, String[] existingExpense, int monthID, SessionStorage currSession) {
        /*
          Builds EditExpenseV.
         */
        JLabel select_expense_label = new JLabel(" Select existing expense:");
        this.expenseCombo = new JComboBox<>(existingExpense); // expense list
        JLabel nameLabel = new JLabel("New Expense Name:");
        this.nameInput = new JTextField(15);
        JLabel valueLabel = new JLabel(" New Expense Budget:");
        this.valueInput = new JTextField(15);
        this.isRecurringCheckBox = new JCheckBox("This is a recurring expense.");
        isRecurringCheckBox.setBounds(100,150,50,50);
        JButton submit = new JButton("Submit");
        submit.setSize(30,10);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel.setLayout(new GridLayout(0,1));
        JPanel panell = new JPanel();
        panell.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panell.setLayout(new GridLayout(0,1));

        frame.add(panel, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Edit Expense");
        frame.setSize(300,500);
        frame.setSize(500,300);
        panel.add(select_expense_label);
        panel.add(expenseCombo);
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(valueLabel);
        panel.add(valueInput);
        panel.add(isRecurringCheckBox);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        frame.pack();
        frame.setVisible(true);

        submit.addActionListener(this);
        expenseCombo.addActionListener(this);
        categoryCombo.addActionListener(this);
        isRecurringCheckBox.addActionListener(this);

        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        /*
          Two ActionListeners with different behaviours differentiated by evt.getSource().
          Formats user input to pass in valid parameters for a CategtoryC to start a use case.
          Pop-up window with context specific message may be shown to user.
         */
        if (evt.getSource() == expenseCombo) {
            this.selectedExpense = (String) expenseCombo.getSelectedItem();
        }if(evt.getSource() == categoryCombo){
            this.selectedCategory = (String) categoryCombo.getSelectedItem();
        }if (evt.getSource() == isRecurringCheckBox){
                this.isRecurring = isRecurringCheckBox.isSelected();
        }if (isRecurring == (!Objects.equals(selectedCategory, "Other"))) {
            JOptionPane.showMessageDialog( this, "A recurring expense belongs to Category 'Other'. Please select Category 'Other' in the field above, thanks! ");
        } else {
            ExpenseOD message = null;
            try {
                message = controller.expenseInMonth(nameInput.getText(), String.valueOf(valueInput), selectedCategory, isRecurring, monthID, currSession, selectedExpense);
            } catch (EntityException e) {
                JOptionPane.showMessageDialog( this, "This month does not exist in current session. Please go to add month page.");
            }
            if(message != null){
                JOptionPane.showMessageDialog( this, message.getMessage());}
        }
    }
}
