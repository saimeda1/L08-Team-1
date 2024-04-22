import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.15
 * The Client class represents a client application that interacts
 * with a server through a socket connection. It provides functionality
 * for user authentication, posting content, commenting on posts,
 * and logging out.
 * This is for phase 2.
 */
public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Scanner scanner;
    private boolean loggedIn = false;
    private User currentUser;
    // User object to keep track of the currently logged-in user

    public Client(String host, int port) {
        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            scanner = new Scanner(System.in);
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
            System.exit(1);
        }
    }

    public void start() {
        try {
            while (true) {
                if (!loggedIn) {
                    System.out.println("Enter command (login, register, exit):");
                } else {
                    System.out.println("Enter command (addcomment, addpost, logout, exit):");
                }
                String command = scanner.nextLine();
                if ("exit".equalsIgnoreCase(command)) {
                    break;
                }
                processCommand(command);
            }
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }
    }

    private void processCommand(String command) throws IOException, ClassNotFoundException {
        switch (command.toLowerCase()) {
            case "login":
                handleLogin();
                break;
            case "register":
                handleRegister();
                break;
            case "logout":
                handleLogout();
                break;
            case "addpost":
                if (loggedIn) handleAddPost();
                else System.out.println("Please login to add a post.");
                break;
            case "addcomment":
                if (loggedIn) handleAddComment();
                else System.out.println("Please login to add a comment.");
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private void handleLogin() throws IOException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        out.writeObject("login");
        out.writeObject(username);
        out.writeObject(password);
        out.flush();

        try {
            boolean result = in.readBoolean();  // Reading the login result
            if (result) {
                currentUser = (User) in.readObject();  // Expecting a user object on success
                loggedIn = true;
                System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
            } else {
                String errorMessage = (String) in.readObject();  // Reading error message on failure
                System.out.println("Login failed: " + errorMessage);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error reading user data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleLogout() throws IOException {
        out.writeObject("logout");
        out.flush();

        loggedIn = false;
        currentUser = null;
        System.out.println("Logged out successfully.");
    }

    private void handleRegister() throws IOException, ClassNotFoundException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        out.writeObject("register");
        out.writeObject(username);
        out.writeObject(password);
        out.flush();

        boolean result = in.readBoolean();
        String message = (String) in.readObject();
        System.out.println(message);
    }

    private void handleAddPost() throws IOException {
        if (!loggedIn || currentUser == null) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Enter the post content:");
        String content = scanner.nextLine();
        Post post = new Post(content, currentUser, false);

        try {
            out.writeObject("addpost");
            out.writeObject(post);
            out.flush();

            boolean result = in.readBoolean();  // Reading the response from the server
            if (result) {
                System.out.println("Post added successfully.");
            } else {
                System.out.println("Failed to add post.");
            }
        } catch (Exception e) {
            System.err.println("Error during add post: " + e.toString());
            e.printStackTrace();
        }
    }



    private void handleAddComment() throws IOException {
        System.out.println("Enter post ID to comment on:");
        int postId;
        try {
            postId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for post ID. Please enter a numeric value.");
            return;
        }

        System.out.println("Enter your comment:");
        String content = scanner.nextLine();
        Comment comment = new Comment(content, currentUser);  // Assuming Comment constructor

        out.writeObject("addcomment");
        out.writeObject(comment);
        out.writeObject(postId);
        out.flush();

        readResponse();
    }

    private void readResponse() throws IOException {
        try {
            boolean result = in.readBoolean();
            if (result) {
                System.out.println("Action completed successfully.");
            } else {
                System.out.println("Action failed.");
            }
        } catch (IOException e) {
            System.err.println("Error while reading response: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void handleSearchUser() throws IOException, ClassNotFoundException {
        System.out.println("Enter username to search:");
        String username = scanner.nextLine();
        out.writeObject("searchuser");
        out.writeObject(username);
        out.flush();
        boolean success = in.readBoolean();
        if (success) {
            User user = (User) in.readObject();
            System.out.println("User found: " + user.getUsername());
        } else {
            String message = (String) in.readObject();
            System.out.println(message);
        }
    }
    private void handleFriendRequest(boolean isBlock) throws IOException {
        System.out.println("Enter friend's username:");
        String friendUsername = scanner.nextLine();
        out.writeObject("friendrequest");
        out.writeObject(friendUsername);
        out.writeBoolean(isBlock);
        out.flush();
        readResponse();
    }

    private void handleDeleteComment() throws IOException {
        System.out.println("Enter comment ID:");
        int commentId = Integer.parseInt(scanner.nextLine());
        out.writeObject("deletecomment");
        out.writeObject(commentId);
        out.flush();
        readResponse();
    }

    private void handleDeletePost() throws IOException {
        if (!loggedIn || currentUser == null) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Enter post ID:");
        int postId = Integer.parseInt(scanner.nextLine());

        out.writeObject("deletepost");
        out.writeObject(currentUser.getUsername());  // Send the username first
        out.writeObject(postId);
        out.flush();

        boolean result = in.readBoolean();
        if (result) {
            System.out.println("Post deleted successfully.");
        } else {
            System.out.println("Failed to delete post.");
        }
    }

    private void closeResources() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (scanner != null) scanner.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1112);
        client.start();
    }


}
