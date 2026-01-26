# SOLID Principles: The Professional Guide

**Goal**: Write code that is robust, maintainable, and flexible.
**The Scenario**: We are building an **E-Commerce Order System**.

---

## 1. Single Responsibility Principle (SRP)
> **"A class should have only one reason to change."**

**The Mistake**: Putting everything into a single `Order` class.

### ❌ Bad Example (God Class)
```java
class Order {
    public void calculateTotalSum() { /* logic */ }
    public void getItems() { /* logic */ }
    public void addItem() { /* logic */ }
    public void deleteItem() { /* logic */ }

    // Violation: Printing logic belongs to presentation layer
    public void printInvoice() {
        System.out.println("Printing Invoice...");
    }

    // Violation: Persistence logic belongs to database layer
    public void saveToDatabase() {
        System.out.println("Saving to DB...");
    }
}
```
*Why is this bad?* If you change the Database (SQL to Mongo), you edit the `Order` class. If you change the Invoice format (PDF to HTML), you edit the `Order` class.

### ✅ Good Example (Separated Responsibilities)
Split the responsibilities into focused classes.

```java
// 1. Core Business Logic
class Order {
    public void calculateTotalSum() { /* ... */ }
}

// 2. Persistence Responsibility
class OrderRepository {
    public void save(Order order) { /* DB logic */ }
}

// 3. Presentation Responsibility
class InvoicePrinter {
    public void print(Order order) { /* Printing logic */ }
}
```

---

## 2. Open/Closed Principle (OCP)
> **"Software entities should be open for extension, but closed for modification."**

**The Mistake**: Using `if-else` or `switch` statements to handle different types.

### ❌ Bad Example (Modifying existing code)
Imagine we want to process payments.

```java
class PaymentProcessor {
    public void process(String type) {
        if (type.equals("CreditCard")) {
            // Process CC
        } else if (type.equals("PayPal")) {
            // Process PayPal
        }
        // Violation: If we add "Bitcoin", we MUST modify this class!
    }
}
```

### ✅ Good Example (Polymorphism)
Use interfaces so you can add new types by adding *new* classes, not touching old ones.

```java
interface PaymentMethod {
    void pay();
}

class CreditCard implements PaymentMethod {
    public void pay() { /* CC logic */ }
}

class PayPal implements PaymentMethod {
    public void pay() { /* PayPal logic */ }
}

// New feature? Just add a new class!
class Bitcoin implements PaymentMethod {
    public void pay() { /* Bitcoin logic */ }
}

class PaymentProcessor {
    public void process(PaymentMethod method) {
        method.pay(); // Works for any payment method (OCP compliant)
    }
}
```

---

## 3. Liskov Substitution Principle (LSP)
> **"Subtypes must be substitutable for their base types without breaking the program."**

**The Mistake**: Creating a subclass that throws exceptions for methods it can't support.

### ❌ Bad Example (Broken Hierarchy)
```java
class Order {
    public void addItem(String item) { /* adds item */ }
}

class ReadOnlyOrder extends Order {
    @Override
    public void addItem(String item) {
        // Violation: Child breaks the expected behavior of the Parent
        throw new UnsupportedOperationException("Can't add to read-only order");
    }
}

// Client code crashes
Order order = new ReadOnlyOrder();
order.addItem("Apple"); // CRASH!
```

### ✅ Good Example (Composition or distinct interfaces)
Don't inherit if you can't fulfill the contract. Separate the concepts.

```java
interface ReadableOrder {
    void getItems();
}

interface WritableOrder extends ReadableOrder {
    void addItem(String item);
}

class StandardOrder implements WritableOrder {
    public void verify() { /* ... */ }
    public void addItem(String item) { /* ... */ }
    public void getItems() { /* ... */ }
}

class ArchivedOrder implements ReadableOrder {
    public void getItems() { /* ... */ }
    // No addItem method exists, so no risk of calling it!
}
```

---

## 4. Interface Segregation Principle (ISP)
> **"Clients should not be forced to depend on methods they do not use."**

**The Mistake**: One massive interface for all users.

### ❌ Bad Example (Fat Interface)
```java
interface OrderInterface {
    void placeOrder();
    void processPayment(); // Only for customer
    void shipOrder();      // Only for warehouse
    void reconcile();      // Only for admin
}

class Customer implements OrderInterface {
    public void placeOrder() { /* ok */ }
    public void shipOrder() { /* logic? Empty? user can't ship! */ }
    public void reconcile() { /* logic? logic? */ }
}
```

### ✅ Good Example (Role Interfaces)
Break it down by actor.

```java
interface Buyer {
    void placeOrder();
    void processPayment();
}

interface Shipper {
    void shipOrder();
}

interface Admin {
    void reconcile();
}

// Now the implementations are clean
class Customer implements Buyer {
    public void placeOrder() { ... }
    public void processPayment() { ... }
}

class WarehouseWorker implements Shipper {
    public void shipOrder() { ... }
}
```

---

## 5. Dependency Inversion Principle (DIP)
> **"High-level modules should not depend on low-level modules. Both should depend on abstractions."**

**The Mistake**: `new`ing concrete classes inside your business logic.

### ❌ Bad Example (Tight Coupling)
```java
class OrderService {
    // Violation: Dependent directly on concrete MySQL implementation
    private MySQLDatabase database = new MySQLDatabase();

    public void save(Order order) {
        database.insert(order);
    }
}
```
*Why is this bad?* You cannot easily switch to PostgreSQL, and you cannot unit test `OrderService` without a real database.

### ✅ Good Example (Dependency Injection)
Depend on an interface, and inject the implementation.

