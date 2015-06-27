import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gila on 02/06/2015.
 */
public class MyMouseAdapterGetSubForum extends MouseAdapter {
    int j;
    Forum forum;

    public MyMouseAdapterGetSubForum(Forum forum){
        this.forum = forum;
    }

    public void setJ(int i){
        j=i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        forum.frame.setVisible(false);
        SubForum a = new SubForum(forum.frame, forum.subForumButtons[j].getText());
    }
}
