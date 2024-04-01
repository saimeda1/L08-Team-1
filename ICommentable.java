/**
 * @author Chenjun Zhou, Xinan Qin, Sai Meda, Bianca Olea
 * @version Apr.1
 * This is for group project phrase 1
 */
public interface ICommentable {
    int getId();
    int getLikes();
    int getDislikes();
    void setLikes(int likes);
    void setDislikes(int dislikes);
    void upVote();
    void downVote();
    String toString();
}
