import java.util.ArrayList;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public interface IUserDatabase {
    boolean signUp(User user);
    ArrayList<Post> getPosts();
    void setPosts();
    boolean addComment(Comment comment, Post post);
    boolean logIn(User user);
    User searchUser(String search);
}
