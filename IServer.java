import java.io.IOException;
public interface IServer {
    void loadUsers() throws IOException, ClassNotFoundException;

    void start() throws IOException;
}
