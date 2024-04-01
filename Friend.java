import java.util.ArrayList;

public class Friend extends User {

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
