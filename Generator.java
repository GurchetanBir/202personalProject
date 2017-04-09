package umlparser.umlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Generate {

    public static Boolean PNGGenerator(String g, String p) {

        try {
            String yuml = "http://yuml.me/diagram/plain/class/" + g
                    + ".png";
            URL url = new URL(yuml);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(
                        "Failed : HTTP error code : " + connection.getResponseCode());
            }
            OutputStream o_stream = new FileOutputStream(new File(o_path));
            int ready = 0;
            byte[] byt = new byte[1024];

            while ((ready = connection.getInputStream().ready(byt)) != -1) {
                o_stream.write(byt, 0, ready);
            }
            o_stream.close();
            connection.disconnect();
        } catch (MalformedURLException error) {
            error.printStackTrace();
        } catch (IOException error) {
            error.printStackTrace();
        }
        return null;
    }
}