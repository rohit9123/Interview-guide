# Practice: Scalability & Distribution

## Scenario 1: The Banking App
You are designing the money transfer system for a major bank.
**Question**: According to CAP Theorem, which two properties must you prioritize? (CP or AP)?

<details>
<summary>Click to Reveal Answer</summary>
**Answer**: **CP (Consistency + Partition Tolerance)**.
*   **Reason**: In Banking, correctness is non-negotiable. You cannot show a user they have $1000 if they just spent it. It is better to show "Service Temporarily Unavailable" (Sacrifice Availability) than to show wrong balance (Sacrifice Consistency).
</details>

---

## Scenario 2: The Viral Social Media Post
You are designing a generic "Like" counter for a viral post. Millions of people are liking it per minute.
**Question**: CP or AP?

<details>
<summary>Click to Reveal Answer</summary>
**Answer**: **AP (Availability + Partition Tolerance)**.
*   **Reason**: If I see 1,000,005 likes and you see 1,000,090 likes, it doesn't matter much. It is "Eventually Consistent". It is far more important that the "Like" button always works (Availability).
</details>

---

## Scenario 3: Consistent Hashing Ring
You have 3 servers (A, B, C) on a ring.
Order on Ring: A (0) -> B (40) -> C (80) -> [Wrap to 0]
Key `K1` hashes to 20.
Key `K2` hashes to 90.

1.  Where does K1 go?
2.  Where does K2 go?
3.  Server B crashes. Where does K1 go now?

<details>
<summary>Click to Reveal Answer</summary>

**Answers**:
1.  **K1 (20)** -> Walks clockwise -> Hits **B (40)**.
2.  **K2 (90)** -> Walks clockwise -> Passes 100/Wrap -> Hits **A (0)**.
3.  **If B crashes**: K1 (20) walks clockwise. Skips B. Hits **C (80)**.
</details>
