Phase 1 README

Project Overview

This phase of the Social Media Platform project focuses on implementing the database side, including user profiles and user management functionality. Below is a detailed description of each class along with instructions on how to compile and run the project.

Instructions

Clone the repository to your local machine.
Compile the project using your preferred Java compiler.
Run the compiled program to execute the social media platform.

Submitted Parts

Student 1: Submitted User class and UserDatabase class on Brightspace.
Student 2: Submitted UserDatabaseTest class on Vocareum.

Detailed Description of Each Class

IUserManager Interface

Defines methods for user management operations.
Includes methods for creating, reading, updating, and deleting users.
Represents the interface for user-related functionalities.

User Class

Represents user profiles in the social media platform.
Attributes: ID, Username, Password, List of Posts.
Provides methods for adding posts, getting user's posts, and checking equality with other users.
Relationships: Interacts with the UserDatabase class for adding and deleting users.

UserDatabase Class

Stores authenticated users in an array.
Provides methods for adding and deleting users.
Utilizes ArrayList for dynamic user management.
Relationships: Interacts with the User class for user management operations.

UserDatabaseTest Class
Contains JUnit test cases for the UserDatabase class.
Tests addUser and deleteUser methods for successful and failure scenarios.
Uses setup method to initialize test data before each test case.
Ensures proper functionality and error handling of user management operations.

Coding Style

Follows standard Java coding conventions.
Includes meaningful variable names and descriptive comments.
Ensures proper indentation and code readability.