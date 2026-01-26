/*
 * PRACTICE QUESTION: Stock Market Monitoring System
 */

import java.util.ArrayList;
import java.util.List;

public class StockMarket_Observer {

    // 1. Observer Interface
    public interface Observer {
        void update(String stockName, double price);
    }

    // 2. Subject Interface
    public interface Subject {
        void subscribe(Observer observer);
        void unsubscribe(Observer observer);
        void notifyObservers();
    }

    // 3. Concrete Subject: Stock
    public static class Stock implements Subject {
        private String name;
        private double price;
        private List<Observer> observers = new ArrayList<>();

        public Stock(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public void setPrice(double newPrice) {
            this.price = newPrice;
            notifyObservers();
        }

        @Override
        public void subscribe(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void unsubscribe(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(name, price);
            }
        }
    }

    // 4. Concrete Observer: MobileApp
    public static class MobileApp implements Observer {
        private String appName;

        public MobileApp(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String stockName, double price) {
            System.out.println("Mobile App Notification (" + appName + "): Price of " + stockName + " is now " + price);
        }
    }

    // 5. Concrete Observer: EmailAlert
    public static class EmailAlert implements Observer {
        private String email;

        public EmailAlert(String email) {
            this.email = email;
        }

        @Override
        public void update(String stockName, double price) {
            System.out.println("Email Alert (" + email + "): Price of " + stockName + " changed to " + price);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Observer Pattern Practice ---");

        // 1. Create a Stock (Subject)
        Stock appleStock = new Stock("Apple", 150.0);

        // 2. Create Observers
        MobileApp appUser = new MobileApp("Robinhood");
        EmailAlert emailUser = new EmailAlert("user@example.com");

        // 3. Subscribe them
        appleStock.subscribe(appUser);
        appleStock.subscribe(emailUser);

        // 4. Update Price
        System.out.println("\nUpdating price to 155.0...");
        appleStock.setPrice(155.0);

        // 5. Unsubscribe one
        appleStock.unsubscribe(emailUser);

        // 6. Update Price again
        System.out.println("\nUpdating price to 160.0...");
        appleStock.setPrice(160.0);
    }
}
