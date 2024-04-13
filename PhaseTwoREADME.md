# Phase 2 README

## Running Instructions
Open a terminal or command prompt, navigate to the project folder, and compile the Java files. The general command to compile all Java files in the directory is:

javac *.java

This will compile all java files in the directory, resolving dependencies among them, and generate class files for each.

Running Test Cases: To run the test cases, you would use the Java command followed by the name of the test case class (without the .java extension). For example, to run ServerTestCases, you would use:

java ServerTestCases

A main method will be implemented alongside a GUI in future phases for easier usability.

## Project Overview
This phase of the Social Media Platform project focuses on implementing multiple clients and starting up servers through extending threads of runnable interfaces. Below is a detailed description of each class along with instructions on how to compile and run the project.

## Instructions
Clone the repository to your local machine.
Compile the project using your preferred Java compiler.
Run the compiled program to execute the social media platform.

# Client Class

The `Client` class manages the user interface for client-side operations. It connects to the server and sends user requests. It also receives responses from the server and updates the user interface accordingly. It handles errors and displays appropriate messages to the user.

## Constructors

### Client(String host, int port)
- **Parameters:**
    -`host`: The host address of the server.
    -`port`: The port number of the server.

## Fields

- `private Socket socket`: Represents the socket for communicating with the server
- `private ObjectOutputStream out`: ObjectOutputStream for writing objects to the server.
- `private ObjectInputStream in`: ObjectInputStream for writing objects from the server.
- `private Scanner scanner`: Scanner object for reading user input from the console.

## Methods

### start(): void

- **Description:** Starts the client and enters a loop to accept user input and send commands to the server
- **Operation:** 
    - Prompts the user to enter a command (login, register, addcomment).
    - Processes the entered command
    -Continues until the user enters "exit"

### handleLogin() throws IOException: void

- **Description:** Handles the login process by prompting the user to enter their username and password, sending the credentials to the server, and displaying the login result.
- **Operation:** 
    - Prompts the user to enter their username and password.
    - Creates a User object with the provided credentials.
    - Sends the User object to the server.
    - Displays the login result received from the server.

### handleRegister() throws IOException: void

- **Description:** Handles the registration process by prompting the user to enter a new username and password, sending the registration data to the server, and displaying the registration result.
- **Operation:** 
    - Prompts the user to enter their username and password.
    - Creates a User object with the provided credentials.
    - Sends the User object to the server.
    - Displays the registration result received from the server.

### handleAddComment() throws IOException: void

- **Description:** Handles the process of adding a comment to a post by prompting the user to enter the post ID and comment content, sending the comment data to the server, and displaying the result.
- **Operation:** 
    - Prompts the user to enter the ID of the post they want to comment on and the content of the comment.
    - Creates a `Post` object with example content (for demonstration purposes) and a `Comment` and `Post` objects to the server.
    - Displays the result received from the server.

### main(String[] args): void

- **Description:** Entry point for the client application. Creates a new Client instance and starts the client.

## Usage
1. Create an instance of the `Client` class, providing the host address and port number of the server.
2. Call the `start()` method to begin the client application.
3. Follow the prompts to interact with the server by entering commands.

## Dependencies
- `java.io.*`: Provides input/output functionality.
- `java.net.Socket`: Represents a socket for network communication.
- `java.util.Scanner`: Allows reading user input from the console.

# Server Class

The `Server` class listens for incoming client connections on a specific port. It also creates a ClientHandler for each connection to handle client requests. It interfaces with UserDatase to retrieve or update user data as requested by clients. It also manages server resources and ensures clean shutdown on server stop.

## Constructors

### Server(int port)

- **Parameters:**
    - `port`: The port on which the server will listen for incoming connections.

## Methods

### loadUsers()

- **Description:** Loads user data from a file (if available) or initializes the user database.
- **Implementation:** Reads user objects from a file named "user_data.dat" using ObjectInputStream and adds them to the user database.

### start()

- **Description:**  Starts the server, waiting for incoming client connections.
- **Implementation:** Enters a continuous loop where the server socket accepts incoming client connections. For each connection, it creates a new instance of ClientHandler to handle client communication.

## Main Method

### main(String[] args)

- **Description:** Entry point of the server application.
- **Implementation:** Instatiates a `Server` object with the specified port (1112 by default) and starts the server by invoking the `start()` method.


# ClientHandler Class

This `ClientHandler` implements a thread for handling client communication on the server side in a client-server architecture. It receives requests from clients, processes them, and sends back appropriate responses.

## Constructors

### ClientHandler(Socket socket, UserDatabase userDatabase)

- **Parameters:**
    -`socket`: The socket representing the client connection
    -`userDatabase`: The database containing user information

## Fields

- `private Socket clientSocket`: Represents the socket for communicating with the client.
- `private UserDatabase userDatabase`: Represents the database containing user information
- `private ObjectInputStream in`:  Input stream for reading objects from the client.
- `private ObjectOutputStream out`: Output stream for sending objects to the client.

## Methods

### run()

- **Description:** Overrides the run() method of the Thread class. Handles client requests in a loop until the client closes the connection.
- **Implementation:** Initializes input and output streams, reads objects from the client, and processes them using the processCommand() method.

### processCommand(String command)

- **Description:** Processes the command received from the client and delegates it to the appropriate method.
- **Parameters:** 
    - `command`: The command received from the client.

### handleLogin()

- **Description:** Handles the login request received from the client.
- **Implementation:** Reads a User object from the client, attempts to log in the user using the userDatabase, and sends back the login result to the client.

### handleRegister()

- **Description:** Handles the registraiton request received from the client.
- **Implementation:** Reads a User object from the client, attempts to register the user using the userDatabase, and sends back the registration result to the client.

### handleAddComment()

- **Description:** Handles the request to add a comment received from the client.
- **Implementation:** Reads a Comment object and a Post object from the client, attempts to add the comment to the post using the userDatabase, and sends back the result to the client.

## Additional Functionality

The ClientHandler class can be extended to handle additional functionalities such as searching for users, updating user profiles, posting new content, etc. These functionalities can be implemented by adding more handle methods and corresponding command cases in the processCommand() method.
