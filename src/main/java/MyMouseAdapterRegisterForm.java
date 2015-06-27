import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Gila on 02/06/2015.
 */
public class MyMouseAdapterRegisterForm extends MouseAdapter {
    int j;
    Main main;
    ArrayList<String> forumNames;

    public MyMouseAdapterRegisterForm(Main main, ArrayList<String> arr){
        this.main = main;
        forumNames = arr;
    }

    public void setJ(int i){
        j=i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        main.frame.setVisible(false);
        Register a = new Register(main.frame, forumNames);
    }
}
