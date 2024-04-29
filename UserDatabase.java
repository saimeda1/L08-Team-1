
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

    public synchronized boolean addPost(Post post, String username) {
        User user = searchUser(username);
        if (user == null) {
            return false;
        }
        user.getPosts().add(post);  // Add post to the user's own list

        // Synchronize the post addition across friends who have this user as a friend
        for (User u : users) {
            for (Friend f : u.getFriends()) {
                if (f.getUsername().equalsIgnoreCase(username)) {
                    f.getPosts().add(post);  // Friend class must handle post lists similarly to User
                }
            }
        }
        return true;
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
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(search.trim())) {
                return user;
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
    public synchronized boolean manageFriendRequest(String requestingUsername, String targetUsername, boolean b) {
        User requestingUser = searchUser(requestingUsername);
        User targetUser = searchUser(targetUsername);

        if (requestingUser == null || targetUser == null) {
            System.out.println("Either the requesting user or the target user does not exist.");
            return false;  // Return false if either user does not exist.
        }

        if (requestingUsername.equals(targetUsername)) {
            System.out.println("A user cannot add themselves as a friend.");
            return false;
        }

        // Check if the target user is already a friend
        for (Friend friend : requestingUser.getFriends()) {
            if (friend.getUsername().equals(targetUsername)) {
                System.out.println("These users are already friends.");
                return false;  // They are already friends, no action needed
            }
        }

        // Add new friend if not already friends
        Friend newFriend = new Friend(targetUsername, targetUser.getPassword(), false); // Assume block status is initially false
        requestingUser.getFriends().add(newFriend);
        System.out.println("Friend added successfully: " + targetUsername);
        return true;
    }


    public synchronized boolean deletePost(int postId) {
        return posts.removeIf(post -> post.getId() == postId);
    }

    public synchronized boolean deleteComment(int commentId) {
        for (Post post : posts) {
            if (post.getComments().removeIf(comment -> comment.getId() == commentId)) {
                return true;
            }
        }
        return false;
    }
    public synchronized ArrayList<Post> getFriendPosts(User user) {
        ArrayList<Post> friendPosts = new ArrayList<>();
        for (Friend friend : user.getFriends()) {
            if (!friend.isBlock()) {
                User friendUser = searchUser(friend.getUsername());
                if (friendUser != null) {
                    friendPosts.addAll(friendUser.getPosts());
                }
            }
        }
        return friendPosts;
    }
    public synchronized boolean upVotePost(int postId) {
        for (Post p : posts) {
            if (p.getId() == postId) {
                p.upVote(); 
                return true;
            }
        }
        return false;
    }

    public synchronized boolean downVotePost(int postId) {
        for (Post p : posts) {
            if (p.getId() == postId) {
                p.downVote(); 
                return true;
            }
        }
        return false;
    }

    public synchronized boolean upVoteComment(int commentId) {
        for (Post post : posts) {
            for (Comment c : post.getComments()) {
                if (c.getId() == commentId) {
                    c.upVote(); 
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized boolean downVoteComment(int commentId) {
        for (Post post : posts) {
            for (Comment c : post.getComments()) {
                if (c.getId() == commentId) {
                    c.downVote(); 
                    return true;
                }
            }
        }
        return false;
    }

}
