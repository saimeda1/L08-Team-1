import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class NewClient {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ClientGUI gui;
    private User currentUser;

    public NewClient(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to server at " + host + ":" + port);
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
                SwingUtilities.invokeLater(() -> {
                    gui.switchToPostView();
                    gui.displayMessage("Login successful. Welcome, " + currentUser.getUsername() + "!");
                });
            } else {
                String errorMessage = (String) in.readObject();
                SwingUtilities.invokeLater(() -> gui.displayMessage("Login failed: " + errorMessage));
            }
        } catch (IOException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Connection error: " + e.getMessage()));
        }
    }


    public void handleLogout() {
        try {
            out.writeObject("logout");
            out.flush();

            SwingUtilities.invokeLater(() -> {
                gui.switchToLoginView();
                gui.displayMessage("Logged out successfully.");
            });
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Connection error: " + e.getMessage()));
        }
    }

    public void handleRegister(String username, String password) {
        try {
            out.writeObject("register");
            out.writeObject(username);
            out.writeObject(password);
            out.flush();

            boolean result = in.readBoolean();
            if (result) {
                String message = (String) in.readObject();
                SwingUtilities.invokeLater(() -> gui.displayMessage("Registration successful: " + message));
            } else {
                String errorMessage = (String) in.readObject();
                SwingUtilities.invokeLater(() -> gui.displayMessage("Registration failed: " + errorMessage));
            }
        } catch (IOException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Connection error: " + e.getMessage()));
        }
    }

    public void handleAddPost(String content) {
        try {
            out.writeObject("addpost");
            out.writeObject(new Post(content,currentUser, false));
            out.flush();

            boolean result = in.readBoolean();
            if (result) {
                SwingUtilities.invokeLater(() -> gui.displayMessage("Post added successfully."));
            } else {
                SwingUtilities.invokeLater(() -> gui.displayMessage("Failed to add post."));
            }
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Error sending post: " + e.getMessage()));
        }
    }

    public void handleAddComment(String content, int postId) {
        try {
            out.writeObject("addcomment");
            out.writeObject(new Comment(content, currentUser));
            out.flush();

            boolean result = in.readBoolean();
            if (result) {
                SwingUtilities.invokeLater(() -> gui.displayMessage("Comment added successfully."));
            } else {
                SwingUtilities.invokeLater(() -> gui.displayMessage("Failed to add comment."));
            }
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Error sending comment: " + e.getMessage()));
        }
    }

    public void handleSearchUser(String username) {
        try {
            out.writeObject("searchuser");
            out.writeObject(username);
            out.flush();
            boolean success = in.readBoolean();
            if (success) {
                User user = (User) in.readObject();
                SwingUtilities.invokeLater(() -> gui.displayMessage("User found: " + user.getUsername()));
            } else {
                String message = (String) in.readObject();
                SwingUtilities.invokeLater(() -> gui.displayMessage(message));
            }
        } catch (IOException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Error searching user: " + e.getMessage()));
        }
    }
    public void reconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
            socket = new Socket(socket.getInetAddress().getHostName(), socket.getPort());
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            gui.displayMessage("Reconnected to the server.");
        } catch (IOException e) {
            gui.displayMessage("Failed to reconnect: " + e.getMessage());
        }
    }


    public void handleFriendRequest(String friendUsername, boolean isBlock) {
        try {
            out.writeObject("friendrequest");
            out.writeObject(friendUsername);
            out.writeBoolean(isBlock);
            out.flush();

            boolean result = in.readBoolean();
            SwingUtilities.invokeLater(() -> gui.displayMessage(result ? "Request sent successfully." : "Failed to send request."));
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Connection error, attempting to reconnect..."));
            reconnect();
            // Try again after reconnecting, if critical, add retry logic here
            try {
                out.writeObject("friendrequest");
                out.writeObject(currentUser.getUsername());
                out.writeObject(friendUsername);
                out.writeBoolean(isBlock);
                out.flush();

                boolean result = true;
                SwingUtilities.invokeLater(() -> gui.displayMessage(result ? "Request sent successfully." : "Failed to send request."));
            } catch (IOException e1) {
                SwingUtilities.invokeLater(() -> gui.displayMessage("Failed to send request after reconnecting: " + e1.getMessage()));
            }
        }
    }

    public void handleDeleteComment(int commentId) {
        try {
            out.writeObject("deletecomment");
            out.writeObject(commentId);
            out.flush();

            boolean result = in.readBoolean();
            SwingUtilities.invokeLater(() -> gui.displayMessage(result ? "Comment deleted successfully." : "Failed to delete comment."));
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Error deleting comment: " + e.getMessage()));
        }
    }

    public void handleDeletePost(int postId) {
        try {
            out.writeObject("deletepost");
            out.writeObject(postId);
            out.flush();

            boolean result = in.readBoolean();
            SwingUtilities.invokeLater(() -> gui.displayMessage(result ? "Post deleted successfully." : "Failed to delete post."));
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Error deleting post: " + e.getMessage()));
        }
    }

    public void fetchFriendPosts() {
        try {
            out.writeObject("fetchFriendPosts");
            out.writeObject(currentUser.getUsername());
            out.flush();

            Object response = in.readObject();
            if (response instanceof String[]) {
                String[] posts = (String[]) response;
                gui.displayFriendPosts(posts);
            } else {
                SwingUtilities.invokeLater(() -> gui.displayMessage("Failed to fetch friend posts."));
            }
        } catch (IOException | ClassNotFoundException e) {
            SwingUtilities.invokeLater(() -> gui.displayMessage("Error fetching friend posts: " + e.getMessage()));
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1112;
        NewClient client = new NewClient(host, port);
        SwingUtilities.invokeLater(() -> {
            ClientGUI gui = new ClientGUI(client);
            client.setGUI(gui);
            gui.setVisible(true);
        });
    }
}
