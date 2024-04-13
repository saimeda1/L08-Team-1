public interface Iclient {
    void connect(String address, int port);
    void send(Object data);
    Object receive();
    void disconnect();
}

