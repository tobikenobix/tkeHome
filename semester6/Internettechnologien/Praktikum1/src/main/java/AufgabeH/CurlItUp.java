package AufgabeH;
import java.io.IOException;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

public class CurlItUp {
    public static String createHTTPSendRequest(URL url, String RequestTyoe) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestMethod(RequestTyoe);

        connection.connect();
        String response = connection.getResponseMessage();
        connection.disconnect();
        return response;
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.posttestserver.dev/index.html");
        System.out.println(createHTTPSendRequest(url,"GET" ));

    }
}
