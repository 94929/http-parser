import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by jsh3571 on 05/11/2016.
 */

public class UrlEncodedDataParser extends DataParser {
    private String dataLine;

    public UrlEncodedDataParser() {
        super();
        dataLine = "";
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
                    if (line.equals(""))
                        inHeaderSection = false;
                } else {
                    if (!line.equals(""))
                        dataLine = line;
                }
            }

            StringTokenizer st = new StringTokenizer(dataLine, "&");
            while (st.hasMoreTokens()) {
                String[] result = st.nextToken().split("=");
                if (result.length > 1) {
                    data.put(result[0], result[1]);
                } else {
                    data.put(result[0], "");
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
