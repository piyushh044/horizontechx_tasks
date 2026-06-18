import java.util.ArrayList;
import java.util.Random;

public class Market{

    public ArrayList<Stock> stocks=new ArrayList<>();
    private Random random=new Random();

    public Market(){

        stocks.add(new Stock("AAPL","Apple Inc.",150.0));
        stocks.add(new Stock("GOOG","Alphabet Inc.",2800.0));
        stocks.add(new Stock("AMZN","Amazon.com",3400.0));
        stocks.add(new Stock("TSLA","Tesla Inc.",900.0));
    }

    public void updatePrices(){

        for(Stock stock:stocks){


            double changePercent=(random.nextDouble()*10)-5;
            double newPrice=stock.price*(1+changePercent/100);

            stock.price=Math.round(newPrice*100.0)/100.0;
        }
    }

    public Stock getStock(String symbol){

        for(int i=0;i<stocks.size();i++){

            if(stocks.get(i).symbol.equalsIgnoreCase(symbol)){

                return stocks.get(i);
            }
        }

        return null;
    }

    public void showMarket(){

        System.out.println("Symbol\tName\t\tPrice");
        
        System.out.println("----------------------------------");

        for(int i=0;i<stocks.size();i++){


            Stock stock=stocks.get(i);
            System.out.printf("%d. %s\t%s\t$%.2f%n",i+1,stock.symbol,stock.name,stock.price);
        }
    }
}