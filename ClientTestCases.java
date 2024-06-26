import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import java.util.Random;

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
        String input = "register\nusernametest13\npasswordtest13\nexit\n";
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
        String username = "usernametest13";
        String password = "passwordtest13";
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

    @Test
    public void testAddPostCommandHandling() {
        String username = "usernametest13";
        String password = "passwordtest13";
        String postContent = "Here is a new post content!";
        String input = "login\n" + username + "\n" + password + "\naddpost\n" + postContent + "\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String expected = "Enter command (login, register, exit):\n" +
                "Enter username:\n" +
                "Enter password:\n" +
                "Login successful. Welcome, " + username + "!\n" +
                "Enter command (addcomment, addpost, logout, exit):\n" +
                "Enter the post content:\n" +
                "Enter command (addcomment, addpost, logout, exit):\n";

        Client.main(new String[0]);

        String actualOutput = outContent.toString();

        actualOutput = actualOutput.replace("\r\n", "\n").trim();
        expected = expected.replace("\r\n", "\n").trim();

        assertEquals(expected, actualOutput, "Output does not match expected output for addpost command");
    }

    @Test
    public void testAddCommentCommandHandling() {
        String username = "usernametest13";
        String password = "passwordtest13";
        int postId = 10000;  // post ID 1 is always valid because at least one comment was added before
        String commentContent = "This is a test comment!";
        String input = "login\n" + username + "\n" + password + "\naddcomment\n" + postId + "\n" + commentContent + "\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String expected = "Enter command (login, register, exit):\n" +
                "Enter username:\n" +
                "Enter password:\n" +
                "Login successful. Welcome, " + username + "!\n" +
                "Enter command (addcomment, addpost, logout, exit):\n" +
                "Enter post ID to comment on:\n" +
                "Enter your comment:\n" +
                "Action completed successfully.\n" +
                "Enter command (addcomment, addpost, logout, exit):\n";

        Client.main(new String[0]);

        String actualOutput = outContent.toString();

        actualOutput = actualOutput.replace("\r\n", "\n").trim();
        expected = expected.replace("\r\n", "\n").trim();

        assertEquals(expected, actualOutput, "Output does not match expected output for addcomment command");
    }
}
