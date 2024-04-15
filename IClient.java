import java.io.IOException;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.15
 * The IClient interface represents the contract for a client application.
 * It defines methods related to starting the client, processing commands,
 * and handling user interactions.
 * This is for phase 2.
 */

public interface IClient {
    void start();
    void processCommand(String command) throws IOException;
    void handleLogin() throws IOException;
    void handleRegister() throws IOException;
    void handleAddComment() throws IOException;

}

