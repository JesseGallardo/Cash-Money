import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
* Codename: Project Cash Money.
*@Author Jesse Gallardo
*Version 0.0.01
*/
public class Main {


    public static void messageDialog(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }//messageDialog

    public static double inputDoubleDialog(String message, String title) {
        String answer = JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        return Double.parseDouble(answer);
    }//inputDoubleDialog

    public static int inputIntegerDialog(String message, String title) {
        String answer = JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        return Integer.parseInt(answer);
    }//inputIntegerDialog

    public static String inputStringDialog(String message, String title) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }//inputStringDialog

    public static int buttonDialog(String message, String title, String[] buttonOptions) {
        return JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttonOptions, buttonOptions[0]);
    }//buttonDialog

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] option1 = {"Make a new Budget Sheet.", "Read the Changelog", "Quit"};
        String[] option2 = {"Add", "Remove"};
        String[] option3 = {"Income", "Expense"};
        String[] opttion4 = {"Yes", "No"};

        messageDialog("Welcome to Cash Money.", "Welcome");
        do {
            int choice1 = buttonDialog("What action would you like to do.", "Welcome", option1);

            switch (choice1) {
                case 0:
                    BudgetSheet bs = new BudgetSheet();
                    A:
                    do {
                        int choice2 = buttonDialog("What do you want to do? ", "Options", option2);
                        if (choice2 == 0) {
                            int choice3 = buttonDialog("What do you want to add?", "Options", option3);
                            if (choice3 == 0) {
                                String nameOfInc = inputStringDialog("What is this name of this piece of income? (EX: Wage)", "Income Info");
                                double amountInc = inputDoubleDialog("How much do you gain from this? (I.E. amount)", "Income Info");
                                bs.addIncome(nameOfInc, amountInc);
                            } else if (choice3 == 1) {
                                String nameOfExp = inputStringDialog("What is the name of this expense?", "Expense Info");
                                double amountExp = inputDoubleDialog("How much do you pay towards this expense", "Expense Info");
                                bs.addExpense(nameOfExp, amountExp);
                            }
                        } else if (choice2 == 1) {
                            int choice3 = buttonDialog("What do you want to remove?", "Options", option2);
                            if (choice3 == 0) {
                                String incomeRem = inputStringDialog("What is the name of the content you wish to remove? (i.e Wage, etc.)", "Input");
                                if (bs.removeIncome(incomeRem)) {
                                    messageDialog("Item removed successfully!", "Removal");
                                } else {
                                    messageDialog("Item does not exist!", "Removal");
                                }
                            } else if (choice3 == 1) {
                                String expenseRem = inputStringDialog("What is the name of the content you wish to remove? (i.e Rent, etc.)", "Input");
                                if (bs.removeExpenses(expenseRem)) {
                                    messageDialog("Item removed successfully!", "Removal");
                                } else {
                                    messageDialog("Item does not exist!", "Removal");
                                }
                            }
                        }
                        int choice4 = buttonDialog("Do you wish to continue editing the budget sheet?", "Do you read this?", opttion4);
                        if (choice4 == 1) {
                            break;
                        }
                    } while (true);
                    try {
                        bs.writeFile();
                    } catch (IOException e) {
                        messageDialog("Error in writting the file! Must restart!", "Error");
                    }
                    messageDialog("A new file has been successfully been made." + "\n" + "There is a new Folder on your Desktop called BudgetSheet all files made by this program are created in their.", "Program");
                    break;
                //case0
                case 1:
                    try {
                        BufferedReader bfr = new BufferedReader(new FileReader(new File("Changelog.txt")));
                    } catch (FileNotFoundException e) {
                        messageDialog("File not found. Action unavailable!", "Program");
                    }
                    break;
                case 2:
                    messageDialog("Ok", "Program");
                    break;
            }
            int final_choice = buttonDialog("Do you wish to use this program again?", "Program", opttion4);
            if (final_choice == 1){
                break;
            }
        }while (true);
        messageDialog("Thanks for using Cash Money", "Program");
    }//main
}
