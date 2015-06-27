import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gila on 02/06/2015.
 */
public class MyMouseAdapterAddForum extends MouseAdapter {
    int j;
    Main main;

    public MyMouseAdapterAddForum(Main main){
        this.main = main;
    }

    public void setJ(int i){
        j=i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        main.frame.setVisible(false);
        AddForum a = new AddForum(main.frame);
    }
}
