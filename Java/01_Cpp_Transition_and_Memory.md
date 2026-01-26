# 0. Java Transition for C++ Experts

**Prerequisite**: You know C++ pointers, destructors, and vtables. We will map those to Java.

## 1. The Memory Model: No More Pointers(ish)

### References vs Pointers
In C++:
```cpp
Person* p = new Person(); // p is a pointer (address)
Person& r = *p;           // r is a reference (alias)
```
In Java:
```java
Person p = new Person(); 
```
- `p` is technically a **reference** (conceptually similar to a C++ pointer but you can't do arithmetic on it).
- **EVERYTHING** (except primitives `int`, `boolean`, `double`, etc.) is on the **Heap**.
- **No Stack Allocation for Objects** (until Project Valhalla/Escape Analysis optimizations, but assume Heap for now).

### Pass-by-Value
**Java is strictly Pass-by-Value.**
- When you pass an object `foo(p)`, you are passing a **copy of the reference bits** (the address).
- If you reassign the parameter inside the function (`p = new Person()`), the original `p` outside is unchanged.
- If you modify the object (`p.setName("Rohit")`), the caller sees the change (because you followed the address).

## 2. Garbage Collection (GC) vs Destructors

### C++
RAII (Resource Acquisition Is Initialization). Objects are destroyed when they go out of scope (stack) or `delete` is called (heap).

### Java
- No `delete`. No deterministic `destructor` (no `~Person()`).
- **Garbage Collector (GC)** runs when it wants (usually when Heap is full).
- **Implication**: DO NOT use `finalize()` (it's deprecated and unreliable).
- **Resource Management**: Use `try-with-resources` (AutoCloseable interface) for files, sockets, DB connections.

```java
// Equivalent to C++ RAII for resources
try (BufferedReader br = new BufferedReader(new FileReader("path"))) {
    return br.readLine();
} // br.close() called automatically here
```

## 3. The `Object` Class (The Root of Hierarchy)

Every class extends `java.lang.Object` implicitly.

### Key Methods to Master
1.  **`equals(Object o)`**: Reference equality (`==`) by default. You **MUST** override this for value objects (like structs).
    - Contract: Reflexive, Symmetric, Transitive, Consistent, Non-null.
2.  **`hashCode()`**: Returns memory address by default. Pital pair with `equals`.
    - **Rule**: If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()`.
    - If you break this, HashMaps will lose your keys.
3.  **`toString()`**: Debugging friend.
4.  **`clone()`**: **AVOID**. It's broken (shallow copy, checks exceptions). Use **Copy Constructors** instead.

```java
// Copy Constructor - The Pro way
public Person(Person other) {
    this.name = other.name;
    this.age = other.age;
}
```

## 4. Modern Java (Java 14+) Features for SDE 2

### Records (Java 14)
Immutable data carriers. Like C++ `struct` but smarter. Replaces Lombok `@Data`.
```java
public record User(String name, int id) {}
// Free: constructor, getters, equals, hashCode, toString
```

### Switch Expressions
No more `break` statements.
```java
String dayType = switch (day) {
    case MONDAY, FRIDAY -> "Work";
    case SATURDAY, SUNDAY -> "Rest";
    default -> "Regular";
};
```

### var (Local Variable Type Inference)
Like C++ `auto`.
```java
var map = new HashMap<String, List<Integer>>(); // Cleaner
```

## 5. Checklist for Mastery
- [ ] **Project assignment**: Write a custom `ArrayList` implementation in Java using an array `Object[]` to understand basics.
- [ ] **Deep Dive**: Read about "Java String Pool". Why is `String` immutable? (Security, Hashing optimizations).
