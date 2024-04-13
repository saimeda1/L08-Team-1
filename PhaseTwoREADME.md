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
