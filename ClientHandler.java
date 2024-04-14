import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket clientSocket;
    private UserDatabase userDatabase;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(Socket socket, UserDatabase userDatabase) {
        this.clientSocket = socket;
        this.userDatabase = userDatabase;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());

            Object inputObject;
            while ((inputObject = in.readObject()) != null) {
                if (inputObject instanceof String) {
                    processCommand((String) inputObject);
                }
            }
        } catch (EOFException e) {
            System.out.println("Client disconnected.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error handling client: " + e.getMessage());
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void processCommand(String command) throws IOException {
        try {
            switch (command.toLowerCase()) {
                case "login":
                    handleLogin();
                    break;
                case "register":
                    handleRegister();
                    break;
                case "addcomment":
                    handleAddComment();
                    break;
                case "addpost":
                    handleAddPost();  // Make sure this line is added
                    break;
                default:
                    out.writeBoolean(false);
                    out.writeObject("Unknown command.");
                    out.flush();
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            out.writeBoolean(false);
            out.writeObject("Error processing request: " + e.getMessage());
            out.flush();
        }
    }

    private void handleLogin() throws IOException, ClassNotFoundException {
        String username = (String) in.readObject();
        String password = (String) in.readObject();
        User user = userDatabase.validateUser(username, password);
        if (user != null) {
            out.writeBoolean(true);
            out.writeObject(user);  // Ensure the user object is being sent back correctly
        } else {
            out.writeBoolean(false);
            out.writeObject("Invalid credentials or user does not exist.");
        }
        out.flush();
    }

    private void handleRegister() throws IOException {
        try {
            String username = (String) in.readObject();
            String password = (String) in.readObject();
            if (userDatabase.userExists(username)) {
                out.writeBoolean(false);
                out.writeObject("Registration failed: Username already taken.");
            } else {
                User user = new User(username, password);
                boolean isRegistered = userDatabase.signUp(user);
                out.writeBoolean(isRegistered);
                if (isRegistered) {
                    out.writeObject("Registration successful.");
                } else {
                    out.writeObject("Registration failed: Error during registration.");
                }
            }
        } catch (ClassNotFoundException e) {
            out.writeBoolean(false);
            out.writeObject("Error processing request: Class not found.");
        } catch (IOException e) {
            out.writeBoolean(false);
            out.writeObject("Error processing request: IO Exception.");
        } finally {
            out.flush();
        }
    }


    private void handleAddComment() throws IOException, ClassNotFoundException {
        Comment comment = (Comment) in.readObject();
        int postId = (int) in.readObject();
        boolean result = userDatabase.addCommentToPost(comment, postId);
        out.writeBoolean(result);
        out.flush();
    }

    private void handleAddPost() throws IOException, ClassNotFoundException {
        Post post = (Post) in.readObject();  // Directly read the post object
        if (post != null && userDatabase.addPost(post)) {
            out.writeBoolean(true);
        } else {
            out.writeBoolean(false);
            System.out.println("Failed to add post: post object is null or addPost failed");
        }
        out.flush();
    }


    private void closeResources() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
