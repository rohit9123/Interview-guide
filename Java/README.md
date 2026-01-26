# Advanced Java Roadmap

**Focus**: Deep internals, Framework magic (Spring/Hibernate under the hood), and Concurrency.

## 1. Core Advanced & Internals
- [x] **C++ to Java Transition**: Memory Model, Stack vs Heap, Pass-by-Value (See `01_Cpp_Transition_and_Memory.md`).
- [ ] **Reflection**: Inspecting classes, private field access, performance cost.
- [ ] **Dynamic Proxies**: `java.lang.reflect.Proxy`. The basis of Spring AOP.
- [ ] **Annotations**: Creating custom annotations and Processors (Retention policies).
- [ ] **Generics**: Type Erasure, Bridge methods, Wildcards (`? extends T`, `? super T`).

## 2. JVM & Performance
- [ ] **Classloading**: Delegation Model, Custom Classloaders.
- [ ] **Garbage Collection**: G1GC vs ZGC. Generations (Eden, Survivor, Old). Tuning basics.
- [ ] **JIT Compiler**: C1 vs C2 compiler, HotSpot optimizations.
- [ ] **Bytecode**: Brief overview of `.class` file structure.

## 3. Concurrency (Pro Level)
- [ ] **Java Memory Model**: `volatile`, `happens-before`, Instruction Reordering.
- [ ] **Locks**: reentrantLock vs synchronized (Internals: Biased Locking).
- [ ] **CompletableFuture**: Async programming patterns.
- [ ] **Virtual Threads (Loom)**: The new paradigm vs Reactive.

## 4. Concepts behind Frameworks
- [ ] **AOP (Aspect Oriented Programming)**: Implementing a mini-aspect using Proxy.
- [ ] **DI (Dependency Injection)**: How to write a mini-IoC container.
