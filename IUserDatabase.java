import java.util.ArrayList;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public interface IUserDatabase {
    boolean signUp(User newUser);
    boolean userExists(String username);
    boolean deleteUser(User user);
    boolean addPost(Post post);
    ArrayList<Post> getPosts();
    boolean addComment(Comment comment, Post post);
    boolean logIn(User user);

    User searchUser(String search);
    boolean addCommentToPost(Comment comment, int postId);
    User validateUser(String username, String password);
}
