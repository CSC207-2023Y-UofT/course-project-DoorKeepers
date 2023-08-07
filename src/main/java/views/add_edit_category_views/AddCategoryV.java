package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryOD;
import views.load_monthly_menu.LoadMonthMenuVB;
import views.monthly_menu.MonthMenuV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A view class for the AddCategoryV that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a CategoryOD object.
 */

public class AddCategoryV extends JFrame implements ActionListener, LoadMonthMenuVB {
    private final MonthMenuV monthMenu;
    private final CategoryC controller;
    private final JTextField nameInput;
    private final JTextField budgetInput;
    private final int monthID;
    private final SessionStorage currSession;
    private final String oldCategory;

    /**
     * Builds AddCategoryV for user entries.
     * @param monthMenu MonthMenuV that need to be updated when a new Category is created
     * @param controller CategoryC reacts to user input to return a CategoryOD.
     * @param monthID int representing the MonthlyStorage.
     * @param currSession SessionStorage the current working session.
     */
    public AddCategoryV(MonthMenuV monthMenu, CategoryC controller, int monthID, SessionStorage currSession) {
        this.monthMenu = monthMenu;
        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
        this.oldCategory = null;
        this.nameInput = new JTextField(15);
        this.budgetInput = new JTextField(15);
    }

    /**
     * Open add category GUI.
     */
    public void openAddCategory(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("Add New Category");
        frame.setSize(300, 500);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel.setLayout(new GridLayout(0, 1));

        JLabel nameLabel = new JLabel("Category Name:");
        JLabel valueLabel = new JLabel("Category Budget:");
        JButton submit = new JButton("Submit");

        submit.addActionListener(this);

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(valueLabel);
        panel.add(budgetInput);
        panel.add(submit);

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Checks and formats user input to pass in valid parameters for a CategtoryC to start a use case.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // Check if user inputs a category name.
        if (nameInput.getText().isEmpty()) {
            JOptionPane.showMessageDialog( this, "Please enter a category name.");
        }
        // Check if user inputs a category budget.
        if (budgetInput.getText().isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter a category budget.");
        } else {
            tryUseCaseAdd();
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
        monthMenu.openMonthMenu(message,false);
    }

    /**
     * Tries an Add Category use case.
     * Pop-up window with context specific message may be shown to user.
     */
    private void tryUseCaseAdd(){
        CategoryOD message;
        message = null;
        try {
            message = controller.categoryInMonth(nameInput.getText(), String.valueOf(budgetInput.getText()),
                    monthID, currSession, oldCategory);
            // Update Month Menu
            loadMonthMenu(currSession,monthID,null);
        } catch (EntityException e) {
            JOptionPane.showMessageDialog(this,
                    "This month does not exist in current session. Please go to add month page.");
        }if (message != null) {
            JOptionPane.showMessageDialog(this, message.getMessage());
        }
    }
}
