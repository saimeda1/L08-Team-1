
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.ServerSocket;

class ServerTestCases {

    @Test
    void testServerInitialization() {
        Assertions.assertDoesNotThrow(() -> {
            Server server = new Server(1112) {
                @Override
                protected ServerSocket createServerSocket(int port) throws IOException {
                    return null;
                }

                @Override
                protected UserDatabase loadOrCreateDatabase() {
                    return null;
                }
            };
        });
    }
}