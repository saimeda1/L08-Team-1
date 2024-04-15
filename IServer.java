import java.io.IOException;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.15
 * The IServer interface represents the contract for a server application.
 * It defines methods related to loading users and starting the server.
 * This is for phase 2.
 */

public interface IServer {
    void loadUsers() throws IOException, ClassNotFoundException;

    void start() throws IOException;
}
