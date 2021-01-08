package Tests.VodAPiITests;

import API.Vod_HttpRequest;
import Common.DBConnector;
import DB.Vod_DB.FilterCatalog_DB;
import JSONOperation.VOD_Json.ProgramDetail_Json;
import Validation.VodValidations.ProgramDetailValidations;
import Common.HttpRequestClient;
import data.Vod_Data.ProgramDataProvider;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.Connection;

public class InputParameterValidation {

    ProgramDetailValidations programDetailValidations;
    String currentMethodName = "";
    public static Logger log = LogManager.getLogger("file");
    int Dataset = -1;
    String Ress;

    DBConnector dbConnector;
    Connection connection;
    HttpRequestClient requestClient = new HttpRequestClient();
    Vod_HttpRequest vod_httpRequest = new Vod_HttpRequest();
    FilterCatalog_DB filterCatalog_db = new FilterCatalog_DB();



    @BeforeTest
    public void beforeTest()
    {
        log.info("***Starting Database Connection***");
       /* dbConnector = new DBConnector();
        connection = dbConnector.getDBConnection("VOD");*/
    }

    @BeforeClass
    public void before() {
        log.info("***********STARTING TESTS FOR QC Woklist API***********");
        programDetailValidations = new ProgramDetailValidations();
    }

    @BeforeMethod
    public void beforeTestCase(Method method) {
        if (!method.getName().equals(currentMethodName)) {
            log.info("------------------TEST  - " + method.getName() + "-------------------");
            currentMethodName = method.getName();
        }
    }

    @Test(description = "Validate mandate params", dataProvider = "InputParametersValidation", dataProviderClass = ProgramDataProvider.class)
    public void InputParametersValidation(String api_params, String expected, String RS) throws Exception {
        Dataset++;

        HttpResponse response = vod_httpRequest.GetValidateProgDetailInputParams(api_params);
        String responseString = requestClient.getResponseString(response);
       // System.out.println(responseString);
        Assert.assertTrue(response.getStatusLine().getStatusCode() == 400, "Response Code is not as expected! Expected: 400, Actual: " + response.getStatusLine().getStatusCode());
        ProgramDetail_Json programDetailJson = new ProgramDetail_Json();
        JSONObject responseObject = new JSONObject(responseString);

        String apiResults = responseObject.getJSONObject("exceptiondetails").getString("exceptionmessage");

        if (programDetailValidations.validateException(expected, apiResults)) {
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
}
