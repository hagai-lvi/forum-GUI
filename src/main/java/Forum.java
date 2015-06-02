import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Gila on 02/06/2015.
 */
public class Forum {
    private JButton[] ForumButtons;
    private JLabel ForumName;
    private JPanel panel;
    private JButton button1;
    private JFrame frame;

    public Forum(String title) {
        frame = new JFrame(title);
        panel = new JPanel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StringBuffer result = getResponse("http://localhost:8080/forum-system/gui/forum/" + title);

        Object obj = JSONValue.parse(result.toString());
        JSONArray arr = (JSONArray) obj;

        int numofForums = arr.size();
        ForumButtons = new JButton[numofForums];

        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
//				frame.setPreferredSize(new Dimension(640, 480));
        for (int i = 0; i < arr.size(); i++) {
            JSONObject a = (JSONObject) arr.get(i);
            String ss = (String)a.values().iterator().next();
            ForumButtons[i] = new JButton(ss);
            ForumButtons[i].setVisible(true);
            panel.add(ForumButtons[i]);
        }
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
