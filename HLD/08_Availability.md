# High Availability (HA)

**Availability** is measured in "Nines".
*   99% (2 nines): Down 3.65 days/year.
*   99.9% (3 nines): Down 8.7 hours/year.
*   99.99% (4 nines): Down 52 mins/year.
*   99.999% (5 nines): Down 5 mins/year. (Gold Standard).

## HA Architecture Patterns

### 1. Active-Passive (Master-Slave)
*   **Setup**: Two servers. Only "Active" handles traffic. "Passive" sends heartbeats.
*   **Failover**: If Active dies, Passive wakes up and takes over IP.
*   **Pros**: Simple. Data consistency is easier.
*   **Cons**: Waste of resources (1 server sits idle). Slower failover (warm-up time).

### 2. Active-Active (Master-Master)
*   **Setup**: Both servers handle traffic. Load Balancer distributes it.
*   **Failover**: If Server A dies, LB sends all traffic to Server B.
*   **Pros**: Uses all resources. Instant failover.
*   **Cons**: Complexity in data synchronization (Dual Write problem).

## SPOF (Single Point of Failure)
An HA system must eliminate ALL SPOFs.
*   Running 10 Web Servers? Good.
*   But all talk to 1 DB? **DB is SPOF**.
*   All talk to 1 Load Balancer? **LB is SPOF**. (Solution: Use HAProxy with Keepalived).
