# High-Level Design (HLD) Mastery Roadmap

**Goal**: Understand how to scale systems to millions of users while maintaining reliability and performance.

## Phase 1: Core Fundamentals & Scaling
- [x] **Network Protocols**: HTTP, HTTPS, TCP/UDP 
- [x] **How DNS works?**: System Design of Domain Name System
- [x] **Scalability**: Vertical vs Horizontal Scaling
- [x] **Scale from ZERO to MILLION User** (35min)
- [x] **CAP Theorem**: Consistency, Availability, Partition Tolerance
- [x] **Consistent Hashing**: Fundamental for distributed systems (32min)
- [x] **Back-Of-The-Envelope Estimation**: Essential for design interviews
- [x] **Availability**: High Availability (Active-Passive & Active-Active Architecture)

## Phase 2: Traffic Management & Building Blocks
- [ ] **Load Balancers**: Algorithms (Round Robin, Least Conn, etc.)
- [ ] **Proxies**: Forward Proxy vs Reverse Proxy (29min)
- [ ] **Caching Strategies**: Distributed Cache, Cache-Aside, Write-Through, Write-Back
- [ ] **Distributed Messaging Queue**: Kafka, RabbitMQ (Design & Use Cases)
- [ ] **Thundering Herd Problem**: Handling peak traffic spikes

## Phase 3: Data Management & Concurrency
- [ ] **SQL vs NoSQL**: Choosing the right database (36min)
- [ ] **Database Indexing**: B+ Tree, Data Page, Clustered and Non-Clustered Indexing
- [ ] **Concurrency Control**: Optimistic & Pessimistic Concurrency
- [ ] **Two Phase Locking (2PL)**: Ensuring data consistency
- [ ] **Handle Distributed Transactions**: Two-Phase Commit (2PC), 3PC, and SAGA Pattern
- [ ] **Design a Key-Value Store**: Dynamo DB Architecture (1hr)
- [ ] **Consistent Hashing**: Deep dive for sharding

## Phase 4: Microservices Architecture
- [ ] **Introduction: Microservices**: Monolithic vs Microservices (29min)
- [ ] **Service Discovery**: How services find each other (21min)
- [ ] **API Gateway**: Microservices Architecture entry point
- [ ] **Service Mesh**: Architecture & Sidecar patterns
- [ ] **Microservices Patterns**: SAGA Pattern, Strangler Pattern, Bulkhead Pattern
- [ ] **Fault Tolerance**: Retry Pattern, Circuit Breaker
- [ ] **Distributed Messaging Queue**: Connecting services asynchronously
- [ ] **Idempotency**: Design Idempotent POST APIs to handle duplicate requests
- [ ] **Dual Write Problem**: Event Driven Microservices Patterns
- [ ] **Partitioning Monoliths**: How many microservices to divide into?

## Phase 5: Security & Authentication
- [ ] **OAuth 2.0 explained**: Flows and permissions
- [ ] **JWT: JSON Web Token**: Practical implementation
- [ ] **Encryption**: Symmetric (AES) vs Asymmetric (Diffie-Hellman)
- [ ] **Security Attacks & Demos**: CSRF, XSS, CORS, and SQL Injection

## Phase 6: HLD Case Studies (The Practice)
- [ ] **Design URL Shortening Service (TinyURL)** (32min)
- [ ] **Whatsapp System Design** (50min)
- [ ] **Design Rate Limiter**: Architecture & Algorithms (Token Bucket, Leaky Bucket)
- [ ] **Design Netflix / YouTube** (Video Streaming)
- [ ] **Design Uber / Lyft** (Ride-sharing)
- [ ] **Design Twitter / Facebook News Feed**
- [ ] **Design Distributed Cache** (Redis Clone)
- [ ] **Design Google Drive / Dropbox**

## Phase 7: Advanced Data & Interaction Patterns
- [ ] **CQRS (Command Query Responsibility Segregation)**: Separating reads from writes
- [ ] **Event Sourcing**: Storing state as a sequence of events
- [ ] **CAP vs PACELC Nuances**: Understanding latency choices during normal operation
- [ ] **Bloom Filters**: Space-efficient membership testing (e.g., in caches/databases)
- [ ] **Geo-Sharding**: Scaling data across geographical boundaries

## Phase 8: Observability & Monitoring (The "Day 2" Operations)
- [ ] **Centralized Logging (ELK/EFK Stack)**: Aggregating logs from 100s of services
- [ ] **Distributed Tracing (Jaeger/Zipkin)**: Tracking a single request across multiple services
- [ ] **Monitoring & Metrics (Prometheus/Grafana)**: Dashboards and Alarms
- [ ] **Health Checks & Circuit Breaker Dashboarding**

## Phase 9: Infrastructure & High Resilience
- [ ] **Deployment Strategies**: Blue-Green, Canary, Rolling Updates
- [ ] **Containers & Orchestration**: Basics of Docker and Kubernetes (K8s)
- [ ] **Disaster Recovery**: Multi-region failover, Data Backup & Restore strategies
- [ ] **Chaos Engineering**: Intentionally breaking things to build stronger systems

---
