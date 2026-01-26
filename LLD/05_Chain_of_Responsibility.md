# Chain of Responsibility Design Pattern

**Type**: Behavioral Pattern

## 1. What is the Chain of Responsibility Pattern?

The Chain of Responsibility pattern allows you to pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

**Core Idea**: Decouple the sender of a request from its receiver by giving multiple objects a chance to handle the request.

## 2. When to use it?

*   **Multiple Handlers**: When more than one object may handle a request, and the handler isn't known *a priori*.
*   **Dynamic Handling**: When you want to issue a request to one of several objects without specifying the receiver explicitly.
*   **Sequential checks**: When you need to execute several checks in a specific order (e.g., Auth -> Validation -> Logging).

## 3. Real-World Analogy

**Technical Support**:
1.  **Level 1 Support (Bot/FAQ)**: Can answer basic questions. If not, passes to Level 2.
2.  **Level 2 Support (Human Agent)**: Handles account issues. If too complex, passes to Level 3.
3.  **Level 3 Support (Manager/Engineer)**: Resolves critical bugs.

**ATM Cash Dispenser**:
You want $130.
1.  **$100 Dispenser**: Checks if it can dispense. Gives 1 note. Remaining: $30. Passes to next.
2.  **$50 Dispenser**: Cannot dispense (needs $50 mult). Passes to next.
3.  **$20 Dispenser**: Gives 1 note. Remaining $10. Passes to next.
4.  **$10 Dispenser**: Gives 1 note. Remaining $0. Done.

## 4. Structure (UML Concept)

1.  **Handler**: Defines an interface for handling requests and optionally implements the successor link.
2.  **Concrete Handler**: Handles requests it is responsible for. If it can handle the request, it does so; otherwise, it forwards it to its successor.
3.  **Client**: Initiates the request to a Concrete Handler on the chain.

## 5. Java Example: Logging System

We will implement a Logging System with three levels: `INFO`, `DEBUG`, and `ERROR`.
*   `INFO`: Only prints basic info.
*   `DEBUG`: Prints info + debugging data.
*   `ERROR`: Prints everything (Info + Debug + Error).

*(See `LogProcessor_Chain.java` for the code)*

## 6. Pros and Cons

| Pros | Cons |
| :--- | :--- |
| **Decoupling**: You receive a request and execute it without knowing the exact processing logic for each step. | **No Guarantee**: A request might end up unhandled if the chain doesn't cover all cases. |
| **Single Responsibility Principle**: You can decouple classes that invoke operations from classes that perform operations. | **Performance**: Debugging and tracing a request through a long chain can be difficult and might impact performance slightly. |
| **Open/Closed Principle**: You can introduce new handlers into the app without breaking existing code. | |
