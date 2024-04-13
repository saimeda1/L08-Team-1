# Phase 1 README


## Running Instructions
Open a terminal or command prompt, navigate to the project folder, and compile the Java files. The general command to compile all Java files in the directory is:

javac *.java

This will compile all .java files in the directory, resolving dependencies among them, and generate .class files for each.

Running Test Cases: To run the test cases, you would use the Java command followed by the name of the test case class (without the .java extension). For example, to run UserTestCases, you would use:

java UserTestCases

A main method will be implemented alongside a GUI in future phases for easier usability. 

## Project Overview
This phase of the Social Media Platform project focuses on implementing user profiles, friendships, and user database functionalities. Below is a detailed description of each class along with instructions on how to compile and run the project.

## Instructions
Clone the repository to your local machine.
Compile the project using your preferred Java compiler.
Run the compiled program to execute the social media platform.

# Comment Class

The `Comment` class represents a comment on a post in a social media system. It contains information about the comment's content, author, creation time, likes, and dislikes.

## Constructors

### Comment(String content, User author)

- **Parameters:** 
    - `content`: The content of the comment.
    - `author`: The user who posted the comment.

## Fields

- `private static int nextId`: Represents the ID counter for generating unique comment IDs.
- `private int id`: Represents the unique ID of the comment.
- `private User author`: Represents the user who posted the comment.
- `private String content`: Represents the content of the comment.
- `private LocalDateTime time`: Represents the creation time of the comment.
- `private int likes`: Represents the number of likes the comment has received.
- `private int dislikes`: Represents the number of dislikes the comment has received.

## Methods

### getId(): int

- **Returns:** The ID of the comment.

### getLikes(): int

- **Returns:** The number of likes the comment has received.

### getDislikes(): int

- **Returns:** The number of dislikes the comment has received.

### setLikes(int likes): void

- **Parameters:** 
    - `likes`: The new number of likes for the comment.

### setDislikes(int dislikes): void

- **Parameters:** 
    - `dislikes`: The new number of dislikes for the comment.

### upVote(): void

- Increases the number of likes for the comment by one.

### downVote(): void

- Increases the number of dislikes for the comment by one.

### toString(): String

- **Returns:** A string representation of the comment, including its ID, content, author, creation time, likes, and dislikes.


# Friend Class

The `Friend` class represents a user's friend in a social media system. It extends the `User` class and adds functionality specific to friends, such as the ability to block, view friend posts, and interact with them.

## Constructors

### Friend(String username, String password, boolean block)

- **Parameters:** 
    - `username`: The username of the friend.
    - `password`: The password of the friend.
    - `block`: Indicates whether the friend is blocked or not.

## Fields

- `private boolean block`: Indicates whether the friend is blocked or not.
- `ArrayList<Post> friendPost`: Stores the posts made by the friend.

## Methods

### isBlock(): boolean

- **Returns:** `true` if the friend is blocked, `false` otherwise.

### setBlock(boolean block): void

- **Parameters:** 
    - `block`: The new block status of the friend.

### getFriendPost(): ArrayList<Post>

- **Returns:** An ArrayList containing posts made by the friend.

### upVotePost(Post post): boolean

- **Parameters:** 
    - `post`: The post to upvote.
- **Returns:** `true` if the upvote was successful, `false` otherwise.

### downVotePost(Post post): boolean

- **Parameters:** 
    - `post`: The post to downvote.
- **Returns:** `true` if the downvote was successful, `false` otherwise.

### addPost(Post post): void

- **Parameters:** 
    - `post`: The post to add to the friend's posts list.

### Overridden Methods

### addPost(Post post): void

- Adds a post to the friend's posts list.



# FriendshipsTest Class

The `FriendshipsTest` class contains unit tests for the functionality provided by the `Friendships` class. These tests verify the correctness of methods related to managing friendships in a social media system.

## Test Cases

### testAddFriendSuccess()

- **Description:** Tests the successful addition of a friend.
- **Test Steps:**
    1. Adds a friend for user1 and user2.
    2. Checks if user2 is in the friend list of user1.
- **Expected Result:** The friend addition should succeed, and user2 should be present in the friend list of user1.

### testAddFriendFailureAlreadyExists()

- **Description:** Tests the failure to add an already existing friend.
- **Test Steps:**
    1. Adds a friend for user1 and user2.
    2. Attempts to add user2 again as a friend of user1.
- **Expected Result:** The friend addition should fail since user2 is already a friend of user1.

### testAddFriendFailureNonExistentUser()

- **Description:** Tests the failure to add a friend for a non-existent user.
- **Test Steps:**
    1. Attempts to add user3 as a friend of user1, which is not present in the system.
- **Expected Result:** The friend addition should fail because user3 does not exist in the system.

### testRemoveFriendSuccess()

