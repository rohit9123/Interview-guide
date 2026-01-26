# Comparable vs Comparator in Java

In Java, both `Comparable` and `Comparator` interfaces are used to sort collections of objects. However, they are used in different scenarios and have distinct characteristics.

## 1. Comparable (Natural Ordering)

`Comparable` is used to define the **natural ordering** of an object. A class implements `Comparable` to define how its instances should be compared to each other.

*   **Package**: `java.lang`
*   **Method**: `public int compareTo(T o)`
*   **Usage**: When you want a default sorting logic for your class (e.g., sorting Students by roll number).
*   **Modification**: Requires modifying the class whose objects you want to sort.

### Example: Student Class

```java
import java.util.*;

class Student implements Comparable<Student> {
    int rollNo;
    String name;
    int age;

    public Student(int rollNo, String name, int age) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
    }

    // Natural ordering by rollNo
    @Override
    public int compareTo(Student st) {
        if (this.rollNo == st.rollNo)
            return 0;
        else if (this.rollNo > st.rollNo)
            return 1;
        else
            return -1;
        // Short trick: return this.rollNo - st.rollNo; (Be careful of overflow)
    }
}

public class Main {
    public static void main(String args[]) {
        ArrayList<Student> al = new ArrayList<Student>();
        al.add(new Student(101, "Vijay", 23));
        al.add(new Student(106, "Ajay", 27));
        al.add(new Student(105, "Jai", 21));

        Collections.sort(al); // Uses compareTo
        for (Student st : al) {
            System.out.println(st.rollNo + " " + st.name + " " + st.age);
        }
    }
}
```

## 2. Comparator (Custom Ordering)

`Comparator` is used to define **custom or multiple ordering** logics. It is useful when you want to sort objects based on different criteria (e.g., name, age) or if you cannot modify the original class.

*   **Package**: `java.util`
*   **Method**: `public int compare(T o1, T o2)`
*   **Usage**: When you need multiple ways to sort or don't own the class code.
*   **Modification**: Doesn't require modifying the class; you create a separate class or lambda.

### Example: Sorting by Age and Name

```java
import java.util.*;

class Student {
    int rollNo;
    String name;
    int age;

    public Student(int rollNo, String name, int age) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
    }
}

// Custom Comparator for Age
class AgeComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        if (s1.age == s2.age)
            return 0;
        else if (s1.age > s2.age)
            return 1;
        else
            return -1;
    }
}

// Custom Comparator for Name
class NameComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}

public class Main {
    public static void main(String args[]) {
        ArrayList<Student> al = new ArrayList<Student>();
        al.add(new Student(101, "Vijay", 23));
        al.add(new Student(106, "Ajay", 27));
        al.add(new Student(105, "Jai", 21));

        System.out.println("Sorting by Age:");
        Collections.sort(al, new AgeComparator());
        for (Student st : al) System.out.println(st.name + " " + st.age);

        System.out.println("\nSorting by Name:");
        Collections.sort(al, new NameComparator());
        for (Student st : al) System.out.println(st.name + " " + st.age);
    }
}
```

### Modern Java Way (Lambdas - Java 8+)

You don't always need separate classes for `Comparator`.

```java
// Sort by Age
Collections.sort(al, (s1, s2) -> s1.age - s2.age);

// Sort by Name
Collections.sort(al, (s1, s2) -> s1.name.compareTo(s2.name));

// Sort by Name then Age (Chaining)
Collections.sort(al, Comparator.comparing(s -> s.name).thenComparingInt(s -> s.age));
```

## Key Differences

| Feature | Comparable | Comparator |
| :--- | :--- | :--- |
| **Package** | `java.lang` | `java.util` |
| **Method** | `compareTo(Object o)` | `compare(Object o1, Object o2)` |
| **Modification** | Must modify the original class | No need to modify the original class |
| **Logic** | Single natural sorting logic | Multiple custom sorting logics |
| **Implementation** | `Collections.sort(list)` | `Collections.sort(list, comparator)` |
