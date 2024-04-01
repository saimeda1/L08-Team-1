import java.time.LocalDateTime;

public class Comment {
    private User author;
    private String content;
    private LocalDateTime time;
    private int id = 0;
    private int likes;
    private int dislikes;

    public int getId() {
        return id;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
        this.time = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
        id++;
    }

    public String toString() {
        return
                "Comment: " + id + ":\n" + content + "\n" +
                        "by " + author.getUsername() + "\n" +
                        "at " + time + "\n" +
                        "likes: " + likes + "\n" +
                        "dislikes: " + dislikes + "\n";
    }
}
