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
 * View class for the EditCategoryV that extends Component class and implements ActionListener interface.
 */
public class EditCategoryV extends JFrame implements ActionListener, LoadMonthMenuVB {
    private final MonthMenuV monthMenu;
    private final CategoryC controller;
    private final JButton submit;
    private final JComboBox<String> categoryCombo;
    private final JTextField nameInput;
    private final JTextField budgetInput;
    private String selectedCategory;
    private final int monthID;
    private final SessionStorage currSession;
    private final JFrame frame;

    /**
     * Builds EditCategoryV for user entries.
     * @param controller CategoryC reacts to user input to return a CategoryOD.
     * @param existingCategory String of existing categories in the MonthlyStorage with monthID.
     * @param monthID int representing the MonthlyStorage.
     * @param currSession SessionStorage the current working session.
     */
    public EditCategoryV(MonthMenuV monthMenu, CategoryC controller, String[] existingCategory, int monthID,
                         SessionStorage currSession) {
        this.monthMenu = monthMenu;
        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
        this.frame = new JFrame();

        this.submit = new JButton("Submit");
        this.categoryCombo = new JComboBox<>(existingCategory);
        this.nameInput = new JTextField(15);
        this.budgetInput = new JTextField(15);
    }

    /**
     * Open edit category GUI.
     */
    public void openEditCategory(){
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setTitle("Edit Category");
        frame.setSize(500,300);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 30, 50, 30));
        panel.setLayout(new GridLayout(0,1));

        JLabel selectCategoryLabel = new JLabel(" Select existing category:");
        JLabel nameLabel = new JLabel("New Category Name:");
        JLabel budgetLabel = new JLabel(" New Category Budget:");
        submit.setSize(30,10);

        submit.addActionListener(this);
        categoryCombo.addActionListener(this);

        panel.add(selectCategoryLabel);
        panel.add(categoryCombo);
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(budgetLabel);
        panel.add(budgetInput);
        panel.add(submit);

        frame.add(panel, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Checks and formats user input to pass in valid parameters for a CategtoryC to start a use case.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        //Two ActionListeners with different behaviours differentiated by checking evt.getSource().
        if (evt.getSource() == categoryCombo) {
            this.selectedCategory = (String) categoryCombo.getSelectedItem();
        } else if(selectedCategory == null){
            JOptionPane.showMessageDialog( this, "Please select a category you wish to edit.");
        } else if(evt.getSource() == submit){
            if (nameInput.getText().isEmpty()) {// Check if user inputs a category name.
                JOptionPane.showMessageDialog( this, "Please enter the previous category name if " +
                        "you don't wish to edit. Thanks.");
            } else if (budgetInput.getText().isEmpty()){// Check if user inputs a category budget.
                JOptionPane.showMessageDialog(this,"Please enter the previous category budget if " +
                        "you don't wish to edit. Thanks.");
            } else {tryUseCaseEdit();}
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
     * Tries an Edit Category Use Case.
     * Pop-up window with context specific message may be shown to user.
     */
    private void tryUseCaseEdit(){
        try {
            CategoryOD message = controller.categoryInMonth(nameInput.getText(), String.valueOf(budgetInput.getText()), monthID, currSession, selectedCategory);
            JOptionPane.showMessageDialog(this, message.getMessage());
            frame.setVisible(false);
            // Update Month Menu
            loadMonthMenu(currSession,monthID,null);
        } catch (EntityException e) {
            JOptionPane.showMessageDialog(this, "This month does not exist in current session. Please go to add month page.");
        }
    }
}
