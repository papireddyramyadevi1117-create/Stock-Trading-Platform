import java.util.*;
class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

class Transaction {
    private String type;
    private String stockSymbol;
    private int quantity;

    public Transaction(String type, String stockSymbol, int quantity) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return type + " " + quantity + " shares of " + stockSymbol;
    }
}

class User {
    private String name;
    private double balance;
    private HashMap<String, Integer> portfolio;
    private ArrayList<Transaction> transactions;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        portfolio = new HashMap<>();
        transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;

        if (cost > balance) {
            System.out.println("Insufficient Balance!");
            return;
        }

        balance -= cost;

        portfolio.put(
                stock.getSymbol(),
                portfolio.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        transactions.add(
                new Transaction("BUY",
                        stock.getSymbol(),
                        quantity)
        );

        System.out.println("Stock Purchased Successfully!");
    }

    public void sellStock(Stock stock, int quantity) {

        if (!portfolio.containsKey(stock.getSymbol())
                || portfolio.get(stock.getSymbol()) < quantity) {

            System.out.println("Not enough shares!");
            return;
        }

        balance += stock.getPrice() * quantity;

        portfolio.put(
                stock.getSymbol(),
                portfolio.get(stock.getSymbol()) - quantity
        );

        transactions.add(
                new Transaction("SELL",
                        stock.getSymbol(),
                        quantity)
        );

        System.out.println("Stock Sold Successfully!");
    }

    public void displayPortfolio() {
        System.out.println("\nPortfolio:");

        for (String stock : portfolio.keySet()) {
            System.out.println(stock + " : "
                    + portfolio.get(stock) + " shares");
        }

        System.out.println("Balance: ₹" + balance);
    }

    public void displayTransactions() {
        System.out.println("\nTransaction History:");

        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Stock apple = new Stock("AAPL", 150);
        Stock tesla = new Stock("TSLA", 250);

        User user = new User("Ramya", 100000);

        while (true) {

            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("AAPL : ₹150");
                    System.out.println("TSLA : ₹250");
                    break;

                case 2:
                    System.out.println("1. AAPL");
                    System.out.println("2. TSLA");

                    int buyChoice = sc.nextInt();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    if (buyChoice == 1)
                        user.buyStock(apple, qty);
                    else if (buyChoice == 2)
                        user.buyStock(tesla, qty);

                    break;

                case 3:
                    System.out.println("1. AAPL");
                    System.out.println("2. TSLA");

                    int sellChoice = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();

                    if (sellChoice == 1)
                        user.sellStock(apple, qty);
                    else if (sellChoice == 2)
                        user.sellStock(tesla, qty);

                    break;

                case 4:
                    user.displayPortfolio();
                    break;

                case 5:
                    user.displayTransactions();
                    break;

                case 6:
                    System.out.println("Thank You!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
