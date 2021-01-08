package data.Linear_Data;

import Common.ExcelUtility;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;

public class Linear_DataProvider {

    public static String ProgramDetail_file_location = "C:\\Users\\kapa8005\\Downloads\\VOD_API\\src\\test\\java\\Excel\\ProgramDetailFile.xlsx";
    public static String ProgramDetail_SheetName= "Sheet1";



    @DataProvider(name = "ProgramDetailResponseValidations")
    public static Object[][] ReadVariant1(Method a) throws IOException {

        ExcelUtility hp = new ExcelUtility();
        //System.out.println(a.getName().substring('_'));
        Object[][] dataset = hp.getDataSetObjectArray(ProgramDetail_file_location,ProgramDetail_SheetName,a.getName());

        //System.out.println("dataprovider");
        return dataset;
    }

}
