import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by jsh3571 on 05/11/2016.
 */

public abstract class DataParser {
    protected Map<String, String> data;

    public DataParser() {
        data = new LinkedHashMap<>();
    }

    public abstract void readData();
    public abstract void writeData();
}
