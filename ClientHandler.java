import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private static final Logger LOGGER = Logger.getLogger(ClientHandler.class.getName());

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            input = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error setting up streams", e);
        }
    }

    @Override
    public void run() {
        try {
            Object request;
            while (!clientSocket.isClosed() && (request = input.readObject()) != null) {
                // Interpret the request based on your protocol
                if (request instanceof String) {
                    String command = (String) request;
                    processCommand(command);
                } else {
                    LOGGER.log(Level.WARNING, "Received unknown request type");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during communication", e);
        } finally {
            cleanUp();
        }
    }

    private void processCommand(String command) {
        // Add command handling logic here
        switch (command.toLowerCase()) {
            case "hello":
                sendResponse("Hello from server!");
                break;
            // More commands here
            default:
                LOGGER.log(Level.WARNING, "Unknown command received: {0}", command);
        }
    }

    private void sendResponse(Object response) {
        try {
            output.writeObject(response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error sending response", e);
        }
    }

    private void cleanUp() {
        try {
            if (input != null) input.close();
            if (output != null) output.close();
            if (clientSocket != null) clientSocket.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error cleaning up resources", e);
        }
    }
}
