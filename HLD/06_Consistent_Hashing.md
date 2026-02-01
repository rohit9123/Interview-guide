# Consistent Hashing

A technique to distribute data across multiple servers (nodes) in a way that minimizes reorganization when nodes are added or removed.

## The Problem with Modulo Hashing `hash(key) % N`
Suppose we have 4 servers. `hash(user_id) % 4`.
*   User 10 -> Server 2.
*   **Scaling Event**: We add a 5th server. Now `hash(user_id) % 5`.
*   **Result**: almost ALL keys change their target server. massive cache miss storm.

## How Consistent Hashing Works (The Ring)
1.  **The Ring**: Imagine a circle mapping hash values from 0 to $2^{32}-1$.
2.  **Server Placement**: Hash the server IP addresses to place them on the Ring.
3.  **Data Placement**: Hash the Key (e.g., UserID) to find its spot on the Ring.
4.  **Assignment**: Walk **clockwise** from the Key's position until you find the first Server. That server stores the key.

## Adding/Removing Nodes
*   **Add Node**: Only keys between the New Node and the Previous Node need to move. (Minimal data movement).
*   **Remove Node**: Keys that mapped to the failed node just continue clockwise to the Next Available Node.

## Virtual Nodes (VNodes)
*   **Problem**: Data might not be evenly distributed (Unbalanced Ring).
*   **Solution**: Each physical server apppears at multiple spots on the ring (e.g., Server A_1, A_2, A_3...). This ensures uniform distribution.
