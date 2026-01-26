# Reflection, Dynamic Proxies & AOP Internals

**Target Audience**: Master Level. Understanding how Frameworks work (Spring, Hibernate).

## 1. Reflection: The "Dark Magic"
Reflection allows code to inspect and modify its own structure at runtime.
**C++ Analogy**: It's like RTTI but on steroids. You can invoke methods by name, access private fields, and create instances without `new`.

### Why use it?
- **Frameworks**: Spring uses it to inject beans (`@Autowired`). Hibernate uses it to map DB rows to objects.
- **Testing**: Mockito uses it to mock final classes/methods.

### The API (Quick Ref)
```java
Class<?> clazz = User.class;
Constructor<?> ctor = clazz.getDeclaredConstructor();
User u = (User) ctor.newInstance();

Field f = clazz.getDeclaredField("privateData");
f.setAccessible(true); // BREAK ENCAPSULATION
f.set(u, "Hacked");

Method m = clazz.getDeclaredMethod("doSecret");
m.setAccessible(true);
m.invoke(u);
```

### Performance Cost
Reflection is slower than direct calls because:
1.  **Type Checking**: JVM checks visibility and type safety at runtime.
2.  **No JIT Optimizations**: Inlining is harder for the JIT compiler.
**Optimization**: MethodHandles (Java 7+) and VarHandles (Java 9+) are modern, faster alternatives.

## 2. Dynamic Proxies: The Core of AOP

How does `@Transactional` or `@Loggable` work? **By wrapping your object in a Proxy.**

### The `InvocationHandler`
You can intercept **interface** method calls.

```java
interface Service { void serve(); }

class RealService implements Service {
    public void serve() { System.out.println("Serving..."); }
}

class LoggingHandler implements InvocationHandler {
    private final Object target;
    public LoggingHandler(Object target) { this.target = target; }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">> Before Method: " + method.getName()); // @Before Aspect
        Object result = method.invoke(target, args);
        System.out.println("<< After Method"); // @After Aspect
        return result;
    }
}

// Usage
Service real = new RealService();
Service proxy = (Service) Proxy.newProxyInstance(
    RealService.class.getClassLoader(),
    new Class<?>[]{Service.class},
    new LoggingHandler(real)
);

proxy.serve(); // Prints: ">> Before...", "Serving...", "<< After..."
```

### Reference: JDK Proxy vs CGLIB
- **JDK Proxy** (Above): Only works if the class implements an **Interface**.
- **CGLIB** (Code Generation Library): Works by **extending the class** (Subclassing).
    - This is why Spring Beans couldn't be `final` in the past (can't extend final class).
    - Used if your bean doesn't implement an interface.

## 3. Building a Mini-AOP Framework (Mental Model)
1.  **Scan** the code for an annotation `@MyTransactional`.
2.  **Create** a Proxy for that object.
3.  **Inject** the Proxy instead of the Real Object into the controller.
4.  When called:
    - `Proxy.invoke()` -> `TransactionManager.begin()` -> `RealMethod()` -> `TransactionManager.commit()`.

## 4. Master Checklist
- [ ] **Experiment**: Write a program that uses Reflection to change a `private final String` (Note: works on Java 8, hard/impossible on Java 17+ due to modules).
- [ ] **Challenge**: Implement a generic `RetryProxy` that retries a method call 3 times if it throws an exception.
