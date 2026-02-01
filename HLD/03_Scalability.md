# Scalability

## 1. Vertical Scaling (Scale Up)
Adding more power to a single server (More CPU, More RAM, Bigger SSD).
*   **Analogy**: Buying a bigger engine for your car.
*   **Pros**:
    *   Simple (No code changes required).
    *   No network overhead between services.
*   **Cons**:
    *   **Hard Limit**: There is a max limit to how much RAM/CPU a single machine can have.
    *   **Single Point of Failure**: If it crashes, everything goes down.
    *   **Expensive**: High-end mainframes cost exponentially more.

## 2. Horizontal Scaling (Scale Out)
Adding more machines to a pool of resources.
*   **Analogy**: Buying more cars (a fleet) to carry more people.
*   **Pros**:
    *   **Unlimited Scale**: Just add another server.
    *   **Resilience**: If one server dies, others take the load.
    *   **Cheaper**: Can use commodity hardware.
*   **Cons**:
    *   **Complexity**: Requires Load Balancers.
    *   **Data Consistency**: Harder to manage state across multiple machines.
    *   **Network Overhead**: Services call each other over the network.
