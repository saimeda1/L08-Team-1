import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private NewClient client;
    private JTextField usernameField, passwordField, postField;
    private JTextArea messageArea;
    private CardLayout cardLayout;
    private JPanel cards;
    private DefaultListModel<String> postListModel;

    public ClientGUI(NewClient client) {
        this.client = client;
        initializeLookAndFeel();
        createGUI();
    }

    private void initializeLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Unable to set Look and Feel");
        }
    }

    private void createGUI() {
        setTitle("Client Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel postPanel = createPostPanel();
        JPanel friendPostsPanel = createFriendPostsPanel();

        cards.add(loginPanel, "Login");
        cards.add(postPanel, "Post");
        cards.add(friendPostsPanel, "FriendPosts");

        getContentPane().add(cards, BorderLayout.CENTER);

        messageArea = new JTextArea(10, 40);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> client.handleLogin(usernameField.getText(), passwordField.getText()));
        registerButton.addActionListener(e -> client.handleRegister(usernameField.getText(), passwordField.getText()));

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        return panel;
    }

    private JPanel createPostPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        postField = new JTextField(20);
        JTextField commentField = new JTextField(20);
        JTextField postIdField = new JTextField(5);
        JTextField usernameSearchField = new JTextField(20);
        JTextField friendUsernameField = new JTextField(20);
        JTextField deleteCommentIdField = new JTextField(5);
        JTextField deletePostIdField = new JTextField(5);

        JButton addPostButton = new JButton("Add Post");
        JButton addCommentButton = new JButton("Add Comment");
        JButton searchUserButton = new JButton("Search User");
        JButton sendFriendRequestButton = new JButton("Send Friend Request");
        JButton deleteCommentButton = new JButton("Delete Comment");
        JButton deletePostButton = new JButton("Delete Post");
        JButton viewFriendPostsButton = new JButton("View Friend's Posts");

        addPostButton.addActionListener(e -> client.handleAddPost(postField.getText()));
        addCommentButton.addActionListener(e -> {
            try {
                int postId = Integer.parseInt(postIdField.getText());
                client.handleAddComment(commentField.getText(), postId);
            } catch (NumberFormatException ex) {
                displayMessage("Invalid post ID.");
            }
        });
        searchUserButton.addActionListener(e -> client.handleSearchUser(usernameSearchField.getText()));
        sendFriendRequestButton.addActionListener(e -> client.handleFriendRequest(friendUsernameField.getText(), false));
        deleteCommentButton.addActionListener(e -> {
            try {
                int commentId = Integer.parseInt(deleteCommentIdField.getText());
                client.handleDeleteComment(commentId);
            } catch (NumberFormatException ex) {
                displayMessage("Invalid comment ID.");
            }
        });
        deletePostButton.addActionListener(e -> {
            try {
                int postId = Integer.parseInt(deletePostIdField.getText());
                client.handleDeletePost(postId);
            } catch (NumberFormatException ex) {
                displayMessage("Invalid post ID.");
            }
        });
        viewFriendPostsButton.addActionListener(e -> switchToFriendPostsView());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(0, 2, 10, 10));

        inputPanel.add(new JLabel("Post:"));
        inputPanel.add(postField);
        inputPanel.add(addPostButton);
        inputPanel.add(new JLabel("Add Comment to Post ID:"));
        inputPanel.add(postIdField);
        inputPanel.add(commentField);
        inputPanel.add(addCommentButton);
        inputPanel.add(new JLabel("Search User:"));
        inputPanel.add(usernameSearchField);
        inputPanel.add(searchUserButton);
        inputPanel.add(new JLabel("Friend Username:"));
        inputPanel.add(friendUsernameField);
        inputPanel.add(sendFriendRequestButton);
        inputPanel.add(new JLabel("Delete Comment ID:"));
        inputPanel.add(deleteCommentIdField);
        inputPanel.add(deleteCommentButton);
        inputPanel.add(new JLabel("Delete Post ID:"));
        inputPanel.add(deletePostIdField);
        inputPanel.add(deletePostButton);
        inputPanel.add(viewFriendPostsButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        return panel;
    }

    private JPanel createFriendPostsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        postListModel = new DefaultListModel<>();
        JList<String> postList = new JList<>(postListModel);
        JButton refreshPostsButton = new JButton("Refresh Posts");
        refreshPostsButton.addActionListener(e -> client.fetchFriendPosts());

        JScrollPane scrollPane = new JScrollPane(postList);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(refreshPostsButton, BorderLayout.SOUTH);

        return panel;
    }

    public void displayFriendPosts(String[] posts) {
        SwingUtilities.invokeLater(() -> {
            postListModel.clear();
            for (String post : posts) {
                postListModel.addElement(post);
            }
        });
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }

    public void switchToLoginView() {
        cardLayout.show(cards, "Login");
    }

    public void switchToPostView() {
        cardLayout.show(cards, "Post");
    }

    private void switchToFriendPostsView() {
        cardLayout.show(cards, "FriendPosts");
        client.fetchFriendPosts();  // Fetch posts when switching to the view
    }
}
