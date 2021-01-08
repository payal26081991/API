package API;

import Common.Credentials;
import Common.HttpRequestClient;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Linear_HttpRequest {
    HttpRequestClient hp = new HttpRequestClient();

    public static Logger log = LogManager.getLogger("file") ;

    public HttpResponse gethttps(String api_params)
    {
        try
        {
            String requestUrl =api_params;
            System.out.println(requestUrl);
            StopWatch watch = new StopWatch();
            watch.start();
            HttpResponse response = hp.doGet(requestUrl);
            watch.stop();
            log.info(requestUrl);
            log.info("Response Time:"  + watch.toString() +" ; Status Code : " + response.getStatusLine().getStatusCode());
            return response;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Exception Occured during getting Response! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured during  getting Response! Message: " + e.getLocalizedMessage());
            return null;
        }
    }
}
