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
    public abstract void writeData(String fileName);
}
