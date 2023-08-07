package views.add_edit_epense_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_expenses_use_case.ExpenseOD;
import views.load_monthly_menu.LoadMonthMenuVB;
import views.monthly_menu.MonthMenuV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * A view class for the AddExpenseV that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a ExpenseOD object.
 */
public class AddExpenseV extends JFrame implements ActionListener, LoadMonthMenuVB {
    private final MonthMenuV monthMenu;
    private final ExpenseC controller;
    private final JTextField nameInput;
    private final JTextField valueInput;
    private final JComboBox<String> categoryCombo;
    private final String selectedExpense;
    private String selectedCategory;
    private final JCheckBox isRecurringCheckBox = new JCheckBox("Is recurring expense");
    private boolean isRecurring;
    private final JButton submit = new JButton("Submit");
    private final int monthID;
    private final SessionStorage currSession;

    /**
     * Builds AddExpenseV for user entries.
     * @param monthMenu MonthMenuV that contains the button that creates AddExpenseV
     * @param controller ExpenseC reacts to user input to return ExpenseOD.
     * @param existingCategory String[] of Category names that exists in current month.
     * @param monthID int representing the MonthlyStorage.
     * @param currSession SessionStorage the current working session.
     */
    public AddExpenseV(MonthMenuV monthMenu, ExpenseC controller, String[] existingCategory, int monthID, SessionStorage currSession) {
        this.monthMenu = monthMenu;
        this.nameInput = new JTextField(15);
        this.valueInput = new JTextField(15);
        this.categoryCombo = new JComboBox<>(existingCategory); // category list

        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
        this.selectedExpense = null;
    }

    /**
     * Open add expense GUI.
     */
    public void openAddExpense(){
        JLabel nameLabel = new JLabel("Expense Name:");
        JLabel valueLabel = new JLabel("Expense Budget:");
        JLabel selectCategoryLabel = new JLabel(" Assign Category:");

        submit.setSize(30, 10);

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel.setLayout(new GridLayout(0, 1));
        JPanel panell = new JPanel();
        panell.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panell.setLayout(new GridLayout(0, 1));

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
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        frame.pack();
        frame.setVisible(true);

        categoryCombo.addActionListener(this);
        isRecurringCheckBox.addActionListener(this);
        submit.addActionListener(this);
    }

    /**
     * Checks and formats user input to pass in valid parameters to start a use case.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == categoryCombo){
            this.selectedCategory = Objects.requireNonNull(categoryCombo.getSelectedItem()).toString();
            System.out.println(selectedCategory);

        }if (evt.getSource() == isRecurringCheckBox){
            this.isRecurring = isRecurringCheckBox.isSelected();
        } else {
            ExpenseOD message;
            message = null;
            try {
                message = controller.expenseInMonth(nameInput.getText(), valueInput.getText(), selectedCategory, isRecurring, monthID, currSession, selectedExpense);
//                JOptionPane.showMessageDialog(this, message.getMessage());
//                 Update Month Menu
                loadMonthMenu(currSession,monthID,null);
            } catch (EntityException e) {
                JOptionPane.showMessageDialog(this, "This month does not exist in current session. Please go to add month page.");
            }if (message != null) {
                JOptionPane.showMessageDialog(this, message.getMessage());
        }}
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
        monthMenu.openMonthMenu(message,false);
    }

//   public static void main(String[] args){
//        MonthMenuC
//       ExpenseP presenter = new ExpenseP();
//       ExpenseUCI UCI = new ExpenseUCI(presenter);
//       ExpenseC controller = new ExpenseC(UCI);
//       String[] categoryList = new String[1];
//       categoryList[0] = "Other";
//       int monthID = 1;
//       MonthlyStorage month = new MonthlyStorage(1,123);
//       SessionStorage currSession = new SessionStorage();
//       currSession.addMonth(month);
//       MonthMenuV monthMenu = new MonthMenuV(monthC, currSession, 1);
//       new AddExpenseV(monthMenu, controller,categoryList, monthID, currSession);
//    }

}
