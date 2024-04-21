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
                User user = (User) in.readObject();  // Assuming User class is serializable and available
                SwingUtilities.invokeLater(() -> {
                    gui.switchToPostView();
                    gui.displayMessage("Login successful. Welcome, " + user.getUsername() + "!");
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
            out.writeObject(new Post(content,currentUser, false));  // Assuming Post class is serializable and available
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
            out.writeObject(new Comment(content, currentUser));  // Assuming Comment class is serializable and available
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