- **Description:** Tests the successful removal of a friend.
- **Test Steps:**
    1. Adds a friend for user1 and user2.
    2. Removes user2 from the friend list of user1.
- **Expected Result:** The friend removal should succeed, and user2 should not be present in the friend list of user1.

### testRemoveFriendFailureNonExistent()

- **Description:** Tests the failure to remove a non-existent friend.
- **Test Steps:**
    1. Attempts to remove user3 from the friend list of user1, which is not present in the system.
- **Expected Result:** The friend removal should fail since user3 is not a friend of user1.

### testGetFriendsListSuccess()

- **Description:** Tests the successful retrieval of the friend list.
- **Test Steps:**
    1. Adds a friend for user1 and user2.
    2. Retrieves the friend list of user1.
- **Expected Result:** The friend list of user1 should contain user2 only, and the size should be 1.

### testGetFriendsListForNonExistentUser()

- **Description:** Tests the retrieval of the friend list for a non-existent user.
- **Test Steps:**
    1. Retrieves the friend list of user3, which is not present in the system.
- **Expected Result:** The friend list should be empty since user3 does not exist in the system.



# Post Class

The `Post` class represents a post in a social media system. It contains information about the post's content, author, creation time, likes, dislikes, and comments.

## Constructors

### Post(String content, User author, boolean hide)

- **Parameters:** 
    - `content`: The content of the post.
    - `author`: The user who created the post.
    - `hide`: Indicates if the post is hidden or not.

## Fields

- `private static int nextId`: Represents the ID counter for generating unique post IDs.
- `private int id`: Represents the unique ID of the post.
- `private String content`: Represents the content of the post.
- `private User author`: Represents the user who created the post.
- `private LocalDateTime time`: Represents the creation time of the post.
- `private ArrayList<Comment> comments`: Stores the list of comments associated with the post.
- `private int likes`: Represents the number of likes the post has received.
- `private int dislikes`: Represents the number of dislikes the post has received.
- `private boolean hide`: Indicates if the post is hidden.

## Methods

### isHide(): boolean

- **Returns:** True if the post is hidden, false otherwise.

### setHide(boolean hide): void

- **Parameters:** 
    - `hide`: The boolean value indicating if the post should be hidden or not.

### getId(): int

- **Returns:** The ID of the post.

### getContent(): String

- **Returns:** The content of the post.

### setContent(String content): void

- **Parameters:** 
    - `content`: The new content of the post.

### getAuthor(): User

- **Returns:** The user who created the post.

### setAuthor(User author): void

- **Parameters:** 
    - `author`: The user who created the post.

### getTime(): LocalDateTime

- **Returns:** The creation time of the post.

### setTime(LocalDateTime time): void

- **Parameters:** 
    - `time`: The new creation time of the post.

### getComments(): ArrayList<Comment>

- **Returns:** The list of comments associated with the post.

### setComments(ArrayList<Comment> comments): void

- **Parameters:** 
    - `comments`: The new list of comments associated with the post.

### getLikes(): int

- **Returns:** The number of likes the post has received.

### setLikes(int likes): void

- **Parameters:** 
    - `likes`: The new number of likes for the post.

### getDislikes(): int

- **Returns:** The number of dislikes the post has received.

### setDislikes(int dislikes): void

- **Parameters:** 
    - `dislikes`: The new number of dislikes for the post.

### upVote(): void

- Increases the number of likes for the post by one.

### downVote(): void

- Increases the number of dislikes for the post by one.

### toString(): String

- **Returns:** A string representation of the post, including its ID, content, author, creation time, likes, dislikes, and comments (if any).


# User Class

The `User` class represents a user in a social media system. It contains information about the user's profile, friends, posts, and comments.

## Constructors

### User(String username, String password)

- **Parameters:** 
    - `username`: The username of the user.
    - `password`: The password of the user.

## Fields

- `private String username`: Represents the username of the user.
- `private String password`: Represents the password of the user.
- `private ArrayList<Friend> friends`: Stores the list of friends of the user.
- `private ArrayList<Post> posts`: Stores the list of posts made by the user.
- `private ArrayList<Comment> comments`: Stores the list of comments made by the user.

## Methods

### getUsername(): String

- **Returns:** The username of the user.

### getPassword(): String

- **Returns:** The password of the user.

### setUsername(String username): void

- **Parameters:** 
    - `username`: The new username to set.

### addFriend(User user): boolean

- **Parameters:** 
    - `user`: The user to add as a friend.
- **Returns:** True if the user was successfully added as a friend, false otherwise.

### removeFriend(User user): boolean

- **Parameters:** 
    - `user`: The user to remove as a friend.
- **Returns:** True if the user was successfully removed as a friend, false otherwise.

### blockFriends(Friend user): boolean

