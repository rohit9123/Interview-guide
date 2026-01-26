/*
 * PRACTICE QUESTION: E-Commerce Discount System
 *
 * Scenario:
 * Implement a discount system where different discount strategies can be applied to a cart.
 */

public class StrategyPattern_Discount {

    // 1. Strategy Interface
    public interface DiscountStrategy {
        double applyDiscount(double totalAmount);
    }

    // 2. Concrete Strategy: No Discount
    public static class NoDiscountStrategy implements DiscountStrategy {
        @Override
        public double applyDiscount(double totalAmount) {
            return totalAmount;
        }
    }

    // 3. Concrete Strategy: Percentage Discount
    public static class PercentageDiscountStrategy implements DiscountStrategy {
        private double percentage;

        public PercentageDiscountStrategy(double percentage) {
            this.percentage = percentage;
        }

        @Override
        public double applyDiscount(double totalAmount) {
            return totalAmount - (totalAmount * percentage / 100);
        }
    }

    // 4. Concrete Strategy: Flat Discount
    public static class FlatDiscountStrategy implements DiscountStrategy {
        private double flatAmount;

        public FlatDiscountStrategy(double flatAmount) {
            this.flatAmount = flatAmount;
        }

        @Override
        public double applyDiscount(double totalAmount) {
            return Math.max(0, totalAmount - flatAmount);
        }
    }

    // 5. Context
    public static class ShoppingCart {
        private DiscountStrategy discountStrategy;
        private double totalAmount;

        public ShoppingCart(double totalAmount) {
            this.totalAmount = totalAmount;
            this.discountStrategy = new NoDiscountStrategy(); // Default
        }

        public void setDiscountStrategy(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy;
        }

        public void checkout() {
            double finalPrice = discountStrategy.applyDiscount(totalAmount);
            System.out.println("Original: $" + totalAmount +
                    " | Final (with " + discountStrategy.getClass().getSimpleName() + "): $" + finalPrice);
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Strategy Pattern: Discount System ---");

        ShoppingCart cart = new ShoppingCart(1000.0);

        // 1. No Discount
        cart.checkout();

        // 2. Flat Discount of $100
        cart.setDiscountStrategy(new FlatDiscountStrategy(100));
        cart.checkout();

        // 3. 20% Off
        cart.setDiscountStrategy(new PercentageDiscountStrategy(20));
        cart.checkout();
    }
}
