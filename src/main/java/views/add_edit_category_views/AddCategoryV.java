package views.add_edit_category_views;

import entities.SessionStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCategoryV extends Component implements ActionListener {
    CategoryC controller;
    JTextField name_input;
    JTextField budget_input;
    public AddCategoryV(CategoryC controller,int monthID, SessionStorage curr_session) {

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
        try{
            controller.addCategoryInMonth(name_input.getText(), String.valueOf(budget_input), monthID, curr_session);
            JOptionPane.showMessageDialog( this, name_input.getText());
        }catch (Exception e){
            JOptionPane.showMessageDialog( this, e.getMessage());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click " + e.getActionCommand());
        System.out.println("Click " + e.getActionCommand());

    }

}
