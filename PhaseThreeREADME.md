# Phase 3 README

## Running Instructions
1. Open a terminal or command prompt and navigate to the project folder. 
2. Compile the Javafiles using your preferred Java compielr. The general command to compile all the java files in the directory is:

javac *.java

3. Start the server by running the `Server` class. You can do this by using the Java command followed by the name of the server class: 

java Server

4. Once the server is running, you can activate the GUI by running the NewClient class (not the Client class). This will launch the client-side GUI:

java NewClient

## Project Overview
This phase of the Social Media Platform project focuses on client GUI implementation. There should be combined functionality of the minimum requirements within the GUI. There was more focus on adding features to the network application of the program as well. 

## Instructions
Clone the repository to your local machine.
Compile the project using your preferred Java compiler.
Run the compiled program to execute the social media platform.

# ClientGUI Class

The `ClientGUI` class manages the user interface for client-side operations. It connects to the server and sends user requests. It also receives responses from the server and updates the user interface accordingly. It handles errors and displays appropriate messages to the user.

## Constructors

### ClientGUI(NewClient client)
- **Parameters:**
    - `client`: The `NewClient` object associated with this GUI.

## Fields

- `private NewClient client`: Represents the client object associated with the GUI.
- `private JTextField usernameField, passwordField, postField`: Text fields for entering username, password, and post content.
- `private JTextArea messageArea`: Area for displaying messages and notifications.
- `private CardLayout cardLayout`: Manages the switching of panels within the GUI.
- `private JPanel cards`: Container for holding different panels within the GUI.
- `private DefaultListModel<String> postListModel`: Model for managing posts displayed in the friend posts panel.

## Methods

### createGUI(): void

- **Description:** Creates the graphical user interface components including login panel, post panel, and friend posts panel.

### displayFriendPosts(String[] posts): void

- **Description:** Updates the friend posts panel with the provided array of posts.

### displayMessage(String message): void

- **Description:** Displays a message in the message area.

### switchToLoginView(): void

- **Description:** Switches the view to the login panel.

### switchToPostView(): void

- **Description:** Switches the view to the post panel.

## Usage

1. Create an instance of the `ClientGUI` class, passing a `NewClient` object as a parameter.
2. The GUI will be displayed with login panel by default.
3. Users can interact with the GUI elements (login fields, buttons) to perform actions such as login, posting, commenting, etc.
4. Messages and notifications will be displayed in the message area.
5. Use methods like `displayFriendPosts` to update the friend posts panel.

## Dependencies
- `javax.swing.*`: Provides GUI components and functionality.
- `java.awt.*`: Provides classes for creating GUI components.
- `java.io.*`: Provides input/output functionality.
- `java.util.*`: Provides utility classes for various operations.
- `NewClient`: Represents the client object associated with the GUI, providing communication with the server.

## New Features

### User Profile Management

- **Profile Picture Upload**: Users can now upload profile pictures, which are stored on the server. The `uploadProfilePicture(byte[] pictureData)` method in the `User` class allows users to set their profile pictures by providing the image data as a byte array.

- **Profile Picture Retrieval**: Users can retrieve their profile pictures from the server using the `getProfilePicture()` method in the `User` class. This method returns the profile picture data as a byte array, allowing clients to display the user's profile picture in the GUI.

### Post Interaction

- **Post Voting**: Users can now upvote or downvote posts. The `upVotePost(Post post)` and `downVotePost(Post post)` methods in the `User` class allow users to interact with posts by voting on them. These methods increment or decrement the post's vote count accordingly.

- **Post Hiding**: Users can hide posts from their feed. The `hidePost(int postId)` and `unhidePost(int postId)` methods in the `User` class enable users to hide or unhide posts based on their preferences. This functionality allows users to customize their feed by hiding posts they are not interested in.

### Comment Interaction

- **Comment Voting**: Users can upvote or downvote comments. The `upVoteComment(Comment comment)` and `downVoteComment(Comment comment)` methods in the `User` class allow users to interact with comments by voting on them. These methods increment or decrement the comment's vote count accordingly.

### Server-Side Functionality

- **Adding Comments**: The server can now handle the addition of comments to posts. The `addComment(Post post, Comment comment)` method in the `User` class allows the server to add comments to posts. This functionality enhances user engagement by enabling discussions within the platform.

## Usage

- To upload a profile picture, call the `uploadProfilePicture(byte[] pictureData)` method with the image data as a byte array.
- To retrieve a profile picture, call the `getProfilePicture()` method, which returns the image data as a byte array.
- To interact with posts, use the `upVotePost(Post post)` and `downVotePost(Post post)` methods to upvote or downvote posts.
- To hide or unhide posts from the feed, use the `hidePost(int postId)` and `unhidePost(int postId)` methods.
- To interact with comments, use the `upVoteComment(Comment comment)` and `downVoteComment(Comment comment)` methods to upvote or downvote comments.
