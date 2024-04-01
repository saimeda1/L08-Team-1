# Phase 1 README

## Project Overview
This phase of the Social Media Platform project focuses on implementing user profiles, friendships, and user database functionalities. Below is a detailed description of each class along with instructions on how to compile and run the project.

## Instructions
Clone the repository to your local machine.
Compile the project using your preferred Java compiler.
Run the compiled program to execute the social media platform.

## Comment Class
Represents comments made on posts.
Attributes: Author, Content, Time, Likes, Dislikes.
Provides methods for upvoting and downvoting comments.
Includes a toString method for displaying comment details.

## Friend Class
Extends the User class to represent a user's friend.
Includes attributes to manage friend relationships and friend posts.
Provides methods for upvoting friend posts.
Relationships: Interacts with the User class for adding and removing friends.

## FriendshipsTest Class
Contains JUnit test cases for the Friendships class.
Tests addFriend, removeFriend, and getFriends methods.
Ensures proper functionality and error handling of friendship management operations.
IUserManager Interface
Defines methods for user management operations.
Includes methods for creating, reading, updating, and deleting users.
Represents the interface for user-related functionalities.

## Post Class
Represents individual posts made by users.
Attributes: Content, Author, Time, Comments, Likes, Dislikes.
Provides methods for managing comments and reactions to posts.
Relationships: Interacts with the User class for adding posts.

## User Class
Represents user profiles in the social media platform.
Attributes: Username, Password, Friends, Posts, Comments.
Provides methods for managing friends, posts, comments, and reactions.
Relationships: Interacts with the Friend class for friend-related functionalities.

## UserDatabase Class
Stores authenticated users in an ArrayList.
Provides methods for user sign-up, login, and searching users.
Ensures user authentication and data management.
Relationships: Interacts with the User class for user management operations.

## UserDatabaseTest Class
Contains JUnit test cases for the UserDatabase class.
Tests signUp, logIn, and deleteUser methods.
Ensures proper functionality and error handling of user management operations.

## Coding Style
Follows standard Java coding conventions.
Includes meaningful variable names and descriptive comments.
Ensures proper indentation and code readability.





