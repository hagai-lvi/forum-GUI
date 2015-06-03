import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hagai_lvi on 5/24/15.
 */
public class A {
	public static JFrame frame;
	private JPanel panel;
	private JButton button1;
	private JButton[] ForumButtons;
	private JPanel ForumPanel;
	private JPanel loginPanel;
	private JTextField username, password;

	public A() {

		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StringBuffer result = getResponse("http://localhost:8080/forum-system/gui/facade");

				Object obj=JSONValue.parse(result.toString());
				JSONArray arr = (JSONArray)obj;

				int numofForums = arr.size();
				ForumButtons = new JButton[numofForums];

				ForumPanel.setLayout(new BoxLayout(ForumPanel, BoxLayout.LINE_AXIS));
//				frame.setPreferredSize(new Dimension(640, 480));
				for (int i = 0; i < arr.size(); i++) {
					JSONObject a = (JSONObject) arr.get(i);
					String ss = (String)a.values().iterator().next();
					ForumButtons[i] = new JButton(ss);
					int j=i;

					MyMouseAdapter listener = new MyMouseAdapter(ForumButtons[i]);
					listener.setJ(i);

					ForumButtons[i].addMouseListener(listener);
					ForumButtons[i].setVisible(true);
					ForumPanel.add(ForumButtons[i]);
				}
				initLoginPanel();
				ForumPanel.add(loginPanel);

				System.out.println(numofForums);
				ForumPanel.setPreferredSize(new Dimension(100*numofForums + 200,100));// changed it to preferredSize, Thanks!
				frame.setContentPane( ForumPanel);
				frame.pack();
			}
		});
	}

	private void initLoginPanel() {
		loginPanel = new JPanel();
		username = new JTextField(15);
		password = new JPasswordField(15);
		loginPanel.add(username);
		loginPanel.add(password);
	}

	public static void addBtnListener(final JButton btn){
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Forum a = new Forum(btn.getText());
			}
		});
	}

	public static void main(String[] args) {
		frame = new JFrame("A");
		frame.setContentPane(new A().panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

	public static StringBuffer getResponse(String url){
		HttpClient c  = HttpClientBuilder.create().build();
		HttpGet req = new HttpGet(url);
		try {
			HttpResponse response = c.execute(req);
			System.out.println(response.toString());
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			return result;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return new StringBuffer();
	}

}
