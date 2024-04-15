import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class UserDatabase implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();

    public UserDatabase() {
        this.users = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public UserDatabase(ArrayList<User> users) {
        this.users = users;
    }

    public synchronized boolean signUp(User newUser) {
        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                return false;
            }
        }
        users.add(newUser);
        return true;
    }

    public synchronized boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }


    public synchronized boolean deleteUser(User user) {
        ArrayList<User> userList = new ArrayList<>();
        boolean check = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                check = true;
                continue;
            }
            userList.add(users.get(i));
        }
        this.users = userList;
        return check;
    }

    public synchronized boolean addPost(Post post) {
        if (posts == null) {
            posts = new ArrayList<>();
        }
        return posts.add(post);
    }
    public ArrayList<Post> getPosts() {
        return posts;
    }

    public synchronized void setPosts() {
        for (User u : users) {
            for (Post p : u.getPosts()) {
                posts.add(p);
            }
        }
    }

    public synchronized boolean addComment(Comment comment, Post post) {
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

    public synchronized boolean logIn(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public synchronized User searchUser(String search) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(search)) {
                return users.get(i);
            }
        }
        return null;
    }

    public synchronized boolean addCommentToPost(Comment comment, int postId) {
        setPosts();
        for (Post p : posts) {
            if (p.getId() == postId) {
                p.getComments().add(comment);
                return true;
            }
        }
        return false;
    }

    public synchronized User validateUser(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

}
