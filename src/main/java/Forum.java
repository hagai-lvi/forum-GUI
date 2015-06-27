import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * Created by Gila on 02/06/2015.
 */
public class Forum {
    public JButton[] subForumButtons;
    private JLabel ForumName;
    private JPanel mainPanel, subforumPanels;
    private JButton button1, backBtn;
    private JFrame parentFrame;
    public JFrame frame;


    public Forum(String title, JFrame parent, JSONArray arr) {
        this.parentFrame = parent;

        frame = new JFrame(title);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        backBtn = new JButton("back to main");
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.setVisible( true );
                frame.dispose();
            }
        });
        mainPanel.add(leftJustify(backBtn));

        frame.setContentPane(mainPanel);

        ForumName = new JLabel("Welcome to Forum "+title);
        ForumName.setFont(new Font("arial",1,30));
        mainPanel.add(leftJustify(ForumName));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        int numofForums = arr.size();
        subForumButtons = new JButton[numofForums];
        subforumPanels = new JPanel();


//				frame.setPreferredSize(new Dimension(640, 480));
        subforumPanels = new JPanel();
        subforumPanels.setLayout(new BoxLayout(subforumPanels, BoxLayout.LINE_AXIS));
//        subforumPanels.add(Box.createRigidArea(new Dimension(5,0)));
        for (int i = 0; i < arr.size(); i++) {
            JSONObject a = (JSONObject) arr.get(i);
            String ss = a.values().iterator().next().toString();
            subForumButtons[i] = new JButton(ss);
            subForumButtons[i].setVisible(true);
            //add mouse listener for clicking
            MyMouseAdapterGetSubForum listener = new MyMouseAdapterGetSubForum(Forum.this);
            listener.setJ(i);
            subForumButtons[i].addMouseListener(listener);
            subforumPanels.add(subForumButtons[i]);
            subforumPanels.add(Box.createRigidArea(new Dimension(5,0)));
        }
        mainPanel.add(leftJustify(subforumPanels));
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
