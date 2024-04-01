import java.time.LocalDateTime;

public class Comment {
    private static int nextId = 1;
    private int id;
    private User author;
    private String content;
    private LocalDateTime time;
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

    public void upVote() {
        likes++;
    }

    public void downVote() {
        dislikes++;
    }

    public Comment(String content, User author) {
        this.author = author;
        this.content = content;
        this.time = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
        this.id = nextId++;
    }

    public String toString() {
        return "-------------------------------\n" +
                "Comment: " + id + "\n" + content + "\n" +
                "by " + author.getUsername() + "\n" +
                "at " + time + "\n" +
                "likes: " + likes + "\n" +
                "dislikes: " + dislikes + "\n";
    }
}
