import java.util.ArrayList;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class User implements IUser, Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private ArrayList<Friend> friends = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();
    private String profilePicPath;
    private static final String PROFILE_PIC_DIR = "profile_pics/";

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void uploadProfilePicture(byte[] pictureData) throws IOException {
        Path directory = Paths.get(PROFILE_PIC_DIR);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        this.profilePicPath = PROFILE_PIC_DIR + getUsername() + "_profile_pic.jpg";
        Path file = Paths.get(profilePicPath);

        Files.write(file, pictureData);
    }
    public byte[] getProfilePicture() throws IOException {
        if (profilePicPath == null || profilePicPath.isEmpty()) {
            return null;
        }

        Path file = Paths.get(profilePicPath);
        if (Files.exists(file)) {
            return Files.readAllBytes(file);
        } else {
            return null;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User check = (User) o;
        return this.username.equals(check.username);
    }

    public String toString() {
        String result = "Username:" + username + "\n" +
                "Friends: ";
        if (friends.size() == 0) {
            result += "No friends";
        } else {
            for (int i = 0; i < friends.size(); i++) {
                if (i < friends.size() - 1) {
                    result += friends.get(i).getUsername();
                    result += ",";
                } else {
                    result += friends.get(i).getUsername();
                }
            }
        }
        return result;
    }

    public boolean addFriend(User user) {
        Friend friend = new Friend(user.getUsername(), user.getPassword(), false);
        if (friends != null) {
            for (int i = 0; i < friends.size(); i++) {
                if (friends.get(i).equals(friend)) {
                    return false;
                }
            }
        }
        friends.add(friend);
        return true;
    }

    public boolean removeFriend(User user) {
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).equals(user)) {
                friends.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean blockFriends(Friend user) {
        for (int i = 0; i < friends.size(); i++) {
            if (friends.get(i).equals(user)) {
                friends.get(i).setBlock(true);
                return true;
            }
        }
        return false;
    }

    public boolean deletePost(int id) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == id) {
                posts.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean deleteComment(int id) {
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).getId() == id) {
                comments.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean upVoteComment(Comment comment) {
        if (comment == null) {
            return false;
        }
        comment.upVote();
        return true;
    }

    public boolean downVoteComment(Comment comment) {
        if (comment == null) {
            return false;
        }
        comment.downVote();
        return true;
    }

    public boolean upVotePost(Post post) {
        if (post == null) {
            return false;
        }
        post.upVote();
        return true;
    } //PHASE 3: UPVOTE POSTS FROM FEED

    public boolean downVotePost(Post post) {
        if (post == null) {
            return false;
        }
        post.downVote();
        return true;
    } //PHASE 3: DOWNVOTE POSTS FROM FEED

    public boolean hidePost(int postId) {
        for (Post post: posts) {
            if (post.getId() == postId) {
                post.setHide(true);
                return true;
            }
        }
        return false;
    } //PHASE 3: HIDE POSTS FROM FEED

    public boolean unhidePost(int postId) {
        for (Post post: posts) {
            if (post.getId() == postId) {
                post.setHide(false);
                return true;
            }
        }
        return false;
    } //PHASE 3: UNHIDING POSTS FROM FEED

    public void addPost(Post post) {
        posts.add(post);
    }

    public void displayFriendPosts() {
        for (Friend f : friends) {
            for (Post p : f.getFriendPost()) {
                System.out.println(p.toString());
            }
        }
    } //PHASE 3: DISPLAY FRIENDS POSTS

    public void addComment(Post post, Comment comment) {
        if (post != null && comment != null) {
            post.addComment(comment);
            comments.add(comment);
        }
    } //PHASE 3: SERVER ADD COMMENTS


}
