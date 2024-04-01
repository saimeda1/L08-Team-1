import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phrase 1
 */
public class FriendTestCases {

    private Friend friend;
    private Post post;

    @Before
    public void setUp() {
        // Initialize Friend object
        friend = new Friend("friendName", "friendPassword", false);

        // Initialize Post object
        post = new Post("This is a friend's test post", friend, false); // Assuming Post constructor
    }

    @Test
    public void testBlockUnblock() {
        assertFalse("Initially friend should not be blocked", friend.isBlock());

        friend.setBlock(true);
        assertTrue("Friend should be blocked", friend.isBlock());

        friend.setBlock(false);
        assertFalse("Friend should be unblocked", friend.isBlock());
    }

    @Test
    public void testAddPost() {
        friend.addPost(post);
        assertEquals("Friend post list should contain 1 post after addition", 1, friend.getFriendPost().size());
    }

    @Test
    public void testUpVotePost() {
        friend.addPost(post);
        int initialLikes = post.getLikes();
        friend.upVotePost(post);
        assertEquals("Post likes should be incremented by 1", initialLikes + 1, post.getLikes());
    }

    @Test
    public void testDownVotePost() {
        friend.addPost(post);
        int initialDislikes = post.getDislikes();
        friend.downVotePost(post);
        assertEquals("Post dislikes should be incremented by 1", initialDislikes + 1, post.getDislikes());
    }

    // Optional: Test for voting on a null post, expecting false
    @Test
    public void testVoteOnNullPost() {
        assertFalse("Voting on a null post should return false", friend.upVotePost(null));
        assertFalse("Voting on a null post should return false", friend.downVotePost(null));
    }
}
