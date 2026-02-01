# Practice: Estimations

## Question 1: Twitter Storage
*   DAU: 200 Million.
*   Tweets/Day: 2 per user.
*   Size/Tweet: 100 Bytes (text) + Metadata. Let's say 200 Bytes total.
*   Percentage with Media: 10% contain images (500KB average).

**Calculate**:
1.  Total Storage needed for 1 Day.
2.  Total Storage for 5 Years.

<details>
<summary>Click to Reveal Answer</summary>

**1. Text Storage:**
*   $200M \times 2 = 400M$ tweets.
*   $400M \times 200B = 80 \text{ GB}$.

**2. Media Storage:**
*   $400M \times 10\% = 40M$ media tweets.
*   $40M \times 500 \text{ KB} = 20,000,000 \text{ MB} = 20 \text{ TB}$.

**Total per Day**:
*   $20 \text{ TB} + 80 \text{ GB} \approx 20 \text{ TB}$.

**Total for 5 Years**:
*   $20 \text{ TB} \times 365 \times 5 \approx 20 \times 1825 \approx 36,500 \text{ TB} = 36.5 \text{ PB}$.
</details>

---

## Question 2: Bandwidth (Ingress)
Using the same numbers above ($20 \text{ TB}$ per day Ingress).
What is the bandwidth required in Mbps (Megabits per second)?

<details>
<summary>Click to Reveal Answer</summary>

*   **Total Data**: 20 TB / Day.
*   $20 \text{ TB} = 20 \times 10^{12}$ Bytes = $160 \times 10^{12}$ bits.
*   **Seconds in Day**: $86,400 \approx 10^5$.
*   **Bits per second**: $160 \times 10^{12} / 10^5 = 160 \times 10^7$ bps.
*   $1.6 \times 10^9$ bps = **1.6 Gbps**.

*Actual calculation (86400)*:
$20,000,000 \text{ MB} / 86400 \approx 231 \text{ MB/s}$.
$231 \times 8 = 1848 \text{ Mbps} \approx 1.8 \text{ Gbps}$.
</details>
