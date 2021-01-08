package Tests.LinearAPITests;

import API.Linear_HttpRequest;
import API.Vod_HttpRequest;
import Common.DBConnector;
import Common.Report.ExtentTestManager;
import DB.Vod_DB.FilterCatalog_DB;
import Validation.VodValidations.ProgramDetailValidations;
import Common.HttpRequestClient;
import com.aventstack.extentreports.Status;
import data.Vod_Data.ProgramDataProvider;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.Connection;

public class Https {


    String currentMethodName = "";
    public static Logger log = LogManager.getLogger("file");
    int Dataset = -1;
    HttpRequestClient requestClient = new HttpRequestClient();
    Linear_HttpRequest linear_httpRequest = new Linear_HttpRequest();


    @BeforeClass
    public void before() {
        log.info("***********STARTING TESTS FOR QC Woklist API***********");
        //functions = new ProgramDetailValidations();
    }

    @BeforeMethod
    public void beforeTestCase(Method method) {
        if (!method.getName().equals(currentMethodName)) {
            log.info("------------------TEST  - " + method.getName() + "-------------------");
            currentMethodName = method.getName();
        }
    }

    @Test(description = "Validate Https", dataProvider = "Https", dataProviderClass = ProgramDataProvider.class)
    public void Https(String api_params) throws Exception {
        System.out.println("test");
       // ExtentTestManager.getTest().log(Status.INFO, "Https testing report");
        Dataset++;

        HttpResponse response = linear_httpRequest.gethttps(api_params.replace(" ", "%20"));
        String responseString = requestClient.getResponseString(response);
        //System.out.println(responseString);
      /* if( responseString.equals("\""))
       {
           responseString= responseString.replace("\"","null");
       }*/
        Assert.assertTrue(response.getStatusLine().getStatusCode() == 200, "Response Code is not as expected! Expected: 200, Actual: " + response.getStatusLine().getStatusCode());
       // ExtentTestManager.getTest().log(Status.PASS, "status code correct");
        if (responseString.contains("programmeimage")) {
            int logo = responseString.indexOf("programmeimage");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")) {
                System.out.println(true);
                String RS = "Pass";

            }
            else
            {ExtentTestManager.getTest().log(Status.FAIL, "programmeimage mismatch");
                Assert.fail("programmeimage mismatch");
                String RS = "Fail";
            }

            //else{};
        }
         if(responseString.contains("programmeurl")) {
            int logo = responseString.indexOf("programmeurl");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")) {
                System.out.println(true);

            }
            else
            {Assert.fail("programmeurl mismatch");}

            //else{};
        }

        if (responseString.contains("logofileurl")) {
            int logo = responseString.indexOf("logofileurl");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")) {
                System.out.println(true);

            }
            else{Assert.fail("logofileurl mismatch");};
        }
        if(responseString.contains("imagefilepath")) {
            int logo = responseString.indexOf("imagefilepath");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")){
                System.out.println(true);

            }
            else{Assert.fail("imagefilepath mismatch");};
        }

        if(responseString.contains("videourl")) {
            int logo = responseString.indexOf("videourl");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")) {
                System.out.println(true);

            }
            else{Assert.fail("videourl mismatch");};
        }

        if(responseString.contains("genreimagepath")) {
            int logo = responseString.indexOf("genreimagepath");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")){
                System.out.println(true);

            }
            else{Assert.fail("genreimagepath mismatch");};
        }

        if(responseString.contains("filepath")) {
            int logo = responseString.indexOf("filepath");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")) {
                System.out.println(true);

            }
            else{Assert.fail("filepath mismatch");};
        }

        /*if(responseString.contains("channellogo")) {
            int logo = responseString.indexOf("channellogo");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")) {
                System.out.println(true);

            }
            else{Assert.fail("channellogo mismatch");};
        }*/
        if(responseString.contains("channellogourl")) {
            int logo = responseString.indexOf("channellogourl");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")){
                System.out.println(true);

            }
            else{Assert.fail("channellogourl mismatch");};
        }
        if(responseString.contains("castimagefilepath")) {
            int logo = responseString.indexOf("castimagefilepath");
            System.out.println(logo);
            String logofil = responseString.substring(logo, logo + 40);
            if (logofil.contains("https")|| logofil.contains("null")) {
                System.out.println(true);

            }
            else{Assert.fail("castimagefilepath mismatch");};
        }


        /*ProgramDetail_Json jsonOperations = new ProgramDetail_Json();
        JSONObject responseObject = new JSONObject(responseString);

        String apiResults = responseObject.getJSONObject("exceptiondetails").getString("exceptionmessage");
        responseObject.get("logofileurl");



        if (functions.validateException(expected, apiResults)) {
            Ress = "Pass";
            log.info("Exception validated sucessfully");
            ProgramDataProvider.WriteResult(currentMethodName, Ress, Dataset + 1);
        } else {
            Ress = "Fail";
            log.info("Exception mismatch");
            ProgramDataProvider.WriteResult(currentMethodName, Ress, Dataset + 1);
            Assert.fail("exception mismatch");
        }

    }
}*/


    }
}