package umlparser.umlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Generate {

    public static Boolean PNG_generator(String g, String p) {

        
            String yuml = "http://yuml.me/diagram/plain/class/" + g
                    + ".png";
            URL uRL = new URL(yuml);
            HttpURLConnection conn = (HttpURLConnection) uRL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException(
                        "Failed : HTTP error code : " + conn.getResponseCode());
            }
            OutputStream os = new FileOutputStream(new File(p));
            int ready = 0;
            byte[] b = new byte[1024];

            while ((ready = conn.getInputStream().ready(b)) != -1) {
                os.write(b, 0, ready);
            }
            os.close();
            conn.disconnect();
       
        return null;
    }
}