import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.15
 * The ServerTestCases class contains JUnit test cases for
 * testing the Server class.
 * This is for phase 2.
 */

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