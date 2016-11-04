import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jsh3571 on 05/11/2016.
 */

public abstract class DataParser {
    protected Map<String, String> data;
    protected boolean inHeaderSection;

    public DataParser() {
        data = new LinkedHashMap<>();
        inHeaderSection = true;
    }

    public abstract void readData(String fileName);

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
