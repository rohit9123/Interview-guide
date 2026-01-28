# Proxy Design Pattern

**Type**: Structural Pattern

## 1. What is the Proxy Pattern?

The Proxy pattern provides a surrogate or placeholder for another object to control access to it. 

**Core Idea**: Instead of interacting with the "Real Object" directly, you interact with a "Proxy" that acts as an intermediary.

## 2. When to use it?
*   **Virtual Proxy (Lazy Initialization)**: Delay the creation of a heavy object (e.g., a large image or database connection) until it's actually needed.
*   **Protection Proxy (Access Control)**: Check if a user has permission to call a method on the real object.
*   **Remote Proxy**: Represent an object that exists in a different memory space or machine.
*   **Logging/Caching Proxy**: Add logging or caching behavior without modifying the real object.

## 3. Real-World Analogy
**Credit Card**:
A credit card is a proxy for your bank account. Instead of carrying cash (the real object), you carry a card (the proxy). When you pay, the card checks if you have balance and then executes the transaction.

## 4. Structure
1.  **Subject (Interface)**: Defines the common interface for the RealSubject and the Proxy.
2.  **RealSubject**: The heavy/restricted object that the proxy represents.
3.  **Proxy**: Maintains a reference to the RealSubject and controls access to it.

## 5. Java Example: Image Loading (Virtual Proxy)

```java
// 1. Subject
interface Image {
    void display();
}

// 2. RealSubject (Heavy Object)
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(); // Expensive operation
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName + " from disk...");
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// 3. Proxy (Lazy Loader)
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName); // Load only when needed
        }
        realImage.display();
    }
}
```

## 6. Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Control**: You can manage the lifecycle of the real object. | **Complexity**: Adds more classes and abstraction. |
| **Performance**: Improves performance (Lazy loading, Caching). | **Latency**: Might add a slight delay due to the intermediary step. |
| **Security**: Provides a layer of protection. | |
