import java.util.ArrayList;

public class UserDatabase {
    private User[] users;

    public UserDatabase(User[] users) {
        this.users = users;
    }

    public boolean addUser(User user) {
        ArrayList<User> userList = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(user)) {
                return false;
            }
            userList.add(users[i]);
        }
        userList.add(user);
        this.users = userList.toArray(new User[0]);
        return true;
    }

    public boolean deleteUser(User user) {
        ArrayList<User> userList = new ArrayList<>();
        boolean check = true;
        for (int i = 0; i < users.length; i++) {
            if (users[i].equals(user)) {
                check = false;
                continue;
            }
            userList.add(users[i]);
        }
        userList.add(user);
        this.users = userList.toArray(new User[0]);
        return check;
    }
}
