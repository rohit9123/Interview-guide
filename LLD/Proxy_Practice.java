/**
 * REFINEMENTS MADE:
 * 1. Interface Implementation: DatabaseProxy now correctly 'implements
 * Database'.
 * 2. Logic Correction: Fixed RealDatabase.delete and Proxy access logic.
 * 3. Security: Used .equals() for role comparison (Best practice over ==).
 * 4. Lazy Initialization: Proxy only creates RealDatabase when first needed
 * (Performance).
 * 5. Testing: Added a main method to verify different roles/permissions.
 */

interface Database {
    void select(int id);

    void delete(int id);
}

// THE REAL OBJECT (Heavy or Restricted)
class RealDatabase implements Database {
    public RealDatabase() {
        System.out.println("Connecting to heavy database... (Simulated)");
    }

    @Override
    public void select(int id) {
        System.out.println("Selecting data for ID: " + id);
    }

    @Override
    public void delete(int id) {
        System.out.println("DELETING data for ID: " + id);
    }
}

// THE PROXY (Intermediary)
class DatabaseProxy implements Database {
    private RealDatabase realDatabase; // Lazy initialized
    private final String role;

    public DatabaseProxy(String role) {
        this.role = role;
    }

    @Override
    public void select(int id) {
        // Everyone can select
        ensureDatabaseInitialized();
        realDatabase.select(id);
    }

    @Override
    public void delete(int id) {
        // Role check (Protection Proxy)
        if ("ADMIN".equalsIgnoreCase(role)) {
            ensureDatabaseInitialized();
            realDatabase.delete(id);
        } else {
            System.out.println("ACCESS DENIED: Role '" + role + "' cannot delete data.");
        }
    }

    private void ensureDatabaseInitialized() {
        if (realDatabase == null) {
            realDatabase = new RealDatabase();
        }
    }
}

// CLIENT
public class Proxy_Practice {
    public static void main(String[] args) {
        System.out.println("--- TESTING WITH USER ROLE ---");
        Database userProxy = new DatabaseProxy("USER");
        userProxy.select(101);
        userProxy.delete(101); // Should fail

        System.out.println("\n--- TESTING WITH ADMIN ROLE ---");
        Database adminProxy = new DatabaseProxy("ADMIN");
        adminProxy.select(202);
        adminProxy.delete(202); // Should pass
    }
}