import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame {
    private NewClient client; // Reference to the Client class
    private JTextField usernameField, postIDField, friendUsernameField, searchUserField;
    private JPasswordField passwordField;
    private JTextArea postArea, commentArea, outputArea;
    private JButton loginButton, registerButton, postButton, commentButton, logoutButton, fetchFriendPostsButton, friendRequestButton, searchUserButton;
    private JButton upvotePostButton, downvotePostButton, upvoteCommentButton, downvoteCommentButton;
    private JCheckBox isBlockCheckBox;

    public ClientGUI(NewClient client) {
        this.client = client;
        this.client.setGUI(this);
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Client Application");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // User interaction panel
        JPanel northPanel = new JPanel();
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        logoutButton = new JButton("Logout");
        searchUserField = new JTextField(10);
        searchUserButton = new JButton("Search User");
        northPanel.add(new JLabel("Username:"));
        northPanel.add(usernameField);
        northPanel.add(new JLabel("Password:"));
        northPanel.add(passwordField);
        northPanel.add(loginButton);
        northPanel.add(registerButton);
        northPanel.add(logoutButton);
        northPanel.add(new JLabel("Search User:"));
        northPanel.add(searchUserField);
        northPanel.add(searchUserButton);

        // Content interaction panel
        JPanel centerPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        postArea = new JTextArea(5, 20);
        postArea.setBorder(BorderFactory.createTitledBorder("Post Content"));
        commentArea = new JTextArea(3, 20);
        commentArea.setBorder(BorderFactory.createTitledBorder("Add Comment"));
        postButton = new JButton("Add Post");
        commentButton = new JButton("Add Comment");
        postIDField = new JTextField(5);
        postIDField.setBorder(BorderFactory.createTitledBorder("Post/Comment ID"));
        friendUsernameField = new JTextField(10);
        friendUsernameField.setBorder(BorderFactory.createTitledBorder("Friend's Username"));
        isBlockCheckBox = new JCheckBox("Block");
        fetchFriendPostsButton = new JButton("Fetch Friends' Posts");
        friendRequestButton = new JButton("Send Friend Request");
        upvotePostButton = new JButton("Upvote Post");
        downvotePostButton = new JButton("Downvote Post");
        upvoteCommentButton = new JButton("Upvote Comment");
        downvoteCommentButton = new JButton("Downvote Comment");

        centerPanel.add(new JScrollPane(postArea));
        centerPanel.add(postButton);
        centerPanel.add(new JScrollPane(commentArea));
        centerPanel.add(commentButton);
        centerPanel.add(postIDField);
        centerPanel.add(friendUsernameField);
        centerPanel.add(isBlockCheckBox);
        centerPanel.add(fetchFriendPostsButton);
        centerPanel.add(friendRequestButton);
        centerPanel.add(upvotePostButton);
        centerPanel.add(downvotePostButton);
        centerPanel.add(upvoteCommentButton);
        centerPanel.add(downvoteCommentButton);

        // Output area
        outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output Log"));

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setupActions();
    }

    private void setupActions() {
        loginButton.addActionListener(e -> client.handleLogin(usernameField.getText(), new String(passwordField.getPassword())));
        registerButton.addActionListener(e -> client.handleRegister(usernameField.getText(), new String(passwordField.getPassword())));
        postButton.addActionListener(e -> client.handleAddPost(postArea.getText()));
        commentButton.addActionListener(e -> {
            String postIdInput = postIDField.getText();
            if (isValidInteger(postIdInput)) {
                int postId = Integer.parseInt(postIdInput);  // Safe to parse now
                client.handleAddComment(commentArea.getText(), postId);
            } else {
                outputArea.append("Invalid post ID.\n");
            }
        });

        logoutButton.addActionListener(e -> client.handleLogout());
        friendRequestButton.addActionListener(e -> {
            client.handleFriendRequest(friendUsernameField.getText(), isBlockCheckBox.isSelected());
        });
        fetchFriendPostsButton.addActionListener(e -> {
            client.handleFetchFriendPosts();
        });
        searchUserButton.addActionListener(e -> {
            client.handleSearchUser(searchUserField.getText());
        });
        upvotePostButton.addActionListener(e -> {
            try {
                int postId = Integer.parseInt(postIDField.getText());
                client.handleUpvotePost(postId);
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid post ID for upvoting.\n");
            }
        });
        downvotePostButton.addActionListener(e -> {
            try {
                int postId = Integer.parseInt(postIDField.getText());
                client.handleDownvotePost(postId);
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid post ID for downvoting.\n");
            }
        });
        upvoteCommentButton.addActionListener(e -> {
            try {
                int commentId = Integer.parseInt(postIDField.getText());
                client.handleUpvoteComment(commentId);
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid comment ID for upvoting.\n");
            }
        });
        downvoteCommentButton.addActionListener(e -> {
            try {
                int commentId = Integer.parseInt(postIDField.getText());
                client.handleDownvoteComment(commentId);
            } catch (NumberFormatException ex) {
                outputArea.append("Invalid comment ID for downvoting.\n");
            }
        });
    }
    private boolean isValidInteger(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> outputArea.append(message + "\n"));
    }
}
