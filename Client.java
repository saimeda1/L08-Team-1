import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Iclient {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Override
    public void connect(String address, int port) {
        try {
            socket = new Socket(address, port);
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            System.out.println("Connected to server at " + address + ":" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Object data) {
        try {
            output.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object receive() {
        try {
            return input.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void disconnect() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
