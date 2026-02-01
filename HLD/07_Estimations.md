# Back-of-the-Envelope Estimation

In interviews, you must estimate scale (Storage, Bandwidth, Server count) quickly.

## The Magic Numbers (Memorize These)
*   **1 Byte** = 8 bits
*   **Characters**: 1 char = 2 Bytes (UTF-16) or 1 Byte (ASCII)
*   **Long/Int**: 8 Bytes / 4 Bytes
*   **KB, MB, GB, TB, PB**: Multiply by 1,000 (roughly).
    *   $10^3$ = 1 KB
    *   $10^6$ = 1 MB
    *   $10^9$ = 1 GB
    *   $10^{12}$ = 1 TB
    *   $10^{15}$ = 1 PB

## Time Approximations
*   **Latency Numbers**:
    *   L1 Cache: 0.5 ns
    *   RAM: 100 ns
    *   SSD: 100 $\mu$s (micro)
    *   Network (Round Ticket): 150 ms (milli)
    *   *Rule of Thumb*: Memory is 1000x faster than Disk. Disk is 100x faster than Network.

## Common Calculations

### 1. QPS (Queries Per Second)
*   **DAU**: Daily Active Users.
*   **Formula**: $QPS = \frac{\text{DAU} \times \text{RequestsPerUser}}{86400}$
    *   *Shortcut*: 86,400 seconds $\approx$ 100,000 ($10^5$) seconds per day.
    *   Example: 10M DAU, 10 requests each.
    *   Total Req = 100M ($10^8$).
    *   QPS = $10^8 / 10^5 = 1000$ QPS.

### 2. Storage Estimation
*   Example: Chat App. 10M DAU. 100 messages/day. 50 Bytes/msg.
*   Total per day: $10^7 \times 10^2 \times 50 = 5 \times 10^{10}$ Bytes.
*   $5 \times 10^{10} = 50$ GB per day.
*   **5 Years**: $50 \text{ GB} \times 365 \times 5 \approx 50 \times 2000 = 100,000 \text{ GB} = 100 \text{ TB}$.
