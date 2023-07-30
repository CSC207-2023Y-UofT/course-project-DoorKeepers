package views.add_edit_category_views;

import entities.Category;
import entities.SessionStorage;
import use_cases.add_edit_category_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCategoryV implements CategoryVB, ActionListener {
    final CategoryC controller;
    private SessionStorage curr_session;

    public EditCategoryV(CategoryC controller){

        JLabel select_category_label = new JLabel(" Select existing category:");
        JComboBox<Category> category = new JComboBox<Category>(curr_session.getMonthlyData()); // category list
        JLabel name_label = new JLabel("New Category Name:");
        JTextField name = new JTextField(15);
        JLabel value_label = new JLabel(" New Category Budget:");
        JTextField value = new JTextField(15);
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
        panel.add(category);
        panel.add(name_label, BorderLayout.WEST);
        panel.add(name, BorderLayout.CENTER);
        panel.add(value_label);
        panel.add(value);
        frame.add(panell, BorderLayout.SOUTH);
        panell.add(submit);

        submit.addActionListener(this);

        CategoryP categoryP = new CategoryP();
        CategoryIB categoryUCI = new CategoryUCI(categoryP);
        this.controller = new CategoryC(categoryUCI);


    }

    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click " + e.getActionCommand());

    }


    @Override
    public ArrayList<Category> category_selection() {
        curr_session.getMonthlyData()
        return null;
    }
}
