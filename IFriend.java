import java.util.ArrayList;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phrase 1
 */
public interface IFriend {
    boolean isBlock();
    void setBlock(boolean block);
    ArrayList<Post> getFriendPost();
    boolean upVotePost(Post post);
    boolean downVotePost(Post post);
    void addPost(Post post);
}
