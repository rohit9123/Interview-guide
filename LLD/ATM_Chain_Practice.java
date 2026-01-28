/**
 * QUESTION:
 * Implement an ATM Withdrawal System using the Chain of Responsibility Pattern.
 * 
 * Requirements:
 * 1. The ATM should support denominations of 2000, 500, and 100.
 * 2. If an amount is requested (e.g., 3700), the system should:
 * - Dispense 1 note of 2000 (Remaining: 1700)
 * - Dispense 3 notes of 500 (Remaining: 200)
 * - Dispense 2 notes of 100 (Remaining: 0)
 * - If any remaining amount cannot be dispensed (e.g., total 3750, remaining
 * 50),
 * print "Amount cannot be fully dispensed in available denominations."
 * 
 * 3. Use an abstract class `BanknoteHandler` and concrete implementations for
 * each denomination.
 * 
 * TODO: Complete the logic in the handlers and the setup in Main.
 */

abstract class BanknoteHandler {
    protected BanknoteHandler nextHandler;

    public void setNext(BanknoteHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void withdraw(int amount);
}

class TwoThousandHandler extends BanknoteHandler {
    @Override
    public void withdraw(int amount) {
        // TODO: Implement logic to handle 2000 notes and pass the rest
    }
}

class FiveHundredHandler extends BanknoteHandler {
    @Override
    public void withdraw(int amount) {
        // TODO: Implement logic to handle 500 notes and pass the rest
    }
}

class OneHundredHandler extends BanknoteHandler {
    @Override
    public void withdraw(int amount) {
        // TODO: Implement logic to handle 100 notes and pass the rest
        // If amount still > 0, print "Cannot dispense..."
    }
}

public class ATM_Chain_Practice {
    public static void main(String[] args) {
        // TODO: Initialize the handlers

        // TODO: Chain them together (2000 -> 500 -> 100)

        System.out.println("Processing withdrawal for $3800:");
        // TODO: Call the chain

        System.out.println("\nProcessing withdrawal for $3850:");
        // TODO: Call the chain
    }
}
