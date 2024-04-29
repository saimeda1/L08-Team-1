import java.io.*;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class Comment implements ICommentable, Serializable {
    private static final long serialVersionUID = 1L;
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
        //System.out.println("Yes");
    }

    public void downVote() {
        dislikes++;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment(String content, User author) {
        this.author = author;
        this.content = content;
        this.time = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
        Random random = new Random();
        this.id = random.nextInt(10000000);
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
