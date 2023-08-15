package views.add_edit_epense_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_expenses_use_case.ExpenseOutputData;
import views.load_monthly_menu.LoadMonthMenuViewBoundary;
import views.monthly_menu.MonthMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * A view class for the AddExpenseView that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a ExpenseOutputData object.
 */
public class AddExpenseView extends JFrame implements ActionListener, LoadMonthMenuViewBoundary {
    private final MonthMenuView monthMenu;
    private final ExpenseController controller;
    private final JTextField nameInput;
    private final JTextField valueInput;
    private final JComboBox<String> categoryCombo;
    private final String selectedExpense;
    private final JCheckBox isRecurringCheckBox = new JCheckBox("Is recurring expense");
    private final JButton submit = new JButton("Submit");
    private final int monthID;
    private final SessionStorage currSession;
    private final JFrame frame;

    /**
     * Builds AddExpenseView for user entries.
     *
     * @param monthMenu        MonthMenuView that contains the button that creates AddExpenseView
     * @param controller       ExpenseController reacts to user input to return ExpenseOutputData.
     * @param existingCategory String[] of Category names that exists in current month.
     * @param monthID          int representing the MonthlyStorage.
     * @param currSession      SessionStorage the current working session.
     */
    public AddExpenseView(MonthMenuView monthMenu, ExpenseController controller, String[] existingCategory, int monthID, SessionStorage currSession) {
        this.monthMenu = monthMenu;
        this.nameInput = new JTextField(15);
        this.valueInput = new JTextField(15);
        this.categoryCombo = new JComboBox<>(existingCategory); // category list
        this.frame = new JFrame();

        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
        this.selectedExpense = null;
    }

    /**
     * Open add expense GUI.
     */
    public void openAddExpense() {
        JLabel nameLabel = new JLabel("Expense Name:");
        JLabel valueLabel = new JLabel("Expense Budget:");
        JLabel selectCategoryLabel = new JLabel(" Assign Category:");

        submit.setSize(30, 10);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel.setLayout(new GridLayout(0, 1));
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel2.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("Add New Expense");
        frame.setSize(300, 500);
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(valueLabel);
        panel.add(valueInput);
        panel.add(selectCategoryLabel);
        panel.add(categoryCombo);
        panel.add(isRecurringCheckBox);
        frame.add(panel2, BorderLayout.SOUTH);
        panel2.add(submit);

        frame.pack();
        frame.setVisible(true);

        submit.addActionListener(this);
    }

    /**
     * Checks and formats user input to pass in valid parameters to start a use case.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        String selectedCategory = Objects.requireNonNull(categoryCombo.getSelectedItem()).toString();
        boolean isRecurring = isRecurringCheckBox.isSelected();
        if (evt.getSource() == submit) {
            ExpenseOutputData message;
            message = null;
            try {
                message = controller.expenseInMonth(nameInput.getText(), valueInput.getText(), selectedCategory, isRecurring, monthID, currSession, selectedExpense);
                frame.setVisible(false);
                ((LoadMonthMenuViewBoundary) this).loadMonthMenu(currSession, monthID, null);
            } catch (EntityException e) {
                JOptionPane.showMessageDialog(this, "This month does not exist in current session. Please go to add month page.");
            }
            if (message != null) {
                JOptionPane.showMessageDialog(this, message.getMessage());
            }
        }
    }

    /**
     * Load Month Menu and notify user if opening Month Menu of a new MonthlyStorage created.
     *
     * @param session the SessionStorage holding the required MonthlyStorage
     * @param monthID the monthID of the required MonthlyStorage
     * @param message notify user when new MonthlyStorage is created, otherwise null
     */
    @Override
    public void loadMonthMenu(SessionStorage session, int monthID, String message) {
        monthMenu.openMonthMenu(message, false);
    }
}
