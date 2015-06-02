import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hagai_lvi on 5/24/15.
 */
public class A {
	private JPanel panel;
	private JButton button1;
	private JTextField textField1;

	public A() {
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("hello");
				textField1.setText("hi");
				HttpClient c  = HttpClientBuilder.create().build();
				HttpGet req = new HttpGet("http://localhost:8080/forum-system/gui/single");
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
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("A");
		frame.setContentPane(new A().panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
