# Phase 2 README

## Running Instructions
Open a terminal or command prompt, navigate to the project folder, and compile the Java files. The general command to compile all Java files in the directory is:

javac *.java

This will compile all java files in the directory, resolving dependencies among them, and generate class files for each.

Running Test Cases: To run the test cases, you would use the Java command followed by the name of the test case class (without the .java extension). For example, to run ServerTestCases, you would use:

java ServerTestCases

You can start the main method in the Server to set up the server and run the main method in the client to connect to the server. We have implemented serialization so when you connect to the server as a client you will communicate with the server so the server will store all the data. To manually test the code you will can open the terminal after connecting to the server and login,register,addposts, or addcomments. All of these functions are thread safe and will not store data locally.

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

# IClient Interface

The IClient interface defines the contract for client-side functionality in a client-server architecture. It specifies the methods that a client class must implement to interact with the server.

## Methods

### start()
- **Description:**  Initiates the client's operation, typically establishing a connection with the server and starting communication.
- **Implementation:** This method is responsible for initializing the client and initiating the communication process with the server.

### processCommand(String command) 

- **Description:** Processes the command received from the user or another source.
- **Parameters:** 
    -`command`: The command to be processed
- **Throws:** `IOException` if an I/O Error occurs during command processing.
- **Implementation:** This method handles the processing of different commands received from the user or other sources. It delegates the execution of specific actions based on the command.

### handleLogin()

- **Description:** Handles the login operation.
- **Throws:** `IOException if an I/O error occurs during the login process.
- **Implementation:** This method is responsible for handling the login process. It typically sends the user's credentials to the server for authentification.

### handleRegister()

- **Description:** Handles the user registration operation
- **Throws:** `IOException` if an I/O error occurs during the registration process.
- **Implementation:** This method handles the user registration process. It sends the necessary information to the server for creating a new user account.

### handleAddComment()

- **Description:** Handles the operation of adding a comment.
- **Throws:** `IOException` if an I/O error occurs during the comment addition process.
- **Implementation:** This method handles the process of adding a comment to a post. It communicates with the server to add the comment to the appropriate post.

## Usage
To implement client functionality in a client-server application, a class needs to implement the IClient interface and provide implementations for all the defined methods. These methods should handle the various aspects of client-server communication, such as initiating connections, processing commands, and interacting with the server for authentication, registration, and data manipulation operations.

# IServer Interface

The `IServer` interface outlines the required functionality for a server component in a client-server architecture. It defines methods that a server class must implement to manage connections, handle client requests, and perform server-side operations.

## Methods

### loadUsers()

- **Description:** Loads user data into the server's user database.
- **Throws:**
    - `IOException`: If an I/O error occurs during the loading process.
    - `ClassNotFoundException`: If the class of a serialized object cannot be found.
- **Implementation:** This method is responsible for loadig user data from a data source, such as a file or a databse into the server's user database. It typicallly read serialized user objects and adds them to the user database.

### start()

- **Description:** Starts the server, enabling it to accept client connections and handle requests.
- **Throws:** `IOException` if an I/O error occurs during the server startup process.
- **Implementation:** This method initiates the server's operation, allowing it to listen for incoming client connections on a specified port. Once started, the server can accept client connections and handle client requests.

## Usage

To create a server component in a client-server application, a class needs to implement the `IServer` interface and provide implementations for the `loadUsers()` and `start()` methods. These methods should handle the initialization of the server, loading user data, and starting the server to accept client connections. Additionally, the class may include additional methods and logic for managing client connections, processing client requests, and performing server-side operations.

# ServerTestCases

## Description

The `ServerTestCases` class contains a single test method
`testServerInitialization()` that verifies the initialization of a server object.

## Dependencies
- JUnit 5: The test cases are written using JUnit 5 framework.

## Getting Started
To run the tests, you need to have JUnit5 installed in your development environment. You can add it as a dependency in your build configuration file (e.g. `pom.sml` for Maven or `build.gradle` for Gradle).

## Prequisites
- Java Development Kit (JDK) installed on your machine.
- Development environment set up with build tools (e.g., Maven, Gradle).

## Usage

1. Navigate to the `ServerTestCases` class.
2. Run the `testServerInitialization()` method.
3. View the test results in your IDE's test runner.

# ClientTestCases

This repository contains JUnit test cases for testing the functionality of a client application.

## Description

The `ClientTestCases` class contains several test methods that verify the behavior of the client application under different scenarios, including command handling for registration, login, and exit.

## Dependencies

- JUnit 5: The test cases are written using JUnit 5 framework.

## Getting Started

To run the tests, you need to have JUnit 5 installed in your development environment. You can add it as a dependency in your build configuration file (e.g., `pom.xml` for Maven or `build.gradle` for Gradle).

## Prerequisites

- Java Development Kit (JDK) installed on your machine.
- Development environment set up with build tools (e.g., Maven, Gradle).

## Usage

1. Navigate to the `ClientTestCases` class.
2. Run each test method individuallly or run all tests together.
3. View the test results in your IDE's test runner.
4. When you run the client test, modify the input username and password in both register and login section to mak them identical otherwise the login will fail.

## Test Cases
1. testRegisterCommandHandling()
This test verifies the functionality of the registration command handling in the client application. It simulates user input for registration and checks if the expected output matches the actual output.

2. testLoginCommandHandling()
This test verifies the functionality of the login command handling in the client application. It simulates user input for login and checks if the expected output matches the actual output.

3. testExitCommandHandling()
This test verifies the functionality of the exit command handling in the client application. It simulates user input to exit the application and checks if the expected output matches the actual output.

4. testAddPostCommandHandling()
This tests adds post command handling functionality. It verifies that the client correctly adds a post when provided with valid input.

5. testAddCommentCommandHandling()
This tests the add comment command handling functionality. It verifies that the client correctly adds a comment to a post when provided with a valid input.

# Who Submitted What
- Bianca Olea worked on checking the code and documentation for the various files and READMe
- Chenjun Zhou and Xinan Qin designed the test cases class to test the client and server.
