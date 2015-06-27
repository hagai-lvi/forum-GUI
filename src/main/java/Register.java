import org.apache.http.message.BasicNameValuePair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by Gila on 24/06/2015.
 */
public class Register {
    private JFrame parentFrame;
    public JFrame frame;
    private ArrayList<String> forums;
    private JPanel mainPanel;
    final public JTextField txtUserName, txtPassword, txtEmail;

    public Register(JFrame parent, ArrayList<String> arr){
        parentFrame = parent;
        forums = arr;

        frame = new JFrame("Add new Forum to the Forum System");
        mainPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        frame.setContentPane(mainPanel);

        JButton BtnBack = new JButton("back to main");
        BtnBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.setVisible( true );
                frame.dispose();
            }
        });
        mainPanel.add(leftJustify(BtnBack));

        JLabel lblUserName = new JLabel("please enter username");
        mainPanel.add(leftJustify(lblUserName));

        txtUserName = new JTextField();
        mainPanel.add(txtUserName);
        //password
        JLabel lblPassword = new JLabel("please enter password");
        mainPanel.add(leftJustify(lblPassword));

        txtPassword = new JTextField();
        mainPanel.add(txtPassword);

        //email
        JLabel lblEmail = new JLabel("please enter email");
        mainPanel.add(leftJustify(lblEmail));

        txtEmail = new JTextField();
        mainPanel.add(txtEmail);

        JPanel ForumsPanel = new JPanel();
        ForumsPanel.setLayout(new BoxLayout(ForumsPanel, BoxLayout.LINE_AXIS));

        JButton[] ForumButtons = new JButton[forums.size()];
        JLabel lblDes = new JLabel("click on the forum you want to register to:");
        mainPanel.add(leftJustify(lblDes));
        mainPanel.add(Box.createRigidArea(new Dimension(5,0)));

        for (int i = 0; i < arr.size(); i++) {
            String ss = arr.get(i);
            ForumButtons[i] = new JButton(ss);
            int j=i;

            MyMouseAdapterRegisterSpecificForum listener = new MyMouseAdapterRegisterSpecificForum(parentFrame,ss,Register.this);

            ForumButtons[i].addMouseListener(listener);
            ForumButtons[i].setVisible(true);
            ForumsPanel.add(leftJustify(ForumButtons[i]));
        }
        mainPanel.add(leftJustify(ForumsPanel));

        frame.setMinimumSize(new Dimension(400,250));
//        frame.setPreferredSize(new Dimension(400,250));
        frame.setVisible(true);
        frame.pack();
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
