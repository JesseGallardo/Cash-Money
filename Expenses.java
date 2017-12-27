import java.util.ArrayList;

public class Expenses extends BudgetSheet{
    private double money;
    private String name;
    public ArrayList<Expenses> arrayE;

    public Expenses(String name, double money){
        this.name = name;
        this.money = money;
        arrayE = new ArrayList<>();
    }

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
}
