import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Gila on 17/06/2015.
 */

public class AddForum {
    private JPanel mainPanel;
    public JFrame frame;
    private static String url = "http://localhost:8080/forum-system/gui/addForum";
    private JFrame parentFrame;
    private JLabel lblTitle, lblName, lblRegex, lblNumberOfModerators, lblUserName, lblPassword;
    private JTextField txtName, txtRegex, txtNumberOfModerators, txtUserName, txtPassword;
    private JButton BtnBack, btnSubmit;

    public AddForum(JFrame parent){
        this.parentFrame = parent;

        frame = new JFrame("Add new Forum to the Forum System");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        frame.setContentPane(mainPanel);

        BtnBack = new JButton("back to main");
        BtnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.setVisible( true );
                frame.dispose();
            }
        });
        mainPanel.add(leftJustify(BtnBack));

        lblTitle = new JLabel("Please fill the form to create new Forum");
        lblTitle.setFont(new Font("arial",1,30));
        mainPanel.add(leftJustify(lblTitle));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        //Name
        lblName = new JLabel("Please enter the name of the forum");
        mainPanel.add(leftJustify(lblName));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        txtName = new JTextField();
        mainPanel.add(leftJustify(txtName));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        //Regex
        lblRegex = new JLabel("Please enter the regex for password policy of the forum");
        mainPanel.add(leftJustify(lblRegex));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        txtRegex = new JTextField();
        mainPanel.add(leftJustify(txtRegex));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        //Number of moderators
        lblNumberOfModerators = new JLabel("please enter maximum number of moderators");
        mainPanel.add(leftJustify(lblNumberOfModerators));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        txtNumberOfModerators = new JTextField();
        mainPanel.add(leftJustify(txtNumberOfModerators));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        //username
        lblUserName = new JLabel("please enter admin username");
        mainPanel.add(leftJustify(lblUserName));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        txtUserName = new JTextField();
        mainPanel.add(leftJustify(txtUserName));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        //password
        lblPassword = new JLabel("please enter admin password");
        mainPanel.add(leftJustify(lblPassword));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        txtPassword = new JTextField();
        mainPanel.add(leftJustify(txtPassword));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));

        btnSubmit = new JButton("add forum");

        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                List<BasicNameValuePair> userParams = new ArrayList<BasicNameValuePair>(2);
                userParams.add(new BasicNameValuePair("username", txtUserName.getText()));
                userParams.add(new BasicNameValuePair("password", txtPassword.getText()));
                String userJson = Communicator.jsonEncode(userParams);

                List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(4);
                params.add(new BasicNameValuePair("name", txtName.getText()));
                params.add(new BasicNameValuePair("regex", txtRegex.getText()));
                params.add(new BasicNameValuePair("numOfModerators", txtNumberOfModerators.getText()));
                params.add(new BasicNameValuePair("user", userJson));

                String jsonRequest = Communicator.jsonEncode(params);

                StringBuffer response = Communicator.sendBack(url, jsonRequest);
                JOptionPane.showMessageDialog(frame, "forum " + txtName.getText() + " added");

                parentFrame.dispose();
                frame.dispose();
                Main.main(null);
            }
        });

        mainPanel.add(leftJustify(btnSubmit));

        frame.setPreferredSize(new Dimension(590,450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private Component leftJustify(JComponent comp){
        Box  b = Box.createHorizontalBox();
        b.add(Box.createRigidArea(new Dimension(5,0)));
        b.add( comp );
        b.add( Box.createHorizontalGlue() );
        // (Note that you could throw a lot more components
        // and struts and glue in here.)
        return b;
    }
}
