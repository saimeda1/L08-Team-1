import java.io.IOException;
public interface IClient {
    void start();
    void processCommand(String command) throws IOException;
    void handleLogin() throws IOException;
    void handleRegister() throws IOException;
    void handleAddComment() throws IOException;

}

