import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phase 1
 */
public interface IPost {
    boolean isHide();
    void setHide(boolean hide);
    int getId();
    String getContent();
    void setContent(String content);
    User getAuthor();
    void setAuthor(User author);
    LocalDateTime getTime();
    void setTime(LocalDateTime time);
    ArrayList<Comment> getComments();
    void setComments(ArrayList<Comment> comments);
    int getLikes();
    void setLikes(int likes);
    int getDislikes();
    void setDislikes(int dislikes);
    void upVote();
    void downVote();
    String toString();
}
