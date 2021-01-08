package Common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class Credentials {

    public static Logger log = LogManager.getLogger("file");
    private static Properties properties = getData();

   // public final static String endpoint = "192.168.1.30/qc";
    public static String VODbaseUrl = "http://ovdservicesuat.whatsonindia.com";
    public static String getProgramDetailAPIEndpoint()
    {
        return VODbaseUrl + "/api/program/v1/";
    }

    public static String LinearBaseUrl = "http://servicesuat.whatsonindia.com";


    public static Properties getData()
    {
        Properties properties=null;
        String propFileName = "src/test/resources/credentials.properties";

        try
        {
            FileInputStream inputStream = new FileInputStream(new File(propFileName));
            properties = new Properties();
            if (inputStream != null)
            {
                properties.load(inputStream);
                inputStream.close();
            }
            else
            {
                log.info("Failed to load property file");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return properties;
    }
}
