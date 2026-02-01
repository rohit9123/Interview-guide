# Command Design Pattern

## Concept
The **Command Design Pattern** encapsulates a request as an object, thereby letting you parameterize other objects with different requests, queue or log requests, and support undoable operations.

It decouples the object that invokes the operation (**Invoker**) from the one that knows how to perform it (**Receiver**).

### Key Components
1.  **Command**: Interface declarying the `execute()` method.
2.  **ConcreteCommand**: Implements `execute()` by calling actions on the Receiver.
3.  **Receiver**: The class that does the actual work (e.g., Light, Fan).
4.  **Invoker**: Holds the command and asks it to execute (e.g., Remote Control).
5.  **Client**: Creates the ConcreteCommand and associates it with the Receiver.

---

## Real-World Analogy: Restaurant Ordering
1.  **Customer (Client)**: Gives an order to the Waiter.
2.  **Order (Command)**: The order slip encapsulates the request (Burger, Fries).
3.  **Waiter (Invoker)**: Takes the order and places it on the counter. The waiter doesn't know how to cook.
4.  **Chef (Receiver)**: Picks up the order and cooks the meal.

---

## Example (Java): Simple Remote Control

### 1. Receiver
The actual device that does the work.
```java
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}
```

### 2. Command Interface
```java
interface Command {
    void execute();
}
```

### 3. Concrete Command
Connects the Receiver with the action.
```java
class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    public void execute() {
        light.turnOn();
    }
}
```

### 4. Invoker
The remote control knows *how* to press a button, but doesn't know *what* the button does.
```java
class SimpleRemoteControl {
    Command slot;

    public void setCommand(Command command) {
        this.slot = command;
    }

    public void buttonWasPressed() {
        slot.execute();
    }
}
```

### 5. Client
Assembles everything.
```java
public class Client {
    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl(); // Invoker
        Light light = new Light();                              // Receiver
        LightOnCommand lightOn = new LightOnCommand(light);     // Command
        
        remote.setCommand(lightOn);
        remote.buttonWasPressed();
    }
}
```
