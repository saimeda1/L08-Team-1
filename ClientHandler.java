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
            in = new ObjectInputStream(clientSocket.getInputStream());
            Object inputObject;

            while ((inputObject = in.readObject()) != null) {
                if (inputObject instanceof String) {
                    processCommand((String) inputObject);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processCommand(String command) throws IOException {
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
            // Additional cases for other functionalities like searching users, etc.
        }
    }

    private void handleLogin() throws IOException {
        try {
            User user = (User) in.readObject();
            boolean isLoggedIn = userDatabase.logIn(user);
            out.writeBoolean(isLoggedIn);
            out.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleRegister() throws IOException {
        try {
            User user = (User) in.readObject();
            boolean isRegistered = userDatabase.signUp(user);
            out.writeBoolean(isRegistered);
            out.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void handleAddComment() throws IOException {
        try {
            Comment comment = (Comment) in.readObject();
            Post post = (Post) in.readObject();
            boolean isCommentAdded = userDatabase.addComment(comment, post);
            out.writeBoolean(isCommentAdded);
            out.flush();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
