# Null Object Design Pattern

**Type**: Behavioral Pattern

## 1. What is the Null Object Pattern?

The Null Object Pattern provides a "do-nothing" object instead of using `null`. It allows you to treat a "missing" object just like any other object, avoiding null pointer checks.

**Core Idea**: Instead of returning `null`, return an object that implements the expected interface but does nothing (or provides default behavior).

## 2. When to use it?
*   **Avoid Null Checks**: When you want to get rid of repetitive `if (obj != null)` checks.
*   **Default Behavior**: When a missing collaborator should simply do nothing.
*   **Testing**: Can be used as a simple mock object.

## 3. Real-World Analogy
Imagine a **Company Hierarchy**:
- A **Manager** has a list of reports.
- A **Junior Developer** has no reports.
- Instead of returning `null` for reports, the Junior Developer returns an `EmptyReportList` object. You can still call `.size()` or iterate over it without checking for null.

## 4. Why use it? (Before vs After)

### Before (Classic approach)
```java
Vehicle vehicle = VehicleFactory.getVehicle("Car");
if (vehicle != null) {
    System.out.println(vehicle.getTankCapacity());
}
```

### After (Null Object approach)
```java
Vehicle vehicle = VehicleFactory.getVehicle("Unknown");
// No null check needed!
System.out.println(vehicle.getTankCapacity()); 
```

## 5. Java Example: Vehicle System

```java
// 1. Interface
interface Vehicle {
    int getTankCapacity();
    int getSeatingCapacity();
}

// 2. Concrete Object
class Car implements Vehicle {
    public int getTankCapacity() { return 50; }
    public int getSeatingCapacity() { return 5; }
}

// 3. Null Object
class NullVehicle implements Vehicle {
    public int getTankCapacity() { return 0; }
    public int getSeatingCapacity() { return 0; }
}

// 4. Factory
class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if ("Car".equalsIgnoreCase(type)) return new Car();
        return new NullVehicle(); // Return Null Object instead of null
    }
}
```

## 6. Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Clean Code**: Reduces boilerplate null checks. | **Hiding Bugs**: If an object is missing due to an error, a Null Object might hide the bug instead of throwing an NPE. |
| **Stability**: Prevents `NullPointerException`. | **Extra Classes**: Requires creating an extra class for every interface. |
| **Uniformity**: Client code treats all objects identically. | |
