package views.add_edit_epense_views;

import entities.EntityException;
import entities.SessionStorage;
import use_case.add_edit_expenses_use_case.ExpenseOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddExpenseV extends Component implements ActionListener {
    ExpenseC controller;
    JTextField nameInput;
    JTextField valueInput;
    JComboBox<String> categoryCombo;
    String selectedExpense;
    String selectedCategory;
    JCheckBox isRecurringCheckBox;
    boolean isRecurring;
    int monthID;
    SessionStorage currSession;

    public AddExpenseV(ExpenseC controller, String[] existingCategory, int monthID, SessionStorage currSession) {
        /*
          Builds AddExpenseV.
         */
        JLabel nameLabel = new JLabel("Expense Name:");
        this.nameInput = new JTextField(15);
        JLabel valueLabel = new JLabel("Expense Budget:");
        this.valueInput = new JTextField(15);
        JLabel selectCategoryLabel = new JLabel(" Assign Category:");
        this.categoryCombo = new JComboBox<>(existingCategory); // category list
        JButton submit = new JButton("Submit");
        submit.setSize(30, 10);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel.setLayout(new GridLayout(0, 1));
        JPanel panell = new JPanel();
        panell.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panell.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add New Expense");
        frame.setSize(300, 500);
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(valueLabel);
        panel.add(valueInput);
        panel.add(selectCategoryLabel);
        panel.add(categoryCombo);
        panel.add(isRecurringCheckBox);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        frame.pack();
        frame.setVisible(true);

        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
        this.selectedExpense = null;

        categoryCombo.addActionListener(this);
        isRecurringCheckBox.addActionListener(this);
        submit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        /*
          Formats user input to pass in valid parameters for a CategtoryC to start a use case.
          Pop-up window with context specific message may be shown to user.
         */
        if(evt.getSource() == categoryCombo){
            this.selectedCategory = (String) categoryCombo.getSelectedItem();
        }if (evt.getSource() == isRecurringCheckBox){
            this.isRecurring = isRecurringCheckBox.isSelected();
        }if (isRecurring == (!Objects.equals(selectedCategory, "Other"))) {
            JOptionPane.showMessageDialog( this, "A recurring expense belongs to Category 'Other'. Please select Category 'Other' in the field above, thanks! ");
        } else {
            try {
                ExpenseOD message = controller.expenseInMonth(nameInput.getText(), String.valueOf(valueInput), selectedCategory, isRecurring, monthID, currSession, selectedExpense);
                JOptionPane.showMessageDialog(this, message.getMessage());
            } catch (EntityException e) {
                JOptionPane.showMessageDialog(this, "This month does not exist in current session. Please go to add month page.");
            }
        }

    }

}
