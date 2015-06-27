import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gila on 02/06/2015.
 */
public class MyMouseAdapterRegisterSpecificForum extends MouseAdapter {
    String forumName;
    JFrame main;
    ArrayList<String> forumNames;
    String username, password;
    final String url = "http://localhost:8080/forum-system/gui/register/";
    Register reg;

    public MyMouseAdapterRegisterSpecificForum(JFrame main,String forumName, Register reg){
        this.main = main;
        this.forumName=forumName;
        this.reg = reg;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        List<BasicNameValuePair> userParams = new ArrayList<BasicNameValuePair>(2);
        String username = reg.txtUserName.getText();
        String password = reg.txtPassword.getText();
        String email = reg.txtEmail.getText();

        userParams.add(new BasicNameValuePair("username", username));
        userParams.add(new BasicNameValuePair("password", password));
        userParams.add(new BasicNameValuePair("email",email));

        String jsonRequest = Communicator.jsonEncode(userParams);

        StringBuffer response = Communicator.sendBack(url+forumName, jsonRequest);
        reg.frame.dispose();
        main.setVisible(true);
    }
}
