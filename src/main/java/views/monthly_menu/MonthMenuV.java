package views.monthly_menu;

import entities.SessionStorage;

import javax.swing.*;
import java.awt.*;

public class MonthMenuV implements MonthMenuVB{
    UpdateViewC controller;

    public MonthMenuV(UpdateViewC controller, SessionStorage session, int monthID){
        this.controller = controller;

        JLabel title = new JLabel("Monthly Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton addExpense = new JButton("Add an expense");
        JButton editExpense = new JButton("Edit an expense");
        JButton addCategory = new JButton("Add an category");
        JButton editCategory = new JButton("Edit an category");
        JButton generateSummary = new JButton("Generate summary");

        String[] expenseTableTitle = new String[]{"Expense", "Value"};
        String[] categoryTableTitle = new String[]{"Category", "Budget"};
        Object[][] expenseList = controller.getOutput(session, monthID).getExpenseList();
        JTable expenseTable = new JTable(expenseList, expenseTableTitle);
        Object[][] categoryList = controller.getOutput(session, monthID).getCategoryList();
        JTable categoryTable = new JTable(categoryList, categoryTableTitle);
        //TODO: put together the components
        //TODO: add reactions to the buttons
    }
}
