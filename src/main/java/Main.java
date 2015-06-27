import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by hagai_lvi on 5/24/15.
 */
public class Main {

	public static JFrame frame;
	private JPanel panel;
	private JButton button1;
	public JButton[] ForumButtons;
	private JButton addForum, registerBtn;
	private JPanel ForumsPanel;
	private JPanel loginPanel;
	private JLabel title1, title2;
	public JTextField username, password;
	private static String getUrl = "http://localhost:8080/forum-system/gui/facade";
//	private static String getUrl = "http://www.mocky.io/v2/5571acf9014a52c319ef7ce7";

	public Main() {

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//login panel
				panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
				initLoginPanel();
				panel.add(loginPanel);

				StringBuffer result = Communicator.getResponse(getUrl);
				ArrayList<String> arr = Communicator.parseJSON(result);
				int numofForums = arr.size();
				ForumButtons = new JButton[numofForums];

				ForumsPanel = new JPanel();
				ForumsPanel.setLayout(new BoxLayout(ForumsPanel, BoxLayout.LINE_AXIS));
				ForumsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

				JLabel lblDes = new JLabel("  click on the forum you want to login to:");
				lblDes.setFont(new Font("arial",1,15));
				panel.add(leftJustify(lblDes));
				panel.add(Box.createRigidArea(new Dimension(0,5)));

				ForumsPanel.add(Box.createRigidArea(new Dimension(5,0)));
				for (int i = 0; i < arr.size(); i++) {
					String ss = arr.get(i);
					ForumButtons[i] = new JButton(ss);
					int j=i;

					MyMouseAdapterGetForum listener = new MyMouseAdapterGetForum(Main.this);
					listener.setJ(i);

					ForumButtons[i].addMouseListener(listener);
					ForumButtons[i].setVisible(true);
					ForumsPanel.add(ForumButtons[i]);
					ForumsPanel.add(Box.createRigidArea(new Dimension(5,0)));
				}

				panel.add(leftJustify(ForumsPanel));
				panel.add(Box.createRigidArea(new Dimension(0,5)));


				JPanel otherOptionPanel = new JPanel();
				otherOptionPanel.setLayout(new BoxLayout(otherOptionPanel, BoxLayout.LINE_AXIS));

				JLabel lblOtherOp = new JLabel("  more options:");
				lblOtherOp.setFont(new Font("arial",1,15));
				panel.add(leftJustify(lblOtherOp));
				panel.add(Box.createRigidArea(new Dimension(0,5)));

				otherOptionPanel.add(Box.createRigidArea(new Dimension(5,0)));
				addForum = new JButton("Add Forum");
				addForum.addMouseListener(new MyMouseAdapterAddForum(Main.this));
				otherOptionPanel.add(addForum);
				otherOptionPanel.add(Box.createRigidArea(new Dimension(5,0)));

				registerBtn = new JButton("register to forum");
				registerBtn.addMouseListener(new MyMouseAdapterRegisterForm(Main.this,arr));
				otherOptionPanel.add(registerBtn);

				panel.add(leftJustify(otherOptionPanel));
				panel.add(Box.createRigidArea(new Dimension(0,5)));

				frame.setPreferredSize(new Dimension(500,330));
				frame.setContentPane( panel);
				frame.pack();

				button1.setVisible(false);
			}
		});
	}

	private void initLoginPanel() {
		JPanel usernameP = new JPanel();
		JLabel usernameL = new JLabel("username: ");
		JPanel passwordP = new JPanel();
		JLabel passwordL = new JLabel("password: ");


		loginPanel = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
		username = new JTextField();
		password = new JPasswordField();
		title1 = new JLabel("Welcome to our forum system");
		title1.setFont(new Font("arial",1,30));
		title2 = new JLabel("please enter username and password and click on the right forum");
		title2.setFont(new Font("arial",1,15));
		loginPanel.add(title1);
		loginPanel.add(title2);

//		usernameP.setLayout(new BoxLayout(usernameP, BoxLayout.LINE_AXIS));
		loginPanel.add(usernameL);
		loginPanel.add(username);

//		passwordP.setLayout(new BoxLayout(passwordP, BoxLayout.LINE_AXIS));
		loginPanel.add(passwordL);
		loginPanel.add(password);

		loginPanel.add(usernameP);
		loginPanel.add(passwordP);

	}

	public static void main(String[] args) {
		frame = new JFrame("Main");
		frame.setContentPane(new Main().panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	private Component leftJustify(JComponent comp){
		Box  b = Box.createHorizontalBox();
		b.add( comp );
		b.add( Box.createHorizontalGlue() );
		// (Note that you could throw a lot more components
		// and struts and glue in here.)
		return b;
	}
}
