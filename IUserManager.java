import java.util.List;

public interface IUserManager {
    public interface IUser {
        int getId();

        void setId(int id);

        String getUsername();

        String getPassword();

        void setPassword(String password);

        void addPost(String content);

        List<Post> getPosts();

        boolean equals(Object o);
    }
}