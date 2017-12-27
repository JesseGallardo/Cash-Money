import java.util.ArrayList;

public class Income extends BudgetSheet{
    private double money;
    private String name;

    public Income(String name, double money){
        this.name = name;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }


}
