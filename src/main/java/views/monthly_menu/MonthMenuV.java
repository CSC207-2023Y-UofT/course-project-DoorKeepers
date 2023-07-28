package views.monthly_menu;

import entities.Category;
import entities.Expense;
import entities.SessionStorage;
import use_cases.monthly_menu.MonthMenuOD;
import use_cases.monthly_menu.UpdateViewIB;
import use_cases.monthly_menu.UpdateViewID;
import use_cases.monthly_menu.UpdateViewUCI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MonthMenuV implements MonthMenuVB {
    views.monthly_menu.UpdateViewC controller;

    public MonthMenuV(views.monthly_menu.UpdateViewC controller, SessionStorage session, int monthID) {
        this.controller = controller;
        //Set the frame and the overall layout
        JFrame frame = new JFrame("Monthly Menu");
        JPanel layout = new JPanel(new BorderLayout(20, 20));

        try {
            //Separate the layout into left and right sides
            JPanel leftLayout = new JPanel(new BorderLayout(20,170));
            leftLayout.setBounds(30,30,200,200);
            JPanel rightLayout = new JPanel(new BorderLayout(20, 20));
            rightLayout.setBounds(30,30,333,200);

            //Left side components
            JLabel showMonth = new JLabel("Month: "+ monthID);
            leftLayout.add(showMonth, BorderLayout.NORTH);
            JPanel buttons = getButtons();
            leftLayout.add(buttons, BorderLayout.CENTER);
            JPanel genSumButton = getGenSum();
            leftLayout.add(genSumButton, BorderLayout.SOUTH);

            //Right side components: set JTables
            Object[] expenseData = controller.getOutput(session, monthID).getExpenseData().toArray();
            Object[] categoryData = controller.getOutput(session, monthID).getCategoryData().toArray();
            JTable expenseTable = getExpenseTable((Expense[]) expenseData);
            JTable categoryTable = getCategoryTable((Category[])categoryData);
            Dimension dimension = new Dimension(333,180);
            expenseTable.setPreferredScrollableViewportSize(dimension);
            categoryTable.setPreferredScrollableViewportSize(dimension);

            //Right side components: set JScrollPanes
            JScrollPane expenseScroll = new JScrollPane(expenseTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            expenseScroll.setBounds(15, 15, 333, 90);
            rightLayout.add(expenseScroll, BorderLayout.NORTH);
            JScrollPane categoryScroll = new JScrollPane(categoryTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            categoryScroll.setBounds(15, 15, 333, 90);
            rightLayout.add(categoryScroll, BorderLayout.CENTER);

            //Add both side to overall layout and set up frame
            layout.add(leftLayout, BorderLayout.WEST);
            layout.add(rightLayout, BorderLayout.CENTER);
            frame.setContentPane(layout);
            frame.pack();
            frame.setVisible(true);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(frame, controller.getOutput(session, monthID).getWarning());
        }
    }

    private static JPanel getButtons() {
        JPanel buttons = new JPanel(new GridLayout(0,2,20,20));

        JButton addExpense = new JButton("Add an expense");
        JButton editExpense = new JButton("Edit an expense");
        JButton addCategory = new JButton("Add an category");
        JButton editCategory = new JButton("Edit an category");

        addExpense.setBounds(200,50,200,30);
        editExpense.setBounds(200,50,200,30);
        addCategory.setBounds(200,50,200,50);
        editCategory.setBounds(200,50,200,50);

        buttons.add(addExpense);
        buttons.add(editExpense);
        buttons.add(addCategory);
        buttons.add(editCategory);

        return buttons;
    }

    private static JPanel getGenSum(){
        JPanel genSumButton = new JPanel(new GridLayout(0,2,20,20));
        JButton generateSummary = new JButton("Generate summary");
        generateSummary.setBounds(200,50,200,50);
        genSumButton.add(generateSummary);

        return genSumButton;
    }

    private JTable getExpenseTable(Expense[] expenseData){
        String[] expenseTableTitle = new String[]{"Expense", "Value"};
        Object[][] expenseList = new Object[expenseData.length][2];
        for (int i = 0; i < expenseData.length; i++) {
            Expense expense = (Expense) expenseData[i];
            expenseList[i][0] = expense.getName();
            expenseList[i][1] = expense.getValue();
        }
        return new JTable(expenseList, expenseTableTitle);
    }

    private static JTable getCategoryTable(Category[] categoryData){
        String[] categoryTableTitle = new String[]{"Category", "Budget"};
        Object[][] categoryList = new Object[categoryData.length][2];
        for (int i = 0; i < categoryData.length; i++) {
            Category category = (Category) categoryData[i];
            categoryList[i][0] = category.getName();
            categoryList[i][1] = category.getBudget();
        }
        return new JTable(categoryList, categoryTableTitle);
    }

    //TODO: add method for button reactions.
}
