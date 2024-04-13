import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Iserver, Runnable {
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private boolean isRunning = false;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            pool = Executors.newFixedThreadPool(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {
        startServer();
    }

    public void startServer() {
        isRunning = true;
        try {
            while (isRunning) {
                pool.execute(new ClientHandler(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        isRunning = false;
        pool.shutdown();
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8000);
        new Thread(server).start();
    }
}
