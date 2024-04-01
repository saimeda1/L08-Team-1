import java.util.ArrayList;

public class UserDatabase {
    private ArrayList<User> users;

    public UserDatabase(ArrayList<User> users) {
        this.users = users;
    }

    public boolean signUp(User user) {
        for (User u : users) {
            if (u.equals(user)) {
                return false;
            }
        }
        users.add(user);
        return true;
    }
    public boolean logIn(User user) {
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public User searchUser(String search){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUsername().equals(search)){
                return users.get(i);
            }
        }
        return null;
    }


}
