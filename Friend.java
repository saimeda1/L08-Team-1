import java.util.ArrayList;
import java.io.Serializable;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phrase 1
 */
public class Friend extends User implements IFriend, Serializable {
    private static final long serialVersionUID = 1L;
    private boolean block;
    ArrayList<Post> friendPost = new ArrayList<>();

    public Friend(String username, String password, boolean block) {
        super(username, password);
        this.block = block;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public ArrayList<Post> getFriendPost() {
        return friendPost;
    }

    public boolean upVotePost(Post post) {
        if (post == null) {
            return false;
        }
        post.upVote();
        return true;
    }

    public boolean downVotePost(Post post) {
        if (post == null) {
            return false;
        }
        post.downVote();
        return true;
    }

    @Override
    public void addPost(Post post) {
        friendPost.add(post);
    }
}
