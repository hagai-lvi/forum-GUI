import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gila on 02/06/2015.
 */
public class MyMouseAdapterGetForum extends MouseAdapter {
    int j;
    Main main;
    private static String url = "http://localhost:8080/forum-system/gui/forum/";

    public MyMouseAdapterGetForum(Main main){
        this.main = main;
    }

    public void setJ(int i){
        j=i;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(2);
        params.add(new BasicNameValuePair("username", main.username.getText()));
        params.add(new BasicNameValuePair("password", main.password.getText()));

        StringBuffer result = Communicator.sendBack(url + main.ForumButtons[j].getText(), Communicator.jsonEncode(params));
        JSONArray arr = Communicator.parseJSON(result);
        if (arr==null){
            JOptionPane.showMessageDialog(main.frame, "username or password incorrect");
        }else {
            main.frame.setVisible(false);
            Forum a = new Forum(main.ForumButtons[j].getText(), main.frame, arr);
        }
    }
}
