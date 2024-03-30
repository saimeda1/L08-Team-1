package PACKAGE_NAME;public class User {
    private int id; //internal number of user
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {
        this.id = 0;
        this.username = null;
        this.password = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = password;
    }
   public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }
        User check = (User) o;
        return this.id == check.id;
    }
}
