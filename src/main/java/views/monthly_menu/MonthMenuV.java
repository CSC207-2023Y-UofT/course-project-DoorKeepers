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

public class MonthMenuV implements MonthMenuVB {
    UpdateViewC controller;

    public MonthMenuV(UpdateViewC controller, SessionStorage session, int monthID) {
        this.controller = controller;

        JFrame frame = new JFrame("Monthly Menu");
        frame.setVisible(true);
        frame.setSize(900,600);
        frame.setLayout(new GridLayout(2,2,3,3));

        JLabel showMonth = new JLabel("Month: ");

        JButton addExpense = new JButton("Add an expense");
        JButton editExpense = new JButton("Edit an expense");
        JButton addCategory = new JButton("Add an category");
        JButton editCategory = new JButton("Edit an category");
        JButton generateSummary = new JButton("Generate summary");

        JPanel buttons = new JPanel(new GridLayout(0,2,3,3));
        buttons.add(addExpense);
        buttons.add(editExpense);
        buttons.add(addCategory);
        buttons.add(editCategory);
        buttons.add(generateSummary);

        String[] expenseTableTitle = new String[]{"Expense", "Value"};
        String[] categoryTableTitle = new String[]{"Category", "Budget"};

        Object[] expenseData = controller.getOutput(session, monthID).getExpenseData().toArray();
        Object[][] expenseList = new Object[expenseData.length][2];
        for (int i = 0; i < expenseData.length; i++) {
            Expense expense = (Expense) expenseData[i];
            expenseList[i][0] = expense.getName();
            expenseList[i][1] = expense.getValue();
        }
        JTable expenseTable = new JTable(expenseList, expenseTableTitle);

        Object[] categoryData = controller.getOutput(session, monthID).getCategoryData().toArray();
        Object[][] categoryList = new Object[categoryData.length][2];
        for (int i = 0; i < categoryData.length; i++) {
            Category category = (Category) categoryData[i];
            categoryList[i][0] = category.getName();
            categoryList[i][1] = category.getBudget();
        }
        JTable categoryTable = new JTable(categoryList, categoryTableTitle);

        //TODO: put together the components
        frame.add(showMonth);
        frame.add(buttons);

    }

    //TODO: add method for button reactions.
}
