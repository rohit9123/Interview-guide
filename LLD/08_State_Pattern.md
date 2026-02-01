# State Design Pattern

## Concept
The **State Design Pattern** allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

It is particularly useful when:
1.  An object's behavior depends on its state, and it must change its behavior at run-time depending on that state.
2.  Operations have large, multipart conditional statements that depend on the object's state.

Instead of writing massive `if-else` or `switch` statements to handle different states (which violates the Open/Closed Principle), we create separate classes for each state and delegate the behavior to the current state object.

---

## Real-World Analogy: Vending Machine
Imagine a Vending Machine. It behaves differently based on its state:
-   **Idle State**: You can insert a coin. You cannot dispense product immediately.
-   **Has Coin State**: You can select a product. You can eject the coin. You cannot insert another coin (assuming single coin slot).
-   **Dispensing State**: It gives you the item. You cannot insert a coin or select another product.

The machine transitions between these states.

---

## Example (Java)

### Without State Pattern (The `switch` mess)
```java
class VendingMachine {
    final static int IDLE = 0;
    final static int HAS_COIN = 1;
    int state = IDLE;

    void insertCoin() {
        if (state == IDLE) {
            state = HAS_COIN;
            System.out.println("Coin inserted");
        } else if (state == HAS_COIN) {
            System.out.println("Coin already present");
        }
    }
    // ... extensive if-else for every action
}
```

### With State Pattern

**1. State Interface**
Define the actions available across states.
```java
interface VendingMachineState {
    void insertCoin();
    void ejectCoin();
    void selectProduct();
    void dispense();
}
```

**2. Concrete States**
Implement behavior specific to each state.

```java
// Idle State
class IdleState implements VendingMachineState {
    VendingMachine vm;
    public IdleState(VendingMachine vm) { this.vm = vm; }

    public void insertCoin() {
        System.out.println("Coin inserted.");
        vm.setState(vm.getHasCoinState());
    }
    public void ejectCoin() { System.out.println("No coin to eject."); }
    public void selectProduct() { System.out.println("Insert coin first."); }
    public void dispense() { System.out.println("Select product first."); }
}

// HasCoin State
class HasCoinState implements VendingMachineState {
    VendingMachine vm;
    public HasCoinState(VendingMachine vm) { this.vm = vm; }

    public void insertCoin() { System.out.println("Coin already inserted."); }
    public void ejectCoin() {
        System.out.println("Coin returned.");
        vm.setState(vm.getIdleState());
    }
    public void selectProduct() {
        System.out.println("Product selected.");
        vm.setState(vm.getDispensingState());
    }
    public void dispense() { System.out.println("Select product first."); }
}

// Dispensing State
class DispensingState implements VendingMachineState {
    VendingMachine vm;
    public DispensingState(VendingMachine vm) { this.vm = vm; }

    public void insertCoin() { System.out.println("Please wait, dispensing."); }
    public void ejectCoin() { System.out.println("Already dispensing."); }
    public void selectProduct() { System.out.println("Already dispensing."); }
    public void dispense() {
        System.out.println("Product dispensed!");
        vm.setState(vm.getIdleState());
    }
}
```

**3. Context Class**
Maintains the current state.

```java
class VendingMachine {
    VendingMachineState idleState;
    VendingMachineState hasCoinState;
    VendingMachineState dispensingState;

    VendingMachineState currentState;

    public VendingMachine() {
        idleState = new IdleState(this);
        hasCoinState = new HasCoinState(this);
        dispensingState = new DispensingState(this);
        
        currentState = idleState; // Initial state
    }

    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    // Getters for states...
    public VendingMachineState getIdleState() { return idleState; }
    public VendingMachineState getHasCoinState() { return hasCoinState; }
    public VendingMachineState getDispensingState() { return dispensingState; }

    // Actions delegate to current state
    public void insertCoin() { currentState.insertCoin(); }
    public void ejectCoin() { currentState.ejectCoin(); }
    public void selectProduct() { currentState.selectProduct(); }
    public void dispense() { currentState.dispense(); }
}
```
