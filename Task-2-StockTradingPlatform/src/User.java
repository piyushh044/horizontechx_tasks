import java.util.ArrayList;

public class User{

    public String username;
    public double cash;

    public ArrayList<Stock> myStocks=new ArrayList<>();

    public ArrayList<Integer> quantity=new ArrayList<>();


    public User(String username,double initialCash){

        this.username=username;
        cash=initialCash;
    }

    public void addStock(Stock stock,int quantity){

        myStocks.add(stock);
        this.quantity.add(quantity);
    }

    public void sellStock(Stock stock,int sellQuantity){

    }

    public double totalValue(){

        double total=cash;

        for(int i=0;i<myStocks.size();i++){
            
            total+=myStocks.get(i).price*quantity.get(i);
        }

        return total;
    }
}