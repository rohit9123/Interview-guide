# Network Protocols

## 1. TCP (Transmission Control Protocol)
**"Reliable, Ordered, Error-Checked"**
TCP is connection-oriented. It establishes a connection (3-Way Handshake) before sending data.
*   **Guarantees**: Delivery, Order, No Duplicates.
*   **Mechanism**: Acknowledgment (ACK) packets, Retransmission if lost, Flow Control.
*   **Use Cases**: Web (HTTP), Email (SMTP), File Transfer (FTP).
*   **Slowness**: The handshake and error-checking add latency.

## 2. UDP (User Datagram Protocol)
**"Fire and Forget"**
UDP is connectionless. It sends packets without checking if the receiver is ready or if they arrived.
*   **Guarantees**: None. Packets can be lost, out of order, or duplicated.
*   **Benefits**: Extremely fast/low latency.
*   **Use Cases**: Video Streaming, VoIP, Online Gaming, DNS lookups.

---

## 3. HTTP (HyperText Transfer Protocol)
Application layer protocol for the web.
*   **HTTP/1.1**: Text-based. Keep-Alive connections. Head-of-line blocking issue (one request per connection at a time).
*   **HTTP/2**: Binary. Multiplexing (multiple requests over one TCP connection). Header compression.
*   **HTTP/3**: Built on QUIC (UDP based) instead of TCP to avoid TCP head-of-line blocking.

---

## 4. HTTPS (Secure)
HTTP + SSL/TLS.
*   **Handshake**:
    1.  Client sends "ClientHello" (supported ciphers).
    2.  Server sends "ServerHello" + Certificate (Public Key).
    3.  Client validates cert.
    4.  Client creates a Session Key, encrypts it with Server's Public Key, sends it.
    5.  Server decrypts with Private Key.
    6.  Both communicate using the Symmetric Session Key.
