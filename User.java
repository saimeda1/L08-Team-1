package PACKAGE_NAME;

import java.util.ArrayList;
import java.util.List;
public class User {
    private int id; //internal number of user
    private String username;
    private String password;
    private List<Post> posts;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public User() {
        this.id = 0;
        this.username = null;
        this.password = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }

    // Method to add a post
    public void addPost(String content) {
        this.posts.add(new Post(this.id, content));
    }

    // Method to get the user's posts
    public List<Post> getPosts() {
        return this.posts;
    }

    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User check = (User) o;
        return this.id == check.id;
    }
}
