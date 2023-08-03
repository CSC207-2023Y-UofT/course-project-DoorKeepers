package views.add_edit_category_views;

import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.CategoryOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A view class for the AddCategoryV that extends Component class and implements ActionListener interface.
 * Creates a new controller that produces a CategoryOD object.
 */

public class AddCategoryV extends Component implements ActionListener {
    CategoryC controller;
    JTextField nameInput;
    JTextField budgetInput;
    int monthID;
    SessionStorage currSession;
    String oldCategory;
    public AddCategoryV(CategoryC controller,int monthID, SessionStorage currSession) {
        /*
          Builds AddCategoryV.
         */
        JLabel nameLabel = new JLabel("Category Name:");
        this.nameInput = new JTextField(15);
        JLabel value_label = new JLabel("Category Budget:");
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

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add New Category");
        frame.setSize(300,500);
        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(nameInput, BorderLayout.CENTER);
        panel.add(value_label);
        panel.add(budgetInput);
        panel.add(submit);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        frame.pack();
        frame.setVisible(true);

        this.controller = controller;
        this.monthID = monthID;
        this.currSession = currSession;
        this.oldCategory = null;

        submit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
        /*
          Formats user input to pass in valid parameters for a CategtoryC to start a use case.
          Pop-up window with context specific message may be shown to user.
         */
        CategoryOD m = null;
        try {
            m = controller.categoryInMonth(nameInput.getText(), String.valueOf(budgetInput), monthID, currSession, oldCategory);
        } catch (EntityException e) {
            JOptionPane.showMessageDialog( this, "This month does not exist in current session. Please go to add month page.");
        }
        if(m != null){
        JOptionPane.showMessageDialog( this, m.getMessage());}

    }

}
