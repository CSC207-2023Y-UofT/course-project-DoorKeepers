package views.add_edit_category_views;

import use_cases.add_edit_category_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryV implements CategoryVB, ActionListener {
    final CategoryC controller;
    public AddCategoryV(CategoryC controller) {

        JLabel name_label = new JLabel("Category Name:");
        JTextField name = new JTextField(15);
        JLabel value_label = new JLabel("Category Budget:");
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

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Add New Category");
        frame.setSize(300,500);
        frame.pack();
        frame.setVisible(true);
        panel.add(name_label, BorderLayout.WEST);
        panel.add(name, BorderLayout.CENTER);
        panel.add(value_label);
        panel.add(value);
        panel.add(submit);
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
    public void exitToMonthMenu() {

    }
}
