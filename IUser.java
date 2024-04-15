import java.io.IOException;
import java.util.ArrayList;

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
