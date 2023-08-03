package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * View class for the EditCategoryV that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a Category_OD object.
 */
public class EditCategoryV extends Component implements ActionListener {
    CategoryC controller;
    JComboBox<String> categoryCombo;
    JTextField nameInput;
    JTextField budgetInput;
    String selectedCategory;
    int monthID;
    SessionStorage currSession;

    public EditCategoryV(CategoryC controller, String[] existingCategory, int monthID, SessionStorage currSession) {
        /*
          Builds EditCategoryV.
         */
        JLabel select_category_label = new JLabel(" Select existing category:");
        this.categoryCombo = new JComboBox<>(existingCategory); // category list
        JLabel name_label = new JLabel("New Category Name:");
        this.nameInput = new JTextField(15);
        JLabel budget_label = new JLabel(" New Category Budget:");
        this.budgetInput = new JTextField(15);
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
        frame.setTitle("Edit Category");
        frame.setSize(300,500);
        frame.setSize(500,300);
        panel.add(select_category_label);
        panel.add(categoryCombo);
        panel.add(name_label, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(budget_label);
        panel.add(budgetInput);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        frame.pack();
        frame.setVisible(true);

        submit.addActionListener(this);
        categoryCombo.addActionListener(this);

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
        if (evt.getSource() == categoryCombo) {
            this.selectedCategory = (String) categoryCombo.getSelectedItem();
        }
        else {
            CategoryOD m = null;
            try {
                m = controller.categoryInMonth(nameInput.getText(), String.valueOf(budgetInput), monthID, currSession, selectedCategory);
            } catch (EntityException e) {
                JOptionPane.showMessageDialog( this, "This month does not exist in current session. Please go to add month page.");
            }
            if(m != null){
                JOptionPane.showMessageDialog( this, m.getMessage());}
        }



    }


}
