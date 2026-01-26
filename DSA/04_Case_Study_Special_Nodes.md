# Case Study: Special Nodes in a Tree

**Problem**: Find nodes $u$ such that distances $d(u, x), d(u, y), d(u, z)$ form a Pythagorean triplet.

This case study compares two approaches to solving this problem in Java, highlighting the transition from a "C++ style" map-heavy approach to an optimized "Java Competitive Programming" approach.

---

## 1. Initial Approach: Recursive DFS + Maps
**Concept**: Use recursion to calculate distances and store them in a nested `HashMap`.

### User's Solution
```java
class Solution {
    // Heavy Data Structure: Map of Maps
    Map<Integer,Map<Integer,Integer> > map = new HashMap<>();
    int X,Y,Z,ans =0;

    void distance(int node,int parent,int source,List<List<Integer>> graph,boolean check,int distance){
        // Creating new Maps for every node is Expensive!
        map.putIfAbsent(source, new HashMap<>());
        map.get(source).put(node, distance);

        // Check logic mixed inside the traversal
        if(check){
            long dx = map.get(X).get(node);
            long dy = map.get(Y).get(node);
            long dz = map.get(Z).get(node);

            long maxi = Math.max(dx, Math.max(dy,dz));
            long sumSq = dx*dx + dy*dy + dz*dz;
            
            // Pythagorean check: a^2 + b^2 = c^2  => sumSq = 2*c^2
            if (sumSq == 2 * maxi * maxi){
                ans++;
            }
        }

        // Recursive Calls -> Risk of StackOverflow
        for(int i=0; i<graph.get(node).size(); i++){
            if(graph.get(node).get(i) != parent){
                distance(graph.get(node).get(i), node, source, graph, check, distance+1);
            }
        }
    }

    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        List<List<Integer> > graph = new ArrayList<>();
        X=x; Y=y; Z=z;
        for(int i=0; i<n; i++) graph.add(new ArrayList<>());
        for(int i=0; i<n-1; i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }

        // Three full recursive passes
        distance(x, x, x, graph, false, 0);
        distance(y, y, y, graph, false, 0);
        distance(z, z, z, graph, true, 0); // Last pass calculates answer
        return ans;
    }
}
```

### Critical Issues
1.  **Stack Overflow Error**: In Java, the default stack size is small. A skewed tree (line graph) with $N=10^5$ will crash deeply recursive solutions.
2.  **Memory Limit Exceeded (MLE)**: `HashMap<Integer, Integer>` has huge overhead compared to `int[]`. Creating $3 \times N$ map entries involves creating Integer objects and Entry objects.
3.  **Performance**: Hashing is $O(1)$ average but has high constant factors. Recursion is slower than iteration.

---

## 2. Optimized Approach: BFS + Arrays
**Concept**: Use Breadth-First Search (BFS) to find shortest paths safely, and use `int[]` arrays for fast access.

### Optimized Solution
```java
import java.util.*;

class Solution {
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        // 1. Build Graph (Standard Adjacency List)
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // 2. Precompute Distances using BFS
        // Arrays are O(1) Access and heavily optimized
        int[] dx = bfs(n, graph, x);
        int[] dy = bfs(n, graph, y);
        int[] dz = bfs(n, graph, z);

        int ans = 0;
        // 3. Simple Iteration to check condition
        for (int i = 0; i < n; i++) {
            long a = dx[i];
            long b = dy[i];
            long c = dz[i];

            // Avoid Sorting: Check a^2 + b^2 + c^2 == 2 * max^2
            long max = Math.max(a, Math.max(b, c));
            long sumSq = a * a + b * b + c * c;

            if (sumSq == 2 * max * max) {
                ans++;
            }
        }
        return ans;
    }

    // BFS: Prevents StackOverflow, Guaranteed Shortest Path
    private int[] bfs(int n, List<List<Integer>> graph, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        
        Queue<Integer> q = new ArrayDeque<>(); // ArrayDeque is faster than LinkedList
        q.add(start);
        dist[start] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }
}
```

### Improvements
1.  **Stack Safety**: BFS uses the Heap memory (via Queue), not the Stack. It works safely for $N=10^5$.
2.  **Speed**: Array access `arr[i]` is significantly faster than `map.get(i)`.
3.  **Clean Separation**: Calculating distances is decoupled from checking logic.

---

## Summary comparison

| Feature | Your DFS Solution | Optimized BFS Solution |
| :--- | :--- | :--- |
| **Logic** | Recursive | Iterative (Queue) |
| **Data Structure** | `HashMap` (Slow, High Ram) | `int[]` (Fast, Low Ram) |
| **Safety** | High risk of StackOverflow | Safe for large inputs |
| **Complexity** | Potentially $O(N)$ but high constant | $O(N)$ with low constant |