- **Parameters:** 
    - `user`: The user to block.
- **Returns:** True if the user was successfully blocked, false otherwise.

### deletePost(int id): boolean

- **Parameters:** 
    - `id`: The ID of the post to delete.
- **Returns:** True if the post was successfully deleted, false otherwise.

### deleteComment(int id): boolean

- **Parameters:** 
    - `id`: The ID of the comment to delete.
- **Returns:** True if the comment was successfully deleted, false otherwise.

### upVoteComment(Comment comment): boolean

- **Parameters:** 
    - `comment`: The comment to upvote.
- **Returns:** True if the upvote was successful, false otherwise.

### downVoteComment(Comment comment): boolean

- **Parameters:** 
    - `comment`: The comment to downvote.
- **Returns:** True if the downvote was successful, false otherwise.

### addPost(Post post): void

- **Parameters:** 
    - `post`: The post to add.

### displayFriendPost(): void

- Displays posts made by friends.

### addComment(Post post, Comment comment): void

- Adds a comment to a post.



# UserDatabase Class

The `UserDatabase` class manages a database of users and provides functionalities such as user sign-up, login, post retrieval, comment addition, and user search.

## Constructor

### UserDatabase(ArrayList<User> users)

- **Description:** Constructs a `UserDatabase` object with an initial list of users.
- **Parameters:**
    - `users`: An ArrayList of User objects representing the initial set of users in the database.

## Methods

### signUp(User user)

- **Description:** Adds a new user to the database.
- **Parameters:**
    - `user`: The User object to be added.
- **Returns:** True if the user is successfully added, false otherwise.

### getPosts()

- **Description:** Retrieves all posts from the database.
- **Returns:** An ArrayList of Post objects representing all posts in the database.

### setPosts()

- **Description:** Sets the posts ArrayList by fetching posts from all users.
- **Note:** This method populates the posts ArrayList with posts from all users in the database.

### addComment(Comment comment, Post post)

- **Description:** Adds a comment to a specified post.
- **Parameters:**
    - `comment`: The Comment object to be added.
    - `post`: The Post object to which the comment will be added.
- **Returns:** True if the comment is successfully added to the post, false otherwise.

### logIn(User user)

- **Description:** Validates user credentials for login.
- **Parameters:**
    - `user`: The User object containing login credentials.
- **Returns:** True if the user credentials are valid, false otherwise.

### searchUser(String search)

- **Description:** Searches for a user by username.
- **Parameters:**
    - `search`: The username of the user to search for.
- **Returns:** The User object if found, null otherwise.



# UserDatabaseTest Class

The `UserDatabaseTest` class contains test cases to verify the functionality of the `UserDatabase` class.

## Test Cases

### testSignUp()

- **Description:** Tests the signUp() method of the UserDatabase class.
- **Objective:** Verify that a new user can be successfully added to the database.
- **Test Steps:**
    1. Add a new user to the database.
    2. Check if the user is successfully added.
- **Expected Behavior:** The method should return true, indicating successful user addition.

### testGetPosts()

- **Description:** Tests the getPosts() method of the UserDatabase class.
- **Objective:** Verify that the method retrieves all posts from the database.
- **Test Steps:**
    1. Call the getPosts() method to retrieve all posts.
    2. Check if the returned ArrayList contains all posts from the database.
- **Expected Behavior:** The method should return an ArrayList containing all posts in the database.

### testAddComment()

- **Description:** Tests the addComment() method of the UserDatabase class.
- **Objective:** Verify that a comment can be successfully added to a post in the database.
- **Test Steps:**
    1. Choose a post from the database.
    2. Add a new comment to the selected post.
    3. Verify if the comment is successfully added to the post.
- **Expected Behavior:** The method should return true, indicating successful addition of the comment to the post.

### testLogIn()

- **Description:** Tests the logIn() method of the UserDatabase class.
- **Objective:** Verify that user login credentials are validated correctly.
- **Test Steps:**
    1. Provide valid login credentials.
    2. Check if the login is successful.
- **Expected Behavior:** The method should return true, indicating successful login.

### testSearchUser()

- **Description:** Tests the searchUser() method of the UserDatabase class.
- **Objective:** Verify that a user can be successfully searched by username.
- **Test Steps:**
    1. Search for an existing user by username.
    2. Verify if the correct user object is returned.
- **Expected Behavior:** The method should return the User object if found, or null if not found.



## Coding Style
Follows standard Java coding conventions.
Includes meaningful variable names and descriptive comments.
Ensures proper indentation and code readability.

## Who Submitted What
* Sai Meda was the project lead and designed interfaces and created interface testcases.
* Bianca Olea was in charge of documentation and creating the user class and permissions.
* Chen Jun developed methods and worked on application control flow.
* Xinan Qin was responsible for developing database objects and test cases.




