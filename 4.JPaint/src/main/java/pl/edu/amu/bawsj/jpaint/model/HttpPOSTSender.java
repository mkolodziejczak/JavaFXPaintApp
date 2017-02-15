package pl.edu.amu.bawsj.jpaint.model;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Maciek on 2017-01-24.
 */
public class HttpPOSTSender {

    private String url;
    private HashMap<String, String> params = new HashMap<>();
    private final String USER_AGENT = "Mozilla/5.0";
    public HttpPOSTSender(String url)
    {
        this.url = url;
    }

    public void addParam(String paramName, String paramValue)
    {
        params.put(paramName, paramValue);
    }

    private String parseParams()
    {
        StringBuffer stringBuffer = new StringBuffer();

        Iterator it = params.entrySet().iterator();
        stringBuffer.append("{");
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            stringBuffer.append("\""+pair.getKey()+"\":\""+pair.getValue()+"\"");
            it.remove();
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public void sendPost() throws Exception {


        URL obj;
        obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        String urlParameters = parseParams();

        con.setDoOutput(true);

        Thread connectionEstablisher = new Thread() {
            public void run() {
                try {
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();

                    int responseCode = con.getResponseCode();
                    System.out.println(responseCode);
                } catch(IOException v) {
                    System.out.println("Couldn't establish connection with API.");
                }
            }
        };

        connectionEstablisher.start();




    }

}
