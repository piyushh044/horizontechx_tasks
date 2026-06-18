import java.util.Scanner;

public class Main{

    static Market market=new Market();

    static User user=new User("Trader",10000.0);

    static Scanner sc=new Scanner(System.in);

    public static void main(String[] args){


        System.out.println("===== SIMPLE STOCK TRADING PLATFORM =====");
        System.out.println("Welcome, "+user.username+"! Starting cash: $"+user.cash);

        while(true){


            market.updatePrices();

            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            String choice=sc.nextLine();


            if(choice.equals("1")){
                viewMarket();
            }

            else if(choice.equals("2")){
                buyStock();
            }

            else if(choice.equals("3")){
                sellStock();
            }

            else if(choice.equals("4")){
                viewPortfolio();
            }

            else if(choice.equals("5")){
                System.out.println("Goodbye!");
                break;
            }

            else{
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }

    static void viewMarket(){


        market.showMarket();
    }

    static void buyStock(){

        market.showMarket();

        System.out.print("Enter the number of the stock to buy: ");

        int stockNumber=sc.nextInt();

        sc.nextLine();

        Stock selectedStock=market.stocks.get(stockNumber-1);

        System.out.print("How many shares? ");
        int quantity=sc.nextInt();
        sc.nextLine();

        double cost=selectedStock.price*quantity;

        if(user.cash>=cost){


            user.cash-=cost;
            user.addStock(selectedStock,quantity);

            System.out.printf("Bought %d shares of %s for $%.2f%n",quantity,selectedStock.symbol,cost);

        }


        else{


            System.out.println("Not enough cash.");
        }
    }

    static void sellStock(){

        if(user.myStocks.isEmpty()){

            System.out.println("You own no stocks.");
            return;

        }

        System.out.println("Your stocks:");

        for(int i=0;i<user.myStocks.size();i++){


            Stock stock=user.myStocks.get(i);
            System.out.printf("%d. %s (%s) - %d shares @ $%.2f%n", i+1,stock.symbol,stock.name,user.quantity.get(i),stock.price);
        }

        System.out.print("Enter number of the stock to sell: ");

        int stockNumber=sc.nextInt();
        sc.nextLine();

        Stock selectedStock=user.myStocks.get(stockNumber-1);
        int ownedQuantity=user.quantity.get(stockNumber-1);



        System.out.print("How many shares to sell? ");
        int sellQuantity=sc.nextInt();
        sc.nextLine();



        if(sellQuantity>ownedQuantity){


            System.out.println("You don't have that many shares. Selling all.");
            sellQuantity=ownedQuantity;


        }

        double money=selectedStock.price*sellQuantity;
        user.cash+=money;

        user.quantity.set(stockNumber-1,ownedQuantity-sellQuantity);

        System.out.printf("Sold %d shares of %s for $%.2f%n",sellQuantity,selectedStock.symbol,money);
    }

    static void viewPortfolio(){

        System.out.println("\n=== Portfolio for "+user.username+" ===");
        System.out.println("Cash: $"+String.format("%.2f",user.cash));

        if(user.myStocks.isEmpty()){

            System.out.println("No stocks owned.");
        }
        else{

            for(int i=0;i<user.myStocks.size();i++){
                
                Stock stock=user.myStocks.get(i);
                int quantity=user.quantity.get(i);
                double value=stock.price*quantity;

                System.out.printf("%s (%s): %d shares @ $%.2f = $%.2f%n",
                        stock.symbol,stock.name,quantity,stock.price,value);
            }
        }

        System.out.printf("Total portfolio value: $%.2f%n",user.totalValue());
    }
}