PHASE 1 README

Project Overview
This phase of the Social Media Platform project focuses on implementing the database side, including user profiles and
news feed functionality. Below is an outline of the classes
and interfaces to be developed.

Profile Classes

User Class
* Represents user profiles
* Methods for database interaction
* Attributes: Username, Password, ID

Friendship Class
* Manages friendships in-memory
* Handles adding, blocking, and removing friends

UserDatabase Class
* Stores authenticated users
* Methods for adding and removing users

Interfaces

IUser
* Operations for user management(CRUD operations)
* Methods for creating, reading, updating, and deleting users

IUserManager
* Interface for adding and removing users

IFriendship
* Methods for managing friendships
* Includes adding, blocking, and removing friends

News Feed Classes

Post Class
* Represents individual posts made by users
* Attributes: Content, Author's ID, Timestamps
* Methods for CRUD operations on posts in the database

Comment Class
* Manages comments made on posts
* Attributes: Commenter's ID, Post ID, Content, Timestamps
* Methods for CRUD operations on comments in the database

Reaction Class
* Handles reactions to posts and comments (e.g., upvotes, downvotes)
* Attributes: Reaction type, Associated post/comemnt ID, User ID
* Methods for managing reactions in the databse

NewsFeedManager Class
* Aggregates posts to display in a user's news feed
* Potential implementation using threads to vary the feed
* Interfaces for managing post operations, comment-related functionalities, and reactions

To-Do List
* Develop JUnit test cases for UserDatabase and NewsFeed classes
* Thoroughly document the README file
* Consider condensing NewsFeed classes if feasible
* Define interfaces for the NewsFeed classes to ensure modularity and scalability

