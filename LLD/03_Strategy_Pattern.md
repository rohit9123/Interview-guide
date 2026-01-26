# Strategy Design Pattern

**Type**: Behavioral Pattern

## 1. What is the Strategy Pattern?

The Strategy Pattern is a behavioral design pattern that enables selecting an algorithm at runtime. Instead of implementing a single algorithm directly, code receives run-time instructions as to which in a family of algorithms to use.

**Core Idea**: Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## 2. When to use it?

*   **Multiple Algorithms**: When you have many classes that differ only in their behavior.
*   **Runtime Switching**: When you need to use different variants of an algorithm within an object and be able to switch between them during runtime.
*   **Isolation**: When you want to isolate the business logic of a class from the implementation details of algorithms that may not be as important in the context of that logic.
*   **Avoiding Conditionals**: When your class has a massive conditional operator (like a huge `switch` or `if-else` block) that switches between different variants of the same algorithm.

## 3. Real-World Analogy

**Navigation System**:
Imagine a navigation app. You want to go from Point A to Point B.
*   If you are driving, the app uses a **Road Strategy**.
*   If you are walking, it uses a **Walking Strategy**.
*   If you are taking a bus, it uses a **Public Transport Strategy**.

The "Navigator" (Context) doesn't care *how* the route is calculated, it just delegates the work to the selected "Routing Strategy".

## 4. Structure (UML Concept)

1.  **Context**: Maintains a reference to one of the concrete strategies and communicates with this object only via the strategy interface.
2.  **Strategy Interface**: Declares a method common to all supported versions of some algorithm.
3.  **Concrete Strategies**: Implement different variations of an algorithm the Strategy interface uses.

## 5. Java Example: Navigation System

Let's implement the Navigation analogy.

### Step 1: Strategy Interface

```java
// The Strategy Interface
public interface RouteStrategy {
    void buildRoute(String a, String b);
}
```

### Step 2: Concrete Strategies

```java
// Strategy A: Road
public class RoadStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String a, String b) {
        System.out.println("Map Route: Road from " + a + " to " + b + ". Time: 15 mins.");
    }
}

// Strategy B: Walking
public class WalkingStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String a, String b) {
        System.out.println("Map Route: Walking path from " + a + " to " + b + ". Time: 40 mins.");
    }
}

// Strategy C: Public Transport
public class PublicTransportStrategy implements RouteStrategy {
    @Override
    public void buildRoute(String a, String b) {
        System.out.println("Map Route: Bus/Train from " + a + " to " + b + ". Time: 25 mins.");
    }
}
```

### Step 3: Context

```java
// The Navigator (Context)
public class Navigator {
    private RouteStrategy routeStrategy;

    // We can set the strategy at runtime
    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public void buildRoute(String a, String b) {
        if (routeStrategy == null) {
            System.out.println("Transportation mode not selected.");
            return;
        }
        routeStrategy.buildRoute(a, b);
    }
}
```

### Step 4: Client Code

```java
public class Main {
    public static void main(String[] args) {
        Navigator nav = new Navigator();
        String start = "Home";
        String end = "Office";

        // User selects Car
        System.out.println("--- User selects Car ---");
        nav.setRouteStrategy(new RoadStrategy());
        nav.buildRoute(start, end);

        // User switches to Walking
        System.out.println("\n--- User switches to Walking ---");
        nav.setRouteStrategy(new WalkingStrategy());
        nav.buildRoute(start, end);
    }
}
```

## 6. Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Open/Closed Principle**: You can introduce new strategies without having to change the context. | **Complexity**: If you only have a few algorithms that rarely change, there's no real reason to overcomplicate the program with new classes and interfaces. |
| **Runtime Swapping**: You can swap algorithms used inside an object at runtime. | **Client Awareness**: Clients must be aware of the differences between strategies to be able to select the proper one. |
| **Isolation**: Implementation details of an algorithm are isolated from the code that uses it. | |
