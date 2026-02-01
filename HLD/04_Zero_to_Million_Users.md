# Scale from Zero to Million Users

This is the classic System Design interview story.

## Stage 1: Single Server Setup (0 - 100 Users)
*   **Architecture**: App, DB, and Web Server all on ONE machine.
*   **Bottleneck**: If that machine fails, game over. CPU/RAM limits.

## Stage 2: Database Separation (100 - 1k Users)
*   **Change**: Move DB to a separate server.
*   **Benefit**: App and DB scale independently.

## Stage 3: Load Balancer + Horizontal Scaling (1k - 100k Users)
*   **Change**: Add a Load Balancer (Nginx/HAProxy) in front. Add multiple App Servers (Stateless).
*   **Benefit**: High availability for App layer.

## Stage 4: Database Replication (Read Scaling)
*   **Problem**: DB writes are fine, but Reads are slow.
*   **Change**: Master-Slave (or Leader-Follower) Architecture.
    *   **Master**: Handles Writes (INSERT/UPDATE).
    *   **Slaves**: Handle Reads (SELECT).
*   **Benefit**: Reads scale horizontally.

## Stage 5: Caching (Speed)
*   **Change**: Add Cache (Redis/Memcached).
*   **Strategy**: Cache-Aside. Check Cache -> If miss, DB -> Write to Cache.
*   **Benefit**: Reduces load on DB for frequent data.

## Stage 6: CDN (Content Delivery Network)
*   **Change**: Serve static files (Images/CSS/JS) from CDN (Cloudflare/AWS CloudFront).
*   **Benefit**: Content is closer to user physically. Faster load times.

## Stage 7: Sharding (Data Partitioning) (1M+ Users)
*   **Problem**: Master DB is too full or too slow for writes.
*   **Change**: Split DB into multiple pieces (Shards) based on UserID or Geography.
*   **Complexity**: Very high. No joins across shards.
