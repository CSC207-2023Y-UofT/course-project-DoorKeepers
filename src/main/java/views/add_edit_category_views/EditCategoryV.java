package views.add_edit_category_views;

import entities.Category;
import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * View class for the Edit_Category_view that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a Category_OD object.
 */
public class EditCategoryV extends Component implements ActionListener {
    CategoryC controller;
    JComboBox<String> category_combo;
    JTextField name_input;
    JTextField budget_input;
    String selected_category;
    int monthID;
    SessionStorage curr_session;

    public EditCategoryV(CategoryC controller, String[] existing_category, int monthID, SessionStorage curr_session) {
        /**
         * Builds Edit_Category_View.
         */
        JLabel select_category_label = new JLabel(" Select existing category:");
        this.category_combo = new JComboBox<>(existing_category); // category list
        JLabel name_label = new JLabel("New Category Name:");
        this.name_input = new JTextField(15);
        JLabel budget_label = new JLabel(" New Category Budget:");
        this.budget_input = new JTextField(15);
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
        panel.add(category_combo);
        panel.add(name_label, BorderLayout.WEST);
        panel.add(name_input, BorderLayout.CENTER);
        panel.add(budget_label);
        panel.add(budget_input);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        frame.pack();
        frame.setVisible(true);

        submit.addActionListener(this);
        category_combo.addActionListener(this);

        this.controller = controller;
        this.monthID = monthID;
        this.curr_session = curr_session;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == category_combo) {
            this.selected_category = (String) category_combo.getSelectedItem();
        }
        else {
            CategoryOD m = null;
            try {
                m = controller.categoryInMonth(name_input.getText(), String.valueOf(budget_input), monthID, curr_session, selected_category);
            } catch (EntityException e) {
                JOptionPane.showMessageDialog( this, "This month does not exist in current session. Please go to add month page.");
            }
            if(m != null){
                JOptionPane.showMessageDialog( this, m.getMessage());}
        }



    }


}
