package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A view class for the Add_Category_view that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a Category_OD object.
 */

public class AddCategoryV extends Component implements ActionListener {
    CategoryC controller;
    JTextField name_input;
    JTextField budget_input;
    int monthID;
    SessionStorage curr_session;
    public AddCategoryV(CategoryC controller,int monthID, SessionStorage curr_session) {
        /**
         * Builds Add_Category_View.
         */
        JLabel name_label = new JLabel("Category Name:");
        this.name_input = new JTextField(15);
        JLabel value_label = new JLabel("Category Budget:");
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

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add New Category");
        frame.setSize(300,500);
        frame.pack();
        frame.setVisible(true);
        panel.add(name_label, BorderLayout.WEST);
        panel.add(name_input, BorderLayout.CENTER);
        panel.add(value_label);
        panel.add(budget_input);
        panel.add(submit);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        this.controller = controller;
        this.monthID = monthID;
        this.curr_session = curr_session;

        submit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        CategoryOD m = null;
        try {
            m = controller.addCategoryInMonth(name_input.getText(), String.valueOf(budget_input), monthID, curr_session);
        } catch (EntityException e) {
            JOptionPane.showMessageDialog( this, "This month does not exist in current session. Please go to add month page.");
        }
        assert m != null;
        JOptionPane.showMessageDialog( this, m.getMessage());

    }

}
