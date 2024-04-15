import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.15
 * The IUser interface represents the contract for a user in a social media system.
 * It defines methods for managing user data and interactions.
 * This is for phase 2.
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
    void uploadProfilePicture(byte[] pictureData) throws IOException;
    byte[] getProfilePicture() throws IOException;
}
