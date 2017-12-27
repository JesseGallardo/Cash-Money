import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BudgetSheet {

    private ArrayList<Income> arrayI;
    private ArrayList<Expenses> arrayE;
    private int size = 0;

    public BudgetSheet() {
        arrayI = new ArrayList<>();
        arrayE = new ArrayList<>();
    }

    public boolean addIncome(String name, double money) {
        for (int i = 0; i < arrayI.size(); i++){
            if (arrayI.get(i).getName().equalsIgnoreCase(name)){
                JOptionPane.showMessageDialog(null,"Name already in use. No duplicates allowed!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        Income i = new Income(name, money);
        if (i != null) {
            arrayI.add(i);
            size++;
            return true;
        } else {
            return false;
        }
    }

    public int containsIncome(String name) {
        for (int i = 0; i < arrayI.size(); i++) {
            Income test = arrayI.get(i);
            String testName = test.getName();
            double testCash = test.getMoney();
            if (testName.equalsIgnoreCase(name)) {
                    return i;
            }
        }
        return -1;
    }

    public boolean removeIncome(String name) {
        int indexCheck = containsIncome(name);
        if (indexCheck != -1) {
            arrayI.remove(indexCheck);
            return true;
        } else {
            return false;
        }
    }

    public boolean addExpense(String name, double money) {
        for (int i = 0; i < arrayI.size(); i++){
            if (arrayI.get(i).getName().equalsIgnoreCase(name)){
                JOptionPane.showMessageDialog(null,"Name already in use. No duplicates allowed!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        Expenses e = new Expenses(name, money);
        if (e != null) {
            arrayE.add(e);
            size++;
            return true;
        } else {
            return false;
        }
    }

    public int containExpenses(String name){
        for (int i = 0; i < arrayE.size(); i++) {
            Expenses test = arrayE.get(i);
            String testName = test.getName();
            double testCash = test.getMoney();
            if (testName.equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removeExpenses(String name){
        int indexCheck = containExpenses(name);
        if (indexCheck != -1){
            arrayE.remove(indexCheck);
            return true;
        } else{
            return false;
        }
    }
    public void writeFile() throws IOException{
        String userHomeFolder = System.getProperty("user.home");
        File folder = new File(userHomeFolder + File.separator + "Desktop" + File.separator + "BudgetSheet");
        folder.mkdir();
        File file = null;
        for (int i = 1; i < 1000; i++){
            String fileName = "BudgetSheet" + i + ".txt";
            file = new File(userHomeFolder + File.separator + "Desktop" + File.separator+ "BudgetSheet" + File.separator + fileName);
            if (file.exists()){
                continue;
            }
            else{
                break;
            }
        }
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("Income \n");
        for (int i = 0; i < arrayI.size(); i++) {
            bw.write(arrayI.get(i).getName() + ":   " + "$" + arrayI.get(i).getMoney() + "\n");
        }
        bw.write("\n"); bw.write("\n");
        bw.write("--------------------");
        bw.write("\n"); bw.write("\n");
        bw.write("Expenses" + "\n");
        for (int i = 0; i < arrayE.size(); i++) {
            bw.write(arrayE.get(i).getName() + ":   " + "$" + arrayE.get(i).getMoney() + "\n");
        }
        bw.write("\n"); bw.write("\n");
        bw.write("--------------------");
        bw.write("\n"); bw.write("\n");
        bw.write("Cash Flow:   " + "$" + (totalIncome() - totalExpenses()));

        bw.close();
    }

    public int getSize() {
        return size;
    }

    public double totalIncome(){
        double total = 0;
        for (Income i: arrayI) {
            total += i.getMoney();
        }
        return total;
    }

    public double totalExpenses(){
        double total = 0;
        for (Expenses e: arrayE) {
            total += e.getMoney();
        }
        return total;
    }
}
