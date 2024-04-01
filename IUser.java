import java.util.ArrayList;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public interface IUser {
    String getUsername();
    String getPassword();
    void setPassword(String password);
    void setUsername(String username);
    ArrayList<Post> getPosts();
    ArrayList<Comment> getComments();
    ArrayList<Friend> getFriends();
    boolean addFriend(User user);
    boolean removeFriend(User user);
    void addPost(Post post);
    boolean deletePost(int id);
    void addComment(Post post, Comment comment);
    boolean deleteComment(int id);
    void displayFriendPosts();
}

