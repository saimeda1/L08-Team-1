import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDatabaseTest {
    private UserDatabase userDatabase;
    private User testUser;

    @Before
    public void setUp() {
        // Initial setup before each test case
        testUser = new User(1, "testUser", "password");
        User[] initialUsers = {testUser};
        userDatabase = new UserDatabase(initialUsers);
    }

    @Test
    public void testAddUserSuccess() {
        User newUser = new User(2, "newUser", "password123");
        boolean result = userDatabase.addUser(newUser);
        assertTrue("Adding a new user should return true.", result);
    }

    @Test
    public void testAddUserFailure() {
        // Attempting to add a user that already exists
        boolean result = userDatabase.addUser(testUser);
        assertFalse("Adding an existing user should return false.", result);
    }

    @Test
    public void testDeleteUserSuccess() {
        boolean result = userDatabase.deleteUser(testUser);
        assertFalse("Deleting an existing user should return false, indicating success.", result);
    }

    @Test
    public void testDeleteUserFailure() {
        User nonExistentUser = new User(3, "nonExistentUser", "pass");
        boolean result = userDatabase.deleteUser(nonExistentUser);
        assertTrue("Deleting a non-existent user should return true, indicating failure.", result);
    }
}
