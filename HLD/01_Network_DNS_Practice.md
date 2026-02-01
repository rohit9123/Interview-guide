# Practice: Network & DNS

## Scenario 1: Video Streaming Service
You are designing a live video streaming service (like Twitch). 
**Question**: Which protocol would you choose for the video data Transfer? TCP or UDP? Why?

<details>
<summary>Click to Reveal Answer</summary>

**Answer**: **UDP**.
*   **Reason**: Live streaming requires low latency. If a packet is lost, it's better to skip it and show the next frame rather than pausing the video to retransmit the lost packet (which TCP does). 
*   However, for the *Control Signal* (Pause, Play, Login), we use **TCP** because we can't afford to lose those commands.
</details>

---

## Scenario 2: DNS Debugging
Users are complaining that they can't access `www.yoursite.com`, getting "Server Not Found".
*   You can ping the IP address `1.2.3.4` successfully.
*   You cannot ping the domain name.

**Question**: Where is the issue likely located?

<details>
<summary>Click to Reveal Answer</summary>

**Answer**: **DNS Resolution Issue**.
Since the server is reachable by IP, the network and server are fine. The failure happens when translating Name -> IP.
It could be issues at:
1.  The User's local cache.
2.  The ISP's Resolver is down.
3.  Your Authoritative Name Server is down or misconfigured (e.g., domain expired).
</details>

---

## Scenario 3: HTTP vs HTTPS Overhead
Your boss wants to disable HTTPS to save "encryption overhead" and improve speed for a public blog.
**Question**: Is this a good idea? What are the modern implications?

<details>
<summary>Click to Reveal Answer</summary>
**Answer**: **No, bad idea.**
1.  **Security**: Man-in-the-middle attacks can inject ads or malware even on "public" content.
2.  **SEO**: Google penalizes non-HTTPS sites.
3.  **Performance**: HTTP/2 and HTTP/3 *require* encryption in practice (browsers enforce it). HTTP/2 multiplexing often makes HTTPS faster than old HTTP/1.1 plain text.
</details>
