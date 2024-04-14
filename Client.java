import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Scanner scanner;
    private boolean loggedIn = false;

    public Client(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
            this.scanner = new Scanner(System.in);
        } catch (IOException e) {
            System.err.println("Error connecting to the server: " + e.getMessage());
            System.exit(1);
        }
    }

    public void start() {
        try {
            while (true) {
                if (!loggedIn) {
                    System.out.println("Enter command (login, register):");
                } else {
                    System.out.println("Enter command (addcomment, addpost, logout):");
                }
                String command = scanner.nextLine();
                if ("exit".equalsIgnoreCase(command)) {
                    break;
                }
                processCommand(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void processCommand(String command) throws IOException {
        out.writeObject(command);
        switch (command.toLowerCase()) {
            case "login":
                handleLogin();
                break;
            case "register":
                handleRegister();
                break;
            case "addcomment":
                if (loggedIn) handleAddComment();
                else System.out.println("You need to log in first.");
                break;
            case "addpost":
                if (loggedIn) handleAddPost();
                else System.out.println("You need to log in first.");
                break;
            case "logout":
                if (loggedIn) logout();
                else System.out.println("You are not logged in.");
                break;
        }
    }

    private void handleLogin() throws IOException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        User user = new User(username, password);
        out.writeObject(user);
        try {
            boolean result = in.readBoolean();
            if (result) {
                loggedIn = true;
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRegister() throws IOException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        User user = new User(username, password);
        out.writeObject(user);
        try {
            boolean result = in.readBoolean();
            System.out.println(result ? "Registration successful" : "Registration failed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAddComment() throws IOException {
        System.out.println("Enter post ID to comment on:");
        int postId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your comment:");
        String content = scanner.nextLine();
        Comment comment = new Comment(content, new User("username", "password")); // Adjust as needed
        out.writeObject(comment);
        try {
            boolean result = in.readBoolean();
            System.out.println(result ? "Comment added successfully" : "Failed to add comment");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleAddPost() throws IOException {
        System.out.println("Enter the post content:");
        String content = scanner.nextLine();
        Post post = new Post(content, new User("username", "password"), false); // Adjust as needed
        out.writeObject(post);
        try {
            boolean result = in.readBoolean();
            System.out.println(result ? "Post added successfully" : "Failed to add post");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        loggedIn = false;
        System.out.println("Logged out successfully.");
    }

    private void closeResources() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null) socket.close();
            if (scanner != null) scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1112);
        client.start();
    }
}
