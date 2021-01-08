package Common;


import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.Map;


public class HttpRequestClient {

    HttpClient httpClient;
    CookieStore httpCookieStore;
    public static Logger log = LogManager.getLogger("file") ;

    public HttpRequestClient()
    {
        httpCookieStore = new BasicCookieStore();
        httpClient = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore).build();

    }

    public HttpResponse doGet(String URL)
    {
        try
        {
            //String encodedurl = URLEncoder.encode(URL,"UTF-8");
            HttpGet get = new HttpGet(URL);
            HttpResponse response = httpClient.execute(get);
            log.info("GET REQUEST");
            return  response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public HttpResponse doPost(String URL, Map<String, String> HeaderValues, String postBody )
    {
        HttpPost post = null ; //= new HttpPost(URL);
        try
        {
             post = new HttpPost(URL);
            if(HeaderValues.size() > 0)
            {
                for(Map.Entry<String, String> headerValue : HeaderValues.entrySet())
                {
                    post.setHeader(headerValue.getKey(), headerValue.getValue());
                }
            }
            StringEntity postingString =  new StringEntity(postBody);
            post.setEntity(postingString);

            HttpResponse response= httpClient.execute(post);
            //post.releaseConnection();
            log.info("POST REQUEST");
            return response;

        } catch (Exception e) {
            return null;
        }
        /*finally{
            post.releaseConnection();
        }*/
    }

    public String   getResponseString(HttpResponse response)
    {
        try
        {
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");

            EntityUtils.consumeQuietly(entity);
            return responseString;
        }
        catch (Exception e)
        {
            log.error("Exception Occured! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured! Message: " + e.getLocalizedMessage());
            return "Exception Occured! Message: " + e.getLocalizedMessage();
        }

    }
}


