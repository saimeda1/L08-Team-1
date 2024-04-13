import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public class Post implements IPost, Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;
    private int id;
    private String content;
    private User author;
    private LocalDateTime time;
    private ArrayList<Comment> comments = new ArrayList<>();
    private int likes;
    private int dislikes;
    private boolean hide;


    public Post(String content, User author, boolean hide) {
        this.id = nextId++;
        this.content = content;
        this.time = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
        this.author = author;
        this.hide = hide;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public int getId() {
        return id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
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

    public String toString() {
        String result = "";
        for (int i = 0; i < comments.size(); i++) {
            result += comments.get(i).toString() + "\n";
        }
        if (hide) {
            return "This post has been hidden\n";
        }
        return "PostID: " + id + "\n" +
                "Post: " + content + "\n" + "by " + author.getUsername() + "\n" + "at " +
                time + "\n" + "likes: " + likes + "\n" + "dislikes: " + dislikes + "\n" +
                result;
    }
}
