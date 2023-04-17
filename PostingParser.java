import java.io.*;
import java.net.*;

class PostingParser {

    public static void main(String[] args) throws IOException {

        URL demo = new URL(
                "https://boards.greenhouse.io/rvohealth/jobs/4231267005");

        URLConnection connection = demo.openConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String i;

        while ((i = reader.readLine()) != null) {
            System.out.println(i);
        }

    }
}
