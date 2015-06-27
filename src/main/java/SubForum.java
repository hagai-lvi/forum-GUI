import org.json.simple.JSONArray;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Gila on 06/06/2015.
 */
public class SubForum {
    private JFrame parentFrame;
    private JFrame frame;
    private static String url = "http://localhost:8080/forum-system/gui/subforum/";
    private JPanel mainPanel;
    private JLabel ForumName, messageBody;
    private JButton backBtn;
    private JTree tree1;
    private JPanel panel1;

    public SubForum(JFrame parent, String title){
        parentFrame = parent;
        frame = new JFrame();
        frame.setVisible(true);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        backBtn = new JButton("back to main");
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parentFrame.setVisible( true );
                frame.dispose();
            }
        });
        mainPanel.add(leftJustify(backBtn));

        //title
        ForumName = new JLabel("Welcome to Subforum " + title);
        ForumName.setFont(new Font("arial",1,30));

        mainPanel.add(leftJustify(ForumName));
        mainPanel.add(Box.createRigidArea(new Dimension(0,5)));


        //send get request
        StringBuffer result = Communicator.getResponse(url + title);
        JSONArray arr = Communicator.parseJSON(result);

        DefaultMutableTreeNode top = new DefaultMutableTreeNode();

//        for (int i=0;i<arr.size();i++){
//
//        }

        top.add(new DefaultMutableTreeNode("msg1"));
        top.add(new DefaultMutableTreeNode("msg2"));
        DefaultMutableTreeNode msg3 = new DefaultMutableTreeNode("msg3");
        msg3.add(new DefaultMutableTreeNode("rep1"));
        msg3.add(new DefaultMutableTreeNode("rep2"));
        top.add(msg3);

        tree1 = new JTree(top);
        tree1.setBounds(50,50,200,200);
        tree1.setPreferredSize(new Dimension(300,300));
//        tree1.set
        tree1.setSize(150,150);
        mainPanel.setPreferredSize(new Dimension(500, 500));
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane scrollPane = new JScrollPane(tree1,v, h);

        tree1.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        tree1.getLastSelectedPathComponent();
                if (node == null) return;
                messageBody.setText(node.toString());
            }
        });
        messageBody = new JLabel("");
        mainPanel.add(scrollPane);
        mainPanel.add(messageBody);
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