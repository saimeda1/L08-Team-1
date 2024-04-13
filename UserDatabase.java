import java.util.ArrayList;
import java.io.Serializable;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class UserDatabase implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();

    public UserDatabase(ArrayList<User> users) {
        this.users = users;
    }

    public boolean signUp(User user) {
        for (User u : users) {
            if (u.equals(user)) {
                return false;
            }
        }
        users.add(user);
        return true;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts() {
        for (User u : users) {
            for (Post p : u.getPosts()) {
                posts.add(p);
            }
        }
    }

    public boolean addComment(Comment comment, Post post) {
        setPosts();
        for (int i = 0; i < posts.size(); i++) {
            if (post.equals(posts.get(i))) {
                ArrayList<Comment> c = posts.get(i).getComments();
                c.add(comment);
                posts.get(i).setComments(c);
                return true;
            }
        }
        return false;
    }

    public boolean logIn(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User searchUser(String search) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(search)) {
                return users.get(i);
            }
        }
        return null;
    }


}
