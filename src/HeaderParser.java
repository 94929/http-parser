import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsh3571 on 05/11/2016.
 */

public class HeaderParser {
    private List<String> headers;
    private List<String> contents;

    public HeaderParser() {
        headers = new ArrayList<>();
        contents = new ArrayList<>();
    }

    public void readHeader(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals("")) break;

                String[] tokens = line.split(" ", 2);
                headers.add(tokens[0].replaceAll("[^a-zA-Z]", ""));
                contents.add(tokens[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeHeader(String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            if (headers.size() != contents.size())
                return;

            for (int i = 0; i < headers.size(); i++)
                bw.write("["+headers.get(i)+"] => ["+contents.get(i)+"]\n");

            bw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
