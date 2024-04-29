import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class NewClient {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ClientGUI gui;
    private User currentUser;
    private boolean loggedIn = false;

    public NewClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
            System.exit(1);
        }
    }

    public void setGUI(ClientGUI gui) {
        this.gui = gui;
    }

    public void handleLogin(String username, String password) {
        try {
            out.writeObject("login");
            out.writeObject(username);
            out.writeObject(password);
            out.flush();

            boolean result = in.readBoolean();
            if (result) {
                currentUser = (User) in.readObject();
                loggedIn = true;
                gui.displayMessage("Login successful. Welcome, " + currentUser.getUsername() + "!");
            } else {
                String errorMessage = (String) in.readObject();
                gui.displayMessage("Login failed: " + errorMessage);
            }
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Connection error: " + e.getMessage());
        }
    }

    public void handleSearchUser(String username) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("searchuser");
            out.writeObject(username);
            out.flush();

            boolean success = in.readBoolean();
            if (success) {
                User user = (User) in.readObject();
                gui.displayMessage("User found: " + user.getUsername());
            } else {
                String message = (String) in.readObject();
                gui.displayMessage("User not found: " + message);
            }
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Error searching user: " + e.getMessage());
        }
    }


    public void handleLogout() {
        try {
            out.writeObject("logout");
            out.flush();
            loggedIn = false;
            currentUser = null;
            gui.displayMessage("Logged out successfully.");
        } catch (IOException e) {
            gui.displayMessage("Connection error: " + e.getMessage());
        }
    }

    public void handleRegister(String username, String password) {
        try {
            out.writeObject("register");
            out.writeObject(username);
            out.writeObject(password);
            out.flush();

            boolean result = in.readBoolean();
            String message = (String) in.readObject();
            gui.displayMessage(result ? "Registration successful: " + message : "Registration failed: " + message);
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Connection error: " + e.getMessage());
        }
    }

    public void handleAddPost(String content) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("addPost");  // First send the command
            out.writeObject(currentUser.getUsername());  // Then send the username
            out.writeObject(new Post(content, currentUser, false));  // Finally, send the Post object
            out.flush();

            boolean result = in.readBoolean();
            String message = (String) in.readObject();
            gui.displayMessage(result ? "Post added successfully." : "Failed to add post: " + message);
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Error sending post: " + e.getMessage());
        }
    }

    public void handleAddComment(String content, int postId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("addComment");  // First send the command
            out.writeObject(new Comment(content, currentUser));  // Then send the Comment object
            out.writeObject(postId);  // Finally, send the post ID
            out.flush();

            boolean result = in.readBoolean();
            //String message = (String) in.readObject();
            gui.displayMessage(result ? "Comment added successfully." : "Failed to add comment: " );
        } catch (IOException  e) {
            gui.displayMessage("Error sending comment: " + e.getMessage());
        }
    }

    public void handleFriendRequest(String friendUsername, boolean isBlock) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("friendrequest");
            out.writeObject(currentUser.getUsername());
            out.writeObject(friendUsername);
            out.writeBoolean(isBlock);
            out.flush();

            boolean result = in.readBoolean();
            String message = (String) in.readObject();
            gui.displayMessage(result ? "Friend request processed successfully: " + message : "Friend request failed: " + message);
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Error processing friend request: " + e.getMessage());
        }
    }

    public void handleFetchFriendPosts() {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("fetchFriendPosts");
            out.writeObject(currentUser.getUsername());
            out.flush();

            boolean success = in.readBoolean();
            if (success) {
                //ArrayList<Post> friendPosts = (ArrayList<Post>) in.readObject();
                Post[] friendPosts = (Post[]) in.readObject();
                StringBuilder postsDisplay = new StringBuilder("Friends' Posts:\n");
                for (Post post : friendPosts) {
                    postsDisplay.append("ID: ").append(post.getId()).append("\n").append(post.getContent()).append("\n")
                            .append("By: ").append(post.getAuthor().getUsername()).append("\n")
                            .append("Likes: ").append(post.getLikes()).append("\n")
                            .append("Dislikes: ").append(post.getDislikes()).append("\n");
                    for (Comment comment : post.getComments()) {
                        postsDisplay.append(comment.toString());
                    }
                }
                gui.displayMessage(postsDisplay.toString());
            } else {
                String message = (String) in.readObject();
                gui.displayMessage("Failed to fetch friends' posts: " + message);
            }
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Error fetching friend posts: " + e.getMessage());
        }
    }

    public void handleDeletePost(int postId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("deletepost");
            out.writeObject(postId);
            out.flush();

            boolean result = in.readBoolean();
            String message = (String) in.readObject();
            gui.displayMessage(result ? "Post deleted successfully." : "Failed to delete post: " + message);
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Error deleting post: " + e.getMessage());
        }
    }

    public void handleDeleteComment(int commentId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("deletecomment");
            out.write(commentId);
            out.flush();

            boolean result = in.readBoolean();
            String message = (String) in.readObject();
            gui.displayMessage(result ? "Comment deleted successfully." : "Failed to delete comment: " + message);
        } catch (IOException | ClassNotFoundException e) {
            gui.displayMessage("Error deleting comment: " + e.getMessage());
        }
    }

    public void handleUpvotePost(int postId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("upvotePost");
            out.writeInt(postId);
            out.flush();

            boolean success = in.readBoolean();
            //String message = (String) in.readObject();
            gui.displayMessage(success ? "Post upvoted successfully." : "Failed to upvote post: " );
        } catch (IOException e) {
            gui.displayMessage("Error upvoting post: " + e.getMessage());
        }
    }

    public void handleDownvotePost(int postId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("downvotePost");
            out.writeInt(postId);
            out.flush();

            boolean success = in.readBoolean();
            //String message = (String) in.readObject();
            gui.displayMessage(success ? "Post downvoted successfully." : "Failed to downvote post: " );
        } catch (IOException e) {
            gui.displayMessage("Error downvoting post: " + e.getMessage());
        }
    }

    public void handleUpvoteComment(int commentId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("upvoteComment");
            out.writeInt(commentId);
            out.flush();

            boolean success = in.readBoolean();
            //String message = (String) in.readObject();
            gui.displayMessage(success ? "Comment upvoted successfully." : "Failed to upvote comment: ");
        } catch (IOException  e) {
            gui.displayMessage("Error upvoting comment: " + e.getMessage());
        }
    }

    public void handleDownvoteComment(int commentId) {
        if (!loggedIn) {
            gui.displayMessage("You are not logged in.");
            return;
        }
        try {
            out.writeObject("downvoteComment");
            out.writeInt(commentId);
            out.flush();

            boolean success = in.readBoolean();
            //String message = (String) in.readObject();
            gui.displayMessage(success ? "Comment downvoted successfully." : "Failed to downvote comment: " );
        } catch (IOException e) {
            gui.displayMessage("Error downvoting comment: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientGUI gui = new ClientGUI(new NewClient("localhost", 1112));
            gui.setVisible(true);
        });
    }
}