```java
// Abstraction
interface Database {
    void save(Order order);
}

class MySQLDatabase implements Database {
    public void save(Order order) { /* SQL logic */ }
}

class PostgreSQLDatabase implements Database {
    public void save(Order order) { /* Postgres logic */ }
}

// Low-level details (DB) depend on Abstraction (Database interface)
// High-level policy (OrderService) depends on Abstraction (Database interface)
class OrderService {
    private Database database;

    // Injection via Constructor
    public OrderService(Database database) {
        this.database = database;
    }

    public void save(Order order) {
        database.save(order);
    }
}

// Usage
OrderService service = new OrderService(new PostgresDatabase());
```

---

## 6. Deep Dive Examples (Alternative Perspectives)

Sometimes the "Classic" examples help click the concept better than the enterprise ones.

### 1a. SRP: The "Swiss Army Knife" vs. Toolkit
**Scenario**: You have an `Employee` class that does everything.

**❌ Bad Way (Mixed Responsibilities)**
```java
class Employee {
    public void calculatePay() { /* accounting logic */ }
    public void reportHours() { /* HR logic */ }
    public void saveToDatabase() { /* backend tech logic */ }
}
```
*Why this is bad*: The CFO asks for a change in pay calculation. You edit this file. The CTO asks for a Database change. You edit **the same file**. Merge conflicts happen, and bugs spread.

**✅ Good Way (Delegated Responsibility)**
```java
class PayCalculator {
    public void calculatePay(Employee e) { ... }
}

class HourReporter {
    public void reportHours(Employee e) { ... }
}

class EmployeeRepository {
    public void save(Employee e) { ... }
}

class Employee { 
    // Just the data
    String name;
    double salary;
}
```

### 2a. OCP: The Discount Calculator (Strategy Pattern)
**Scenario**: You have different discount rules (Seasonal, Senior, Employee).

**❌ Bad Way (Modification)**
```java
public double calculate(double price, String type) {
    if (type.equals("Christmas")) return price * 0.9;
    if (type.equals("BlackFriday")) return price * 0.5; // Changing code every sale?
    return price;
}
```

**✅ Good Way (Extension - OCP)**
```java
interface DiscountStrategy {
    double apply(double price);
}

class ChristmasDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.9; }
}

class BlackFridayDiscount implements DiscountStrategy {
    public double apply(double price) { return price * 0.5; }
}

class PriceCalculator {
    public double calculate(double price, DiscountStrategy strategy) {
        return strategy.apply(price);
    }
}
// Usage: calculate(100, new ChristmasDiscount());
```
*Why this is better*: Marketing invents a "New Year Sale"? You just create `NewYearDiscount.java`. You never touch `PriceCalculator` again.


### 3a. LSP: The "Bird" Problem (Behavioral Inheritance)
**Scenario**: We have a `Bird` class. We assume all birds fly.

**❌ The Violation**
```java
class Bird {
    public void fly() { System.out.println("I can fly!"); }
}

class Sparrow extends Bird { /* OK */ }

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
```
**Why it fails LSP**: A client code looping through `List<Bird>` expects all of them to `fly()`. If a `Penguin` is in the list, the program crashes. **The subtype (Penguin) cannot substitute the base type (Bird).**

**✅ The Fix**
Separate the capabilities. NOT all birds fly.

```java
class Bird { /* common bird stuff like eat() */ }

interface Flyable {
    void fly();
}

class Sparrow extends Bird implements Flyable {
    public void fly() { /* ... */ }
}

class Penguin extends Bird {
    // No fly method here!
}
```
Now, if you want a list of things that fly, you ask for `List<Flyable>`, and Penguins are physically excluded from that list. No crashes.


### 4a. ISP: The "Multi-Function Printer" Problem
**Scenario**: You have a big office printer (Print, Scan, Fax) and a simple home printer (Print only).

**❌ Bad Way (Forcing dependencies)**
```java
interface SmartDevice {
    void print();
    void fax();
    void scan();
}

class BasicPrinter implements SmartDevice {
    public void print() { /* Yes */ }
    public void fax() { throw new RuntimeException("I can't fax!"); } // Violation
    public void scan() { throw new RuntimeException("I can't scan!"); } // Violation
}
```

**✅ Good Way (Segregated Interfaces)**
```java
interface Printer { void print(); }
interface Scanner { void scan(); }
interface Fax { void fax(); }

class SuperXeroxMachine implements Printer, Scanner, Fax {
    /* Implements all 3 */
}

class BasicPrinter implements Printer {
    public void print() { /* Works happily */ }
    // No fax/scan methods required!
}
```


### 5a. DIP: The "Light Bulb & Switch" Example
**Scenario**: You have a Switch that turns on a Bulb.

**❌ Bad Way**
```java
class LightBulb {
    public void turnOn() { ... }
    public void turnOff() { ... }
}

class ElectricPowerSwitch {
    // Hard dependency on LightBulb
    public LightBulb bulb; 
    public ElectricPowerSwitch(LightBulb b) { this.bulb = b; }
    
    public void press() {
        if (on) bulb.turnOff();
        else bulb.turnOn();
    }
}
```
*Why*: You can't use this `ElectricPowerSwitch` to turn on a `Fan` or `AC`. It's hardcoded to `LightBulb`.

**✅ Good Way**
```java
interface Switchable {
    void turnOn();
    void turnOff();
}

class LightBulb implements Switchable { ... }
class Fan implements Switchable { ... }

class ElectricPowerSwitch {
    public Switchable device; // Depends on Abstraction
    
    public ElectricPowerSwitch(Switchable device) {
        this.device = device;
    }
}
```
Now one Switch can control *anything* that is `Switchable`.
