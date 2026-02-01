# Domain Name System (DNS)

DNS is the "Phonebook of the Internet". It translates human-readable domain names (`google.com`) into IP addresses (`142.250.190.46`).

## How it Works (The Lookup Journey)

When you type `www.example.com` in your browser:

1.  **Browser Cache**: Checks if it already knows the IP.
2.  **OS Cache**: Checks the operating system's hosts file/cache.
3.  **Resolver (ISP)**: The request goes to your ISP's Recursive Resolver.

If the Resolver doesn't know, it asks the Hierarchy:

4.  **Root Server (.)**: "I don't know exact IP, but here is the address of the `.com` TLD Server."
5.  **TLD Server (.com)**: "I don't know exact IP, but here is the Authoritative Name Server for `example.com`."
6.  **Authoritative Name Server**: "Yes, I manage `example.com`. The IP is `93.184.216.34`."

7.  **Result**: The Resolver caches this IP (for TTL) and returns it to your browser.

## Record Types
*   **A**: Maps hostname to IPv4.
*   **AAAA**: Maps hostname to IPv6.
*   **CNAME**: Maps alias to another hostname (`www.google.com` -> `google.com`).
*   **MX**: Mail Exchange (for emails).
*   **NS**: Name Server (who is authoritative).
*   **TTL (Time To Live)**: How long to cache the record. Low TTL = fast updates but high load. High TTL = good cache ratio but slow updates.
