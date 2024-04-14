import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTestCases {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream("".getBytes()));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testRegisterCommandHandling() {
        String input = "register\nusernametest7\npasswordtest7\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String expected = "Enter command (login, register, exit):\n" +
                "Enter username:\n" +
                "Enter password:\n" +
                "Registration successful.\n" +
                "Enter command (login, register, exit):\n";

        Client.main(new String[0]);

        String actualOutput = outContent.toString();

        actualOutput = actualOutput.replace("\r\n", "\n").trim();
        expected = expected.replace("\r\n", "\n").trim();

        assertEquals(expected, actualOutput, "Output does not match expected output for register command");
    }

    @Test
    public void testLoginCommandHandling() {
        String username = "usernametest7";
        String password = "passwordtest7";
        String input = "login\n" + username + "\n" + password + "\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String expected = "Enter command (login, register, exit):\n" +
                "Enter username:\n" +
                "Enter password:\n" +
                "Login successful. Welcome, " + username + "!" + "\n"  +
                "Enter command (addcomment, addpost, logout, exit):\n";

        Client.main(new String[0]);

        String actualOutput = outContent.toString();

        actualOutput = actualOutput.replace("\r\n", "\n").trim();
        expected = expected.replace("\r\n", "\n").trim();

        assertEquals(expected, actualOutput, "Output does not match expected output for login command");
    }

    @Test
    public void testExitCommandHandling() {
        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String expected = "Enter command (login, register, exit):\n";

        Client.main(new String[0]);

        String actualOutput = outContent.toString();

        actualOutput = actualOutput.replace("\r\n", "\n").trim();
        expected = expected.replace("\r\n", "\n").trim();

        assertEquals(expected, actualOutput, "Output does not match expected output for exit command");
    }
}

