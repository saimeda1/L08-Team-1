import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class PostTestCases {

    private Post post;
    private User author;
    private Comment comment;

    @Before
    public void setUp() {
        // Initialize the User object here. Replace "authorName" with an actual username.
        author = new User("authorName", "password");
        // Initialize your Post object here
        post = new Post("This is a test post", author, false);
        // Initialize a Comment object. Adjust according to your Comment constructor
        comment = new Comment("This is a test comment", author);
    }

    @Test
    public void testPostConstructor() {
        assertNotNull("Post object should not be null", post);
        assertEquals("Content of the post does not match", "This is a test post", post.getContent());
        assertEquals("Author of the post does not match", "authorName", post.getAuthor().getUsername());
        assertFalse("Hide should be false", post.isHide());
        assertEquals("Initial likes should be 0", 0, post.getLikes());
        assertEquals("Initial dislikes should be 0", 0, post.getDislikes());
        assertTrue("Time should be initialized", post.getTime() != null);
    }

    @Test
    public void testUpVote() {
        int initialLikes = post.getLikes();
        post.upVote();
        assertEquals("Likes should be incremented by 1", initialLikes + 1, post.getLikes());
    }

    @Test
    public void testDownVote() {
        int initialDislikes = post.getDislikes();
        post.downVote();
        assertEquals("Dislikes should be incremented by 1", initialDislikes + 1, post.getDislikes());
    }

    @Test
    public void testSetAndGetComments() {
        ArrayList<Comment> comments = new ArrayList<>();
        comments.add(comment);
        post.setComments(comments);
        assertEquals("Comments list should contain 1 comment", 1, post.getComments().size());
        assertTrue("The comment in the post should be the one we added", post.getComments().contains(comment));
    }

    @Test
    public void testToString() {
        String expectedOutputWithoutComments = "PostID: " + post.getId() + "\n" +
                "Post: This is a test post\n" + "by authorName\n" + "at " +
                post.getTime() + "\n" + "likes: 0\n" + "dislikes: 0\n";
        assertEquals("toString output does not match expected output", expectedOutputWithoutComments, post.toString().trim());
    }

    @Test
    public void testHidePost() {
        post.setHide(true);
        assertTrue("Post should be marked as hidden", post.isHide());
        assertEquals("toString should reflect hidden status", "This post has been hidden\n", post.toString().trim());
    }

}

