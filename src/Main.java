/**
 * Created by jsh3571 on 05/11/2016.
 */

public class Main {
    public static void main(String[] args) {
        // HeaderParser HP = new HeaderParser();
        // HP.readHeader("multipart.txt");
        // HP.writeHeader("parsed_multipart.txt");

        // DataParser MPDP = new MultiPartDataParser();
        // MPDP.readData("multipart.txt");
        // MPDP.writeData("parsed_multipart.txt");

        DataParser UEDP = new UrlEncodedDataParser();
        UEDP.readData("urlencoded.txt");
        UEDP.writeData("parsed_urlencoded.txt");
    }
}
