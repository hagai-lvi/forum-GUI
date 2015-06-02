import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gila on 02/06/2015.
 */
public class MyMouseAdapter extends MouseAdapter {
    int j;
    JButton btn;

    public MyMouseAdapter(JButton btn){
        this.btn = btn;
    }

    public void setJ(int i){
        j=i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Forum a = new Forum(btn.getText());
    }
}
