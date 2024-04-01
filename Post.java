import java.time.LocalDateTime;
import java.util.ArrayList;

public class Post{
    private int id = 0;
    private String content;
    private User author;
    private LocalDateTime time;
    private ArrayList<Comment> comments = new ArrayList<>();
    private int likes;
    private int dislikes;


    public Post(String content, User author) {
        this.content = content;
        this.time = LocalDateTime.now();
        this.likes = 0;
        this.dislikes = 0;
        this.author = author;
        id++;
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
    public String toString(){
        String result = "";
        for (int i = 0; i < comments.size(); i++){
            result += comments.get(i).toString() + "\n";
        }
        return  "PostID: " + id + "\n" +
                "Post: " + content + "\n" + "by " + author.getUsername() + "\n" + "at " +
                time + "\n" + "likes: " + likes + "\n" + "dislikes: " + dislikes + "\n" + result;
    }
}
