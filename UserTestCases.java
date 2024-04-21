import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class UserTestCases {

    private User user;
    private Friend friend; // Assuming Friend is a type that you can mock or instantiate
    private Post post; // Assuming Post is a type that you can mock or instantiate
    private Comment comment; // Assuming Comment is a type that you can mock or instantiate

    @Before
    public void setUp() {
        user = new User("testUser", "password123");
        friend = new Friend("friendUser", "friendPass", false); // Adjust according to your Friend constructor
        post = new Post("author",user, false); // Initialize your Post object accordingly
        comment = new Comment("This is a test comment", user); // Adjust Comment initialization as needed
    }

    @Test
    public void testUserCreation() {
        assertEquals("Username should be 'testUser'", "testUser", user.getUsername());
        assertEquals("Password should be 'password123'", "password123", user.getPassword());
    }

    @Test
    public void testAddFriend() {
        boolean result = user.addFriend(new User("friendUser", "friendPass"));
        assertTrue("Friend should be added successfully", result);
        assertEquals("Friend list should contain 1 friend", 1, user.getFriends().size());
    }

    @Test
    public void testRemoveFriend() {
        user.addFriend(new User("friendUser", "friendPass"));
        boolean result = user.removeFriend(new User("friendUser", "friendPass"));
        assertTrue("Friend should be removed successfully", result);
        assertEquals("Friend list should be empty after removal", 0, user.getFriends().size());
    }

    @Test
    public void testAddPost() {
        user.addPost(post);
        assertEquals("Posts list should contain 1 post", 1, user.getPosts().size());
    }

    @Test
    public void testDeletePost() {
        user.addPost(post);
        boolean result = user.deletePost(post.getId()); // Ensure your Post object has a getId() method
        assertTrue("Post should be deleted successfully", result);
        assertEquals("Posts list should be empty after deletion", 0, user.getPosts().size());
    }

    @Test
    public void testUpVotePost() {
        user.addPost(post);
        boolean result = user.upVotePost(post);
        assertTrue("Post should be up voted successfully", result);
        assertEquals("Post's likes should be 1", 1, post.getLikes());
    } //PHASE 3 TESTCASE

    @Test
    public void testDownVotePost() {
        user.addPost(post);
        boolean result = user.downVotePost(post);
        assertTrue("Post should be deleted successfully", result);
        assertEquals("Post's dislikes should be 1", 1, post.getDislikes());

    } //PHASE 3 TESTCASE

    @Test
    public void testHidePost() {
        user.addPost(post);
        boolean result = user.hidePost(post.getId());
        assertTrue("Post should be hidden successfully", result);
        assertEquals("Posts lists should be empty after deletion", 0, user.getPosts().size());
    } //PHASE 3 TESTCASE

    @Test
    public void testUpVoteComment() {
        boolean result = user.upVoteComment(comment);
        assertTrue("Comment should be upvoted successfully", result);
        assertEquals("Comment's likes should be 1", 1, comment.getLikes());
    }

    @Test
    public void testDownVoteComment() {
        boolean result = user.downVoteComment(comment);
        assertTrue("Comment should be downvoted successfully", result);
        assertEquals("Comment's dislikes should be 1", 1, comment.getDislikes());
    }

    @Test
    public void testEquals() {
        User user2 = new User("testUser", "differentPassword");
        assertTrue("Users with the same username should be equal", user.equals(user2));
    }

    // Add more tests as necessary for full coverage.
}

