# Social Media Platform Project Overview

This project encompasses a comprehensive social media platform that allows users to manage profiles, friendships, and interact with posts and comments. It is structured into multiple phases, each contributing uniquely to the functionality and user experience. Below is a detailed guide on running the application, a description of key components, and contributions from each team member.

## Running Instructions

To compile and run the project, follow these steps:

1. **Compile Java Files**:
   Open a terminal or command prompt, navigate to the project directory, and compile all Java files using:
This will generate `.class` files for each `.java` file, resolving all dependencies.
javac *.java

3. **Run Test Cases**:
To execute test cases, use:
Replace `ClassNameTestCases` with the actual test class name, e.g., `UserTestCases`.
java ClassNameTestCases

5. **Start the Server**:
Run the server to handle incoming client requests:
java Server

7. **Launch Client GUI**:
Activate the client-side GUI for user interaction:
java NewClient

### Compilation and Execution:
javac *.java
java Server
java NewClient


## Project Components

### Client-Side Operations
- **Client**: Manages user interface for operations such as login, registration, and posting comments.
- **NewClient**: Extends client functionalities with a GUI interface.

### Server-Side Management
- **Server**: Handles incoming connections and requests, interfacing with the `UserDatabase` for data retrieval and updates.
- **ClientHandler**: Manages communication with individual clients in a threaded manner.

### User and Data Management
- **UserDatabase**: Manages a database of users, facilitating functionalities like user sign-up, login, and post retrieval.
- **User**: Represents user profiles, including methods for adding friends, posts, and handling comments.
- **Post and Comment Classes**: Handle the creation and management of posts and comments on the platform.

### Testing and Validation
- **UserTestCases, ServerTestCases, ClientTestCases**: Contain unit tests to ensure functionality meets expected outcomes.

## Contributions

- **Sai Meda**: Led the project development, designed interfaces, and created test cases. meda@purdue.edu
- **Bianca Olea**: Focused on documentation, developed the User class, and ensured adherence to project specifications. bolea@purdue.edu
- **Chenjun Zhou**: Developed methods, contributed to the application control flow, and assisted in GUI design. zhou1486@purdue.edu
- **Xinan Qin**: Developed database functionalities and contributed significantly to server-side testing. qin206@purdue.edu

## New Features in Phase 3

- **GUI Integration**: Streamlined client operations through a comprehensive GUI.
- **Profile Picture Management**: Users can now upload and retrieve their profile pictures.
- **Enhanced Post Interaction**: Users can upvote, downvote, and hide posts directly from their feed.
- **Comment Interaction**: Added functionalities for users to vote on comments.

## Future Directions

- **Enhanced Security Measures**: Implementing advanced encryption for user data.
- **Real-time Notifications**: Introducing notifications for real-time updates on user interactions.
- **Mobile Compatibility**: Developing a mobile version of the application for broader accessibility.

## Coding Style

The project adheres to standard Java coding conventions including meaningful variable names and comprehensive comments to ensure code readability and maintainability.

## Repository Management

All team members contributed via Git, with a clear commit history that reflects the development process and individual contributions effectively.


