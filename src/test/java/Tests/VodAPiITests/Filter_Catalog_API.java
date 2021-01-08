package Tests.VodAPiITests;

import API.Vod_HttpRequest;
import Common.DBConnector;
import Common.ExcelUtility;
import Common.Report.ExtentTestManager;
import DB.Vod_DB.FilterCatalog_DB;
import DB.Vod_DB.ProgramDetail_DB;
import JSONOperation.VOD_Json.ProgramDetail_Json;
import Common.HttpRequestClient;
import Validation.VodValidations.ProgramDetailValidations;
import data.Vod_Data.FilterCatalogDataProvider;
import data.Vod_Data.ProgramData;
import data.Vod_Data.ProgramDataProvider;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Filter_Catalog_API {

    ProgramDetailValidations programDetailValidations;
    //Database database;
    String currentMethodName = "";
    public static Logger log = LogManager.getLogger("file");
    int Dataset= -1;
    String Ress;
    LinkedHashMap<String, String>Result=new LinkedHashMap<String, String>();
    public HashMap<String, String> URLs=null;
    public boolean TestSkip=false;
    public double start,end,total;
    private DecimalFormat df=new DecimalFormat("#.##");
    public boolean TestFail=false;

    DBConnector dbConnector;
    Connection connection;
    HttpRequestClient requestClient = new HttpRequestClient();
    Vod_HttpRequest vod_httpRequest = new Vod_HttpRequest();
    FilterCatalog_DB filterCatalog_db = new FilterCatalog_DB();



    @BeforeTest
    public void beforeTest()
    {
        log.info("***Starting Database Connection***");
        //dbConnector = new DBConnector();
        //connection = dbConnector.getDBConnection("VOD");
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


    @Test(description = "validate filter catalog API response", dataProvider = "FilterCatalogResponseValidation", dataProviderClass = FilterCatalogDataProvider.class)

    public void FilterCatalogResponseValidation(String api_params, String operator, String RS) throws Exception {
        Dataset++;


        HttpRequestClient requestClient = new HttpRequestClient();

        HttpResponse response = vod_httpRequest.GetValidateFilterCatalogResponse(api_params, operator);
        String responseString = requestClient.getResponseString(response);
        //System.out.println(responseString);
        Assert.assertTrue(response.getStatusLine().getStatusCode() == 200, "Response Code is not as expected! Expected: 400, Actual: " + response.getStatusLine().getStatusCode());
        ProgramDetail_Json programDetailJson = new ProgramDetail_Json();

        ArrayList<ProgramData> apiResults = new ArrayList<>(programDetailJson.getoperatorname(responseString));


        ArrayList<ProgramData> db = new ArrayList<>(filterCatalog_db.getOperatorNames(operator, connection));


        if (programDetailValidations.validategetOperatorNames(db, apiResults))

        {
            Ress = "Pass";
            log.info("Catalog Detail fetched succesfully");
            ProgramDataProvider.WriteResult(currentMethodName, Ress, Dataset + 1);
        } else {
            Ress = "Fail";
            log.info("Catalog Detail mismatch");
            ProgramDataProvider.WriteResult(currentMethodName, Ress, Dataset + 1);
            Assert.fail("Catalog Detail mismatch");
        }
    }



    ExcelUtility hp1 = new ExcelUtility();
    @Test(description = "validate filter catalog API response",dataProvider = "FilterCatalogResponseValidations", dataProviderClass = FilterCatalogDataProvider.class)
    public void F(LinkedHashMap<String ,String> data) {
        Dataset++;
        String CaseToRun = hp1.getData(data, "CaseToRun");
        String TestCase = hp1.getData(data, "TestCaseName");
        ExtentTestManager.startTest(TestCase);

        if (CaseToRun.equalsIgnoreCase("N")) {
            System.out.println(TestCase + " is N so skipped");
            TestSkip = true;
            throw new SkipException(TestCase + "is N so skipped");
        } else {

            String TestCaseName = hp1.getData(data, "TestCaseName");
            System.out.println(TestCaseName);
            String api = hp1.getData(data, "API");
            System.out.println(api);
            String Operator = hp1.getData(data, "Operator");
                System.out.println(Operator);

           // String progLang = hp1.getData(data, "ProgLang");
            //System.out.println(progLang);

        }
    }
    
}


