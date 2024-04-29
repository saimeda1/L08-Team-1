import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class CommentTestCases {

    private User author;
    private Comment comment;

    @Before
    public void setUp() {
        // Initialize the User object here. Replace "username" with an actual username.
        author = new User("username", "password");
        // Initialize your Comment object here
        comment = new Comment("This is a test comment.", author);
    }

    @Test
    public void testCommentConstructor() {
        assertNotNull("Comment object should not be null", comment);
        assertEquals("The content of the comment does not match", "This is a test comment.", comment.getContent());
        assertEquals("The author of the comment does not match", "username", comment.getAuthor().getUsername());
        assertEquals("Initial likes should be 0", 0, comment.getLikes());
        assertEquals("Initial dislikes should be 0", 0, comment.getDislikes());
    }

    @Test
    public void testUpVote() {
        int initialLikes = comment.getLikes();
        comment.upVote();
        assertEquals("Likes should be incremented by 1", initialLikes + 1, comment.getLikes());
    }

    @Test
    public void testDownVote() {
        int initialDislikes = comment.getDislikes();
        comment.downVote();
        assertEquals("Dislikes should be incremented by 1", initialDislikes + 1, comment.getDislikes());
    }

    @Test
    public void testSetLikes() {
        comment.setLikes(5);
        assertEquals("Likes should be set to 5", 5, comment.getLikes());
    }

    @Test
    public void testSetDislikes() {
        comment.setDislikes(3);
        assertEquals("Dislikes should be set to 3", 3, comment.getDislikes());
    }

    @Test
    public void testToString() {
        String expected = "-------------------------------\n" +
                "Comment: " + comment.getId() + "\n" +
                "This is a test comment.\n" +
                "by username\n" +
                "at " + comment.getTime() + "\n" +
                "likes: 0\n" +
                "dislikes: 0\n";
        assertEquals("toString output does not match expected output", expected, comment.toString());
    }
}
