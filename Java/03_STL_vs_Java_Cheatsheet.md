# cheatsheet: C++ STL vs Java Collections

**Goal**: Fast mapping for Competitive Programmers & C++ Devs switching to Java.

## 1. Sequence Containers

| C++ STL | Java Collection | Notes |
| :--- | :--- | :--- |
| `std::vector<T>` | `ArrayList<T>` | Dynamic array. `vector` in Java is synchronized (slow), avoid it. |
| `std::vector` (fixed size) | `T[]` (Array) | Arrays are fixed size in Java. `int[] arr = new int[5];` |
| `std::list<T>` | `LinkedList<T>` | Doubly linked list. |
| `std::deque<T>` | `ArrayDeque<T>` | Double-ended queue. Faster than `LinkedList`. |
| `std::stack<T>` | `ArrayDeque<T>` | Java's `Stack` class is legacy (synchronized). Use `Deque` interface. |
| `std::queue<T>` | `ArrayDeque<T>` | `Queue` is an interface. `ArrayDeque` is the implementation. |
| `std::priority_queue<T>` | `PriorityQueue<T>` | Min-Heap by default in Java (Max-Heap in C++). |

### Code Examples

**Vector / ArrayList**
```cpp
// C++
vector<int> v;
v.push_back(1);
int x = v[0];
v.size();
```
```java
// Java
List<Integer> v = new ArrayList<>(); // Note: Wrapper Class Integer
v.add(1);
int x = v.get(0); // No operator overloading []
v.size();
```

**Stack (LIFO)**
```cpp
// C++
stack<int> s;
s.push(1);
s.top();
s.pop();
```
```java
// Java - Use ArrayDeque
Deque<Integer> s = new ArrayDeque<>();
s.push(1);
s.peek(); // Equivalent to top()
s.pop();
```

**Priority Queue (Heap)**
```cpp
// C++ (Max Heap by default)
priority_queue<int> pq; 
pq.push(10);
```
```java
// Java (Min Heap by default)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.add(10);

// For Max Heap in Java:
PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
```

## 2. Associative Containers (Maps & Sets)

| C++ STL | Java Collection | Time Complexity | Notes |
| :--- | :--- | :--- | :--- |
| `std::set<T>` | `TreeSet<T>` | O(log N) | Sorted. Uses Red-Black Tree. |
| `std::unordered_set<T>` | `HashSet<T>` | O(1) avg | Unsorted. Hashing. |
| `std::map<K, V>` | `TreeMap<K, V>` | O(log N) | Sorted by Key. |
| `std::unordered_map<K, V>` | `HashMap<K, V>` | O(1) avg | Unsorted Key-Value pairs. |
| `std::multiset` | *No direct equivalent* | | Use `TreeMap<T, Integer>` (count map) or Guava `Multiset`. |

### Code Examples

**Map**
```cpp
// C++
map<string, int> m;
m["apple"] = 5;
if (m.count("apple")) { ... }
```
```java
// Java
Map<String, Integer> m = new HashMap<>(); // or TreeMap
m.put("apple", 5);
if (m.containsKey("apple")) { ... }
int val = m.get("apple"); // returns null if missing!
int valOrDefault = m.getOrDefault("apple", 0); // Safer
```

## 3. Algorithms & Utilities

| C++ | Java Equivalent |
| :--- | :--- |
| `sort(v.begin(), v.end())` | `Collections.sort(list)` or `Arrays.sort(arr)` |
| `stable_sort` | Java's sort is stable for Objects (TimSort). |
| `reverse` | `Collections.reverse(list)` |
| `min / max` | `Collections.min(list)` / `Collections.max(list)` |
| `binary_search` | `Collections.binarySearch(list, key)` |
| `lower_bound` | *Custom* or use `TreeSet.ceiling()` |
| `upper_bound` | *Custom* or use `TreeSet.higher()` |
| `next_permutation` | *No built-in.* Must implement manually. |
| `pair<T, U>` | *No built-in.* Make a `record` or class. |

### Sorting with Custom Comparator
**C++**
```cpp
sort(v.begin(), v.end(), [](int a, int b) {
    return a > b;
});
```
**Java**
```java
Collections.sort(list, (a, b) -> b - a); // Lambda
// or
list.sort((a, b) -> b.compareTo(a));
```

## 4. Key Differences / Gotchas
1.  **Primitives vs Wrappers**: Java generics ONLY work with Objects. You can't have `ArrayList<int>`. You must use `ArrayList<Integer>`. This adds overhead (autoboxing/unboxing).
2.  **No Operator Overloading**: You can't evaluate `map[key]++`. You must do `map.put(key, map.get(key) + 1)`.
3.  **Iterator invalidation**: Modifying a collection while iterating throws `ConcurrentModificationException`. Use `Iterator.remove()` to be safe.

## 5. Nested Collections (Common in CP)

### 2D List (Vector of Vectors)
Equivalent to C++ `vector<vector<int>>`.

**Java**
```java
// Declaration
List<List<Integer>> matrix = new ArrayList<>();

// Initialization (Adding rows)
for (int i = 0; i < n; i++) {
    matrix.add(new ArrayList<>()); // Must add empty rows manually!
}

// Accessing
matrix.get(0).add(5);      // matrix[0].push_back(5)
int val = matrix.get(0).get(0); // matrix[0][0]
```

### Map of Maps
Equivalent to C++ `map<int, map<int, int>>`.

**Java**
```java
Map<Integer, Map<Integer, Integer>> nestedMap = new HashMap<>();

// Put: map[u][v] = w
// We must check if outer key exists first
nestedMap.putIfAbsent(1, new HashMap<>()); 
nestedMap.get(1).put(2, 10);

// Get: int w = map[u][v]
if (nestedMap.containsKey(1) && nestedMap.get(1).containsKey(2)) {
    int w = nestedMap.get(1).get(2);
}
```

## 6. Length vs Size (The "How big is it?" Cheat Sheet)
**Rule of Thumb**:
*   **Arrays (`[]`)** -> `.length` (It's a fixed property, "Length of the box")
*   **String** -> `.length()` (It's an object method)
*   **Collections** -> `.size()` (Just like C++ STL)

| Data Structure | Syntax | Type | Visual Hint |
| :--- | :--- | :--- | :--- |
| **Array** (`int[]`, `char[]`) | `.length` | **Property** | No parentheses `()`. It's a raw memory block. |
| **String** (`"hello"`) | `.length()` | **Method** | It's an Object, so it needs `()`. |
| **Collections** (`List`, `Set`, `Map`) | `.size()` | **Method** | Holds items. Like `std::vector::size()`. |

### Getting Dimensions of 2D Structures

**1. 2D Array (`int[][]`)**
```java
int[][] grid = new int[5][10];

int rows = grid.length;        // 5 (Count of rows)
int cols = grid[0].length;     // 10 (Length of first row)
```

**2. 2D List (`ArrayList<ArrayList<Integer>>`)**
```java
List<List<Integer>> matrix = new ArrayList<>();

int rows = matrix.size();        // Count of lists inside
int cols = matrix.get(0).size(); // Size of the first list
```
