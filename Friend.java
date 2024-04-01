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

    public boolean upVotePost(int id) {
        for (int i = 0; i < super.getPosts().size(); i++) {
            if (super.getPosts().get(i).getId() == id) {
                super.getPosts().get(i).setLikes(super.getPosts().get(i).getLikes() + 1);
                return true;
            }
        }
        return false;
    }
    public boolean downVotePost(int id){
        for (int i = 0; i < super.getPosts().size(); i++) {
            if (super.getPosts().get(i).getId() == id) {
                super.getPosts().get(i).setDislikes(super.getPosts().get(i).getDislikes() + 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addPost(Post post) {
        friendPost.add(post);
    }
}
