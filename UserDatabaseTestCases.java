import java.util.ArrayList;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class UserDatabaseTestCases {

    private UserDatabase userDatabase;
    private User user1;
    private User user2;
    private Post post1;
    private Comment comment1;

    @Before
    public void setUp() {
        // Initialize users
        user1 = new User("user1", "password1");
        user2 = new User("user2", "password2");

        // Initialize post and comment
        post1 = new Post("Test post by user1", user1, false);
        comment1 = new Comment("Test comment by user2", user2);

        // Add users to a list
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);

        // Initialize UserDatabase with a list of users
        userDatabase = new UserDatabase(users);
    }

    @Test
    public void testSignUpExistingUser() {
        assertFalse("SignUp should fail for an existing user", userDatabase.signUp(user1));
    }

    @Test
    public void testSignUpNewUser() {
        assertTrue("SignUp should succeed for a new user", userDatabase.signUp(user2));
    }

    @Test
    public void testLogInSuccess() {
        assertTrue("Login should succeed for correct username and password", userDatabase.logIn(new User("user1", "password1")));
    }

    @Test
    public void testLogInFailure() {
        assertFalse("Login should fail for incorrect password", userDatabase.logIn(new User("user1", "wrongPassword")));
    }

    @Test
    public void testAddCommentToPost() {
        userDatabase.signUp(user2);
        user1.addPost(post1);
        userDatabase.setPosts();
        assertTrue("Adding a comment to a post should succeed", userDatabase.addComment(comment1, post1));
        assertEquals("Post should have 1 comment after addition", 1, post1.getComments().size());
    }

    @Test
    public void testSearchUserFound() {
        User foundUser = userDatabase.searchUser("user1");
        assertNotNull("Search should find the user", foundUser);
        assertEquals("Found user should have the username 'user1'", "user1", foundUser.getUsername());
    }

    @Test
    public void testSearchUserNotFound() {
        User foundUser = userDatabase.searchUser("nonexistentUser");
        assertNull("Search should not find the user", foundUser);
    }

    // Add more tests as necessary for full coverage.
}

