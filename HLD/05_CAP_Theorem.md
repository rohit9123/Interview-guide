# CAP Theorem

For any distributed data store, it is impossible to simultaneously guarantee more than two of the following three guarantees:

## 1. Consistency (C)
*   **Definition**: Every read receives the most recent write or an error.
*   **Real World**: If I update my profile picture, you should see the new one *immediately*, no matter which server you talk to.
*   **Trade-off**: Requires locking or waiting for replication -> Slow/Unavailable during partitions.

## 2. Availability (A)
*   **Definition**: Every request receives a (non-error) response, without the guarantee that it contains the most recent write.
*   **Real World**: If I update my profile picture, you might see the old one for a few seconds, but the system *always responds*.
*   **Trade-off**: Must return stale data if sync is broken.

## 3. Partition Tolerance (P)
*   **Definition**: The system continues to operate despite an arbitrary number of messages being dropped or delayed by the network between nodes.
*   **Reality**: In a distributed system, **P is mandatory**. Network fails. You cannot choose to have "No Partition Tolerance" unless you run on a single machine.

## The Choice: CP vs AP

Since P is mandatory, you must choose between:

*   **CP (Consistency + Partition Tolerance)**:
    *   System waits for sync to complete. If network breaks, it returns ERROR or times out.
    *   **Examples**: Banking, MongoDB (default), Redis (default).
*   **AP (Availability + Partition Tolerance)**:
    *   System returns whatever data it has (maybe stale). Prioritizes "Always Up".
    *   **Examples**: Cassandra, DynamoDB, DNS, Facebook News Feed.
