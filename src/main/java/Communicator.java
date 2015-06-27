import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by Gila on 05/06/2015.
 */
public class Communicator {
    public static StringBuffer getResponse(String url){
        url = url.replace(" ", "%20");
        HttpClient c  = HttpClientBuilder.create().build();
        HttpGet req = new HttpGet(url);
        req.setHeader("Accept", "application/json");
        req.setHeader("Content-type", "application/json");
//Set-Cookie: JSESSIONID=3C3914FF0BE1D8FFD1232C89108FE591;
        if (Globals.SessionID!="")
            req.setHeader("Cookie","JSESSIONID=" + Globals.SessionID);

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

    public static StringBuffer sendBack(String url, String jsonString){
        HttpClient c  = HttpClientBuilder.create().build();
        StringBuffer result = new StringBuffer();
        try {
            // Request parameters and other properties.
            HttpPost httpPost = new HttpPost(url);

            httpPost.setEntity(new StringEntity(jsonString));
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            //Execute and get the response.
            HttpResponse response = c.execute(httpPost);
            HttpEntity entity = response.getEntity();

            Pattern pattern = Pattern.compile("JSESSIONID=([^;]*);");
            String cookieHeader = response.getHeaders("Set-Cookie")[0].toString();
            Matcher matcher = pattern.matcher(cookieHeader);

            if (matcher.find()==true) {
                Globals.SessionID = matcher.group(1);
//                if (Globals.SessionID != matcher.group(1) && Globals.SessionID != "") {
//                    System.out.println("switch Session ID");
//                    throw new RuntimeException();
            }
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    BufferedReader rd = new BufferedReader(
                            new InputStreamReader(response.getEntity().getContent()));


                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }
                } finally {
                    instream.close();
                }
            }
        } catch (Exception e){}


        return result;
    }

    public static JSONArray parseJSON(StringBuffer str){
        Object obj=JSONValue.parse(str.toString());
        JSONArray arr = (JSONArray)obj;
        return arr;
    }

    public static String jsonEncode(List<BasicNameValuePair> params){
        JSONObject obj=new JSONObject();
        int n = params.size();

        for (int i=n-1;i>=0;i--) {
            BasicNameValuePair tmp = params.get(i);
            if (tmp.getValue().indexOf('{') == -1)
                obj.put(tmp.getName(), tmp.getValue());
            else {

                Object objNested = JSONValue.parse(tmp.getValue().toString());
                obj.put(tmp.getName(), objNested);
            }
        }
        return obj.toString();
    }
}
