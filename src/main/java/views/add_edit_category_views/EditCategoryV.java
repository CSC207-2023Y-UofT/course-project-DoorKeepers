package views.add_edit_category_views;

import entities.Category;
import entities.EntityException;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCategoryV extends Component implements ActionListener {
    CategoryC controller;
    JComboBox<Category> category_combo;
    JTextField name_input;
    JTextField budget_input;
    Category selected_category;

    public EditCategoryV(CategoryC controller, Category[] existing_category, int monthID, SessionStorage curr_session) {

        this.controller = controller;

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
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500,300);
        panel.add(select_category_label);
        panel.add(category_combo);
        panel.add(name_label, BorderLayout.WEST);
        panel.add(name_input, BorderLayout.CENTER);
        panel.add(budget_label);
        panel.add(budget_input);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        submit.addActionListener(this);
        category_combo.addActionListener(this);

        try{
            controller.editCategoryInMonth(name_input.getText(), String.valueOf(budget_input), monthID, curr_session, selected_category);
            JOptionPane.showMessageDialog( this, name_input.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog( this, e.getMessage());
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
        this.selected_category = (Category) category_combo.getSelectedItem();

    }


}
