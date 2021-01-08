package Tests.VodAPiITests;

//import Validation.VodValidations.ProgramDetailValidations;
import API.Vod_HttpRequest;
import Common.DBConnector;
import DB.Vod_DB.ProgramDetail_DB;
import JSONOperation.VOD_Json.ProgramDetail_Json;
import Validation.VodValidations.ProgramDetailValidations;
import Common.ExcelUtility;
import Common.HttpRequestClient;
import data.Vod_Data.ProgramData;
import data.Vod_Data.ProgramDataProvider;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Program_Detail_API {

    ProgramDetailValidations programDetailValidations;
    //Database database;
    String currentMethodName = "";
    public static Logger log = LogManager.getLogger("file");
    int Dataset= -1;
    String Ress;
    DBConnector dbConnector;
    Connection connection;
    HttpRequestClient requestClient = new HttpRequestClient();
    Vod_HttpRequest vod_httpRequest = new Vod_HttpRequest();
    ProgramDetail_DB programDetail_db = new ProgramDetail_DB();


    @BeforeTest
    public void beforeTest()
    {
        log.info("***Starting Database Connection***");
        dbConnector = new DBConnector();
        connection = dbConnector.getDBConnection("VOD");
    }

    @BeforeClass
    public void before()
    {
        log.info("***********STARTING TESTS FOR QC Woklist API***********");
        programDetailValidations = new ProgramDetailValidations();
    }

    @BeforeMethod
    public void beforeTestCase(Method method)
    {
        if (!method.getName().equals(currentMethodName))
        {
            log.info("------------------TEST  - " +method.getName() + "-------------------");
            currentMethodName = method.getName();
        }
    }


@Test(description = "validate program  detail API response", dataProvider = "ProgramDetailResponseValidation", dataProviderClass = ProgramDataProvider.class)

    public void ProgramDetailResponseValidation(String api_params, String progID, String ResponseLang, String RS) throws Exception {
    Dataset++;


    HttpResponse response = vod_httpRequest.GetValidateProgDetailResponse(api_params, progID, ResponseLang);
    String responseString = requestClient.getResponseString(response);
    System.out.println(responseString);
    if (responseString.contains("programmeimage"))
    {
        int logo = responseString.indexOf("programmeimage");
        System.out.println(logo);
        String logofil = responseString.substring(logo, logo+40);
       if ( logofil.contains("http"))
       {
           System.out.println(true);
       }

    }
     if(responseString.contains("programmebackgroundimage")) {
        int logo = responseString.indexOf("programmebackgroundimage");
        System.out.println(logo);
        String logofil = responseString.substring(logo, logo + 30);
        if (logofil.contains("https")) {
            System.out.println(true);

        }
        else{Assert.fail("programmebackgroundimage mismatch");};
    }

    Assert.assertTrue(response.getStatusLine().getStatusCode() == 200, "Response Code is not as expected! Expected: 400, Actual: " + response.getStatusLine().getStatusCode());
    ProgramDetail_Json programDetailJson = new ProgramDetail_Json();
    //JSONObject responseObject = new JSONObject(responseString);


    ArrayList<ProgramData> apiResults = new ArrayList<>(programDetailJson.getProgram(responseString));

    ArrayList<ProgramData> apiResults1 = new ArrayList<>(programDetailJson.getDirectorForProgramDetails(responseString));
    ArrayList<ProgramData> apiResults2 = new ArrayList<>(programDetailJson.getCastForProgramDetails(responseString));


    System.out.println(ResponseLang);
    if(ResponseLang.equalsIgnoreCase("English")) {
        ArrayList<ProgramData> db = new ArrayList<>(programDetail_db.getProgramDetails(progID, connection));

        ArrayList<ProgramData> db1 = new ArrayList<>(programDetail_db.getDirectorForProgramDetails(progID,connection));
        ArrayList<ProgramData> db2 = new ArrayList<>(programDetail_db.getCastForProgramDetails(progID,connection));


        //database.close();

        if ((programDetailValidations.validateProgramDetails(db, apiResults)) && (programDetailValidations.validategetDirectorForProgramDetails(db1, apiResults1)) && (programDetailValidations.validategetCastForProgramDetails(db2, apiResults2)))

        {
            Ress = "Pass";
            log.info("Program Detail fetched succesfully");
            ProgramDataProvider.WriteResult(currentMethodName, Ress, Dataset + 1);
        }

        else {
            Ress = "Fail";
            log.info("Program Detail mismatch");
            ProgramDataProvider.WriteResult(currentMethodName, Ress,Dataset+1);
            Assert.fail("Program Detail mismatch");
        }
    }
    else
    {
        ArrayList<ProgramData> db = new ArrayList<>(programDetail_db.getMultiLingualProgramDetails(progID, ResponseLang,connection));

        if (programDetailValidations.validateProgramDetails(db, apiResults))
        {
            Ress = "Pass";
            log.info("Program Detail fetched succesfully");
            ProgramDataProvider.WriteResult(currentMethodName, Ress,Dataset+1);
        }
        else {
            Ress = "Fail";
            log.info("Program Detail mismatch");
            ProgramDataProvider.WriteResult(currentMethodName, Ress,Dataset+1);
            Assert.fail("Program Detail mismatch");
        }
    }


}

ExcelUtility hp = new ExcelUtility();
@Test(dataProvider = "ProgramDetailsResponseValidate", dataProviderClass = ProgramDataProvider.class)
    public void A(LinkedHashMap<String ,String> data){
        String TestCaseName = hp.getData(data, "TestCaseName");
        System.out.println(TestCaseName);
        String api = hp.getData(data, "API");
        System.out.println(api);
}
}