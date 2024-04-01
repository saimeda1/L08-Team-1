import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class FriendshipsTest {
    private Friendships friendships;
    private User user1, user2, user3;

    @Before
    public void setUp() {
        user1 = new User(1, "user1", "password1");
        user2 = new User(2, "user2", "password2");
        user3 = new User(3, "user3", "password3");
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        // Note: user3 is intentionally not added to simulate a non-existent user
        friendships = new Friendships(users);
    }

    @Test
    public void testAddFriendSuccess() {
        assertTrue(friendships.addFriend(user1.getId(), user2.getId()));
        assertTrue(friendships.getFriends(user1.getId()).contains(user2.getId()));
    }

    @Test
    public void testAddFriendFailureAlreadyExists() {
        friendships.addFriend(user1.getId(), user2.getId());
        assertFalse(friendships.addFriend(user1.getId(), user2.getId()));
    }

    @Test
    public void testAddFriendFailureNonExistentUser() {
        assertFalse(friendships.addFriend(user1.getId(), user3.getId()));
    }

    @Test
    public void testRemoveFriendSuccess() {
        friendships.addFriend(user1.getId(), user2.getId());
        assertTrue(friendships.removeFriend(user1.getId(), user2.getId()));
        assertFalse(friendships.getFriends(user1.getId()).contains(user2.getId()));
    }

    @Test
    public void testRemoveFriendFailureNonExistent() {
        assertFalse(friendships.removeFriend(user1.getId(), user3.getId()));
    }

    @Test
    public void testGetFriendsListSuccess() {
        friendships.addFriend(user1.getId(), user2.getId());
        ArrayList<Integer> friendsList = friendships.getFriends(user1.getId());
        assertTrue(friendsList.contains(user2.getId()));
        assertEquals(1, friendsList.size());
    }

    @Test
    public void testGetFriendsListForNonExistentUser() {
        ArrayList<Integer> friendsList = friendships.getFriends(user3.getId());
        assertTrue(friendsList.isEmpty());
    }
}

