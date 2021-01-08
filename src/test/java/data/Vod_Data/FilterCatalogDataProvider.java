package data.Vod_Data;

import Common.ExcelUtility;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class FilterCatalogDataProvider {


    public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = "C:\\Users\\kapa8005\\Downloads\\VOD_API\\src\\test\\java\\Excel\\Test.xlsx";
    public static String SheetName= "Sheet1";


    public static String ColName="Result";
    public static int col_num;

    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;

    private static XSSFRow Row;

    @DataProvider(name = "FilterCatalogResponseValidation")
    public static Object[][] ReadVariant2() throws IOException
    {
        FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook(fileInputStream); //get my workbook
        worksheet=workbook.getSheet("FilterCatalogResponseValidation");// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0

        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum

        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array

        for(int i=0; i<RowNum-1; i++) //Loop work for Rows
        {
            XSSFRow row= worksheet.getRow(i+1);
            for (int j=0; j<ColNum; j++) //Loop work for colNum
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                    XSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= ""; //if it get Null value it pass no data
                    else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                    }
                }
            }
        }
        return Data;
    }

    @DataProvider(name = "FilterCatalogResponseValidations")
    public static Object[][] ReadVariant9(Method a) throws IOException {

        ExcelUtility hp = new ExcelUtility();
        //System.out.println(a.getName().substring('_'));
        Object[][] dataset = hp.getDataSetObjectArray(file_location,"FilterCatalogResponseValidation",a.getName());

        //System.out.println("dataprovider");
        return dataset;
    }


}
