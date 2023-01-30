import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private String result = "";
    private InputStream inputStream;

    public String getPropValues(String value) throws IOException {
        try {
            Properties prop = new Properties();
            String propFileName = "api.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null)
                prop.load(inputStream);
            else
                throw new FileNotFoundException("property file '" + propFileName + "'not found in the classpath");

            String propValue = prop.getProperty(value);
            this.result = propValue;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
