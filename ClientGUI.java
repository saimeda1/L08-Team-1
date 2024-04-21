import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private NewClient client;
    private JTextField usernameField, passwordField, postField;
    private JTextArea messageArea;
    private CardLayout cardLayout;
    private JPanel cards;  // Container for card layout

    public ClientGUI(NewClient client) {
        this.client = client;
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Handle exception for Look and Feel
        }

        createGUI();
    }

    private void createGUI() {
        setTitle("Client Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel();
        JPanel postPanel = createPostPanel();

        cards.add(loginPanel, "Login");
        cards.add(postPanel, "Post");

        add(cards);
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.SOUTH);
        scrollPane.setPreferredSize(new Dimension(780, 150));

        pack();
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> client.handleLogin(usernameField.getText(), passwordField.getText()));
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(e -> client.handleRegister(usernameField.getText(), passwordField.getText()));
        panel.add(registerButton);

        return panel;
    }

    private JPanel createPostPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        postField = new JTextField(10);
        JButton addPostButton = new JButton("Add Post");
        addPostButton.addActionListener(e -> client.handleAddPost(postField.getText()));
        panel.add(postField, BorderLayout.CENTER);
        panel.add(addPostButton, BorderLayout.SOUTH);

        return panel;
    }

    public void switchToPostView() {
        cardLayout.show(cards, "Post");
    }

    public void switchToLoginView() {
        cardLayout.show(cards, "Login");
    }

    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }
}
