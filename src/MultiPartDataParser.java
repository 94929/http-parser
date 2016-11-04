import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by jsh3571 on 05/11/2016.
 */

public class MultiPartDataParser extends DataParser {
    private boolean seekingValue;
    private String boundary, key;

    public MultiPartDataParser() {
        super();

        this.inHeaderSection = true;
        this.seekingValue = false;
        this.boundary = "";
        this.key = "";
    }

    @Override
    public void readData(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (inHeaderSection) {
                    if (line.equals("")) {
                        inHeaderSection = false;
                        continue;
                    }

                    String[] tokens = line.split(" ", 2);

                    // Setting boundary
                    if (tokens[0].replaceAll("[^a-zA-Z]", "").equals("ContentType")) {
                        StringTokenizer st = new StringTokenizer(tokens[1]);

                        while (st.hasMoreTokens()) {
                            String current = st.nextToken();
                            String[] result = current.split("=");
                            if (result[0].equals("boundary")) {
                                boundary = result[1].replaceAll("-", "");
                                break;
                            }
                        }
                    }
                } else {
                    if (line.replaceAll("-", "").equals(boundary))
                        seekingValue = false;

                    String[] result = line.split("name=");

                    // Getting key
                    if (result.length > 1) {
                        key = result[1].replaceAll("\"", "");
                        data.put(key, "");
                        seekingValue = true;
                        continue;
                    }

                    // Putting value
                    if (seekingValue)
                        data.put(key, line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeData(String fileName) {
        try {
            File file = new File(fileName);

            if (!file.exists())
                file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (Map.Entry<String, String> entry : data.entrySet())
                bw.write("[" + entry.getKey() + "] : [" + entry.getValue() + "]\n");

            bw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
