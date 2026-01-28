/**
 * PRACTICE QUESTION: Library Management System (Null Object Pattern)
 * 
 * Best Practices version:
 * 1. Singleton: Only one Null Object exists (saves memory).
 * 2. Null-Safety at Assignment: Ensure the field is NEVER null inside the
 * class.
 * 3. Immutability: Use final fields.
 */

interface Borrower {
    String getName();
}

// 1. Concrete Class
class RegisteredUser implements Borrower {
    private final String name; // 'final' for best practice

    public RegisteredUser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

// 2. Null Object (as a Singleton)
class NoBorrower implements Borrower {
    // Singleton instance: Since every 'NoBorrower' behaves identical,
    // we don't need multiple objects of this class.
    private static final NoBorrower instance = new NoBorrower();

    private NoBorrower() {
    } // Private constructor to prevent new instances

    public static NoBorrower getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "Available for borrowing";
    }
}

class Book {
    private final String title;
    private final Borrower borrower;

    public Book(String title, Borrower borrower) {
        this.title = title;
        // BEST PRACTICE: Guard against null input right at the constructor.
        // This ensures 'borrower' is NEVER null inside this class.
        this.borrower = (borrower == null) ? NoBorrower.getInstance() : borrower;
    }

    public String getStatus() {
        // Now this code is 100% safe from NullPointerException!
        return "Book: " + title + " | Borrower: " + borrower.getName();
    }
}

public class NullObject_Practice {
    public static void main(String[] args) {
        // Test Case 1: Borrowed Book
        Borrower user = new RegisteredUser("Rohit");
        Book b1 = new Book("Design Patterns", user);

        // Test Case 2: Available Book (passing null intentionally)
        Book b2 = new Book("Java Basics", null);

        System.out.println(b1.getStatus());
        System.out.println(b2.getStatus());
    }
}
