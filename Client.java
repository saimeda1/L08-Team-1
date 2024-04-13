import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements IClient{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Scanner scanner;

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
                System.out.println("Enter command (login, register, addcomment):");
                String command = scanner.nextLine();
                if ("exit".equalsIgnoreCase(command)) {
                    break;
                }
                processCommand(command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void processCommand(String command) throws IOException {
        out.writeObject(command);
        switch (command.toLowerCase()) {
            case "login":
                handleLogin();
                break;
            case "register":

                break;
            case "addcomment":
                handleAddComment();
                break;
        }
    }

    public void handleLogin() throws IOException {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        User user = new User(username, password);
        out.writeObject(user);
        try {
            boolean result = in.readBoolean();
            System.out.println(result ? "Login successful" : "Login failed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleRegister() throws IOException {
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

    public void handleAddComment() throws IOException {
        System.out.println("Enter post ID to comment on:");
        int postId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter your comment:");
        String content = scanner.nextLine();
        // Assuming you have a way to get the User and Post by ID or similar
        Post post = new Post("Example content", new User("exampleUser", "pass"), false);
        post.setId(postId); // Set the post ID to the given ID
        Comment comment = new Comment(content, new User("commentingUser", "pass"));
        out.writeObject(comment);
        out.writeObject(post);
        try {
            boolean result = in.readBoolean();
            System.out.println(result ? "Comment added successfully" : "Failed to add comment");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 1112);
        client.start();
    }
}
