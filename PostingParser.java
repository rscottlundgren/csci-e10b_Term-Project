import java.io.*;
import java.net.*;

import javax.swing.text.html.parser.ParserDelegator;

class PostingParser {

    public static void main(String[] args) throws IOException {

        /**
         * // Create A New URL Object
         * URL demo = new URL(
         * "https://boards.greenhouse.io/rvohealth/jobs/4231267005");
         * // Open A Connection To The URL
         * URLConnection connection = demo.openConnection();
         * //
         * InputStreamReader inputStream = new
         * InputStreamReader(connection.getInputStream());
         * //
         * BufferedReader reader = new BufferedReader(inputStream);
         * //
         * String i;
         * //
         * while ((i = reader.readLine()) != null) {
         * System.out.println(i);
         * }
         */

        URL demo = new URL("https://boards.greenhouse.io/rvohealth/jobs/4231267005");
        URLConnection connection = demo.openConnection();
        InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(inputStream);
        ParserDelegator pd = new ParserDelegator();
        pd.parse(reader, new GreenhouseParse(), false);
    }
}
