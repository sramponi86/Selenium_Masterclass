import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class NonRestAssuredTests {
    static URL url;
    static HttpURLConnection conn;
    static String appId;
    static String appKey;
    static List<NameValuePair> queryParams;

    @BeforeClass
    public static void prepareObjects() {
        appId = Constants.APP_ID;
        appKey = Constants.APP_KEY;
        queryParams = new ArrayList<NameValuePair>();
    }

    private String setQueryParams(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean isItFirstParameter = true;

        for(NameValuePair pair: params){
            if(isItFirstParameter)
                isItFirstParameter = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    @Test
    public void performPostRequest() throws IOException {
        queryParams.add(new BasicNameValuePair(Constants.INGREDIENT_KEY, Constants.INGREDIENT_VALUE));
        queryParams.add(new BasicNameValuePair(Constants.APP_ID_KEY, Constants.APP_ID));
        queryParams.add(new BasicNameValuePair(Constants.APPKEY_KEY, Constants.APP_KEY));

        url = new URL(Constants.API_URL + setQueryParams(queryParams));
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.close();
        conn.connect();
        System.out.println(conn.getResponseCode());
        System.out.println(conn.getResponseMessage());

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }

    }
}
