import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Friend> friends = new ArrayList<>();
    private ArrayList<Post> posts = new ArrayList<>();
    private ArrayList<Comment> comments = new ArrayList<>();

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

    public void setPassword() {
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

    public void addPost(Post post) {
        posts.add(post);
    }

    public void displayFriendPost() {
        for (Friend f : friends) {
            for (Post p : f.getFriendPost()) {
                System.out.println(p.toString());
            }
        }
    }

    public void addComment(Post post, Comment comment) {

    }


}
