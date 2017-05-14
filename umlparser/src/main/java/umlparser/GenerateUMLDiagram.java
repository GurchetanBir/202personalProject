package umlparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GenerateUMLDiagram {

    public static Boolean generatePNG(String grammarCode, String outputPath) {

        try {
            String link = "https://yuml.me/diagram/plain/class/" + grammarCode
                    + ".png";
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException(
                        "Failed : " + conn.getResponseCode());
            }
            OutputStream os = new FileOutputStream(new File(outputPath));
            int read = 0;
            byte[] bytes = new byte[2048];

            while ((read = conn.getInputStream().read(bytes)) != -1) {
            	os.write(bytes, 0, read);
            }
            os.close();
            conn.disconnect();
        } 
        catch (MalformedURLException e) {
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}