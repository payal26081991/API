package API;

import Common.Credentials;
import Common.HttpRequestClient;
import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Vod_HttpRequest {

    HttpRequestClient hp = new HttpRequestClient();

    public static Logger log = LogManager.getLogger("file") ;

    public HttpResponse GetValidateProgDetailInputParams(String api_params)
    {
        try
        {
            String requestUrl = Credentials.getProgramDetailAPIEndpoint() + api_params;
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

    public HttpResponse GetValidateProgDetailResponse(String api_params, String programmeId, String ResponseLangugae)
    {
        try
        {
            String requestUrl = Credentials.getProgramDetailAPIEndpoint() + api_params + "programmeid=" +programmeId + "&responselanguage=" +ResponseLangugae;
            System.out.println(requestUrl);
            StopWatch watch = new StopWatch();
            watch.start();
            HttpResponse response = hp.doGet(requestUrl);
            watch.stop();
            //log.info(requestUrl);
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


    public HttpResponse GetValidateFilterCatalogResponse(String api_params, String operator)
    {
        try
        {
            String requestUrl = Credentials.getProgramDetailAPIEndpoint() + api_params + "headendids=" +operator;

            System.out.println(requestUrl);
            StopWatch watch = new StopWatch();
            watch.start();
            HttpResponse response = hp.doGet(requestUrl);
            watch.stop();
            //log.info(requestUrl);
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
