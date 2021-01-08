package data.Vod_Data;

import Common.ExcelUtility;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class ProgramDataProvider {

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


    @DataProvider(name = "InputParametersValidation")
    public static Object[][] ReadVariant() throws IOException
    {
            FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
            workbook = new XSSFWorkbook(fileInputStream); //get my workbook
            worksheet=workbook.getSheet("InputParametersValidation");// get my sheet from workbook
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

    @DataProvider(name = "ProgramDetailsResponseValidate")
    public static Object[][] ReadVariant8(Method a) throws IOException {

        ExcelUtility hp = new ExcelUtility();
        //System.out.println(a.getName());
        Object[][] dataset = hp.getDataSetObjectArray(file_location,"ProgramDetailsResponseValidate",a.getName());


        return dataset;
    }



    @DataProvider(name = "ProgramDetailResponseValidation")
    public static Object[][] ReadVariant2() throws IOException
    {
        FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook(fileInputStream); //get my workbook
        worksheet=workbook.getSheet("ProgramDetailResponseValidation");// get my sheet from workbook
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



    public static void WriteResult(String Sheetname,String Ress, int DR) throws Exception
    {

        FileInputStream file_input_stream= new FileInputStream("C:\\Users\\kapa8005\\Downloads\\VOD_API\\src\\test\\java\\Excel\\Test.xlsx");
        ExcelWBook=new XSSFWorkbook(file_input_stream);
        ExcelWSheet=ExcelWBook.getSheet(Sheetname);
        XSSFRow Row=ExcelWSheet.getRow(0);

        int sheetIndex=ExcelWBook.getSheetIndex(Sheetname);
        DataFormatter formatter = new DataFormatter();
        if(sheetIndex==-1)
        {
            System.out.println("No such sheet in file exists");
        } else      {
            col_num=-1;
            for(int i=0;i<Row.getLastCellNum();i++)
            {
                XSSFCell cols=Row.getCell(i);
                String colsval=formatter.formatCellValue(cols);
                if(colsval.trim().equalsIgnoreCase(ColName.trim()))
                {
                    col_num=i;
                    break;
                }
            }
            Row= ExcelWSheet.getRow(DR);
            try
            {
                //get my Row which is equal to Data  Result and that colNum
                XSSFCell cell=ExcelWSheet.getRow(DR).getCell(col_num);
                // if no cell found then it create cell
                if(cell==null) {
                    cell=Row.createCell(col_num);
                }
                //Set Result is pass in that cell number
                cell.setCellValue(Ress);
                System.out.println("write done");


            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        }
        FileOutputStream file_output_stream=new FileOutputStream("C:\\Users\\kapa8005\\Downloads\\VOD_API\\src\\test\\java\\data\\Test.xlsx");
        ExcelWBook.write(file_output_stream);
        file_output_stream.close();
        if(col_num==-1) {
            System.out.println("Column you are searching for does not exist");
        }
    }


    @DataProvider(name = "Https")
    public static Object[][] ReadVariant5() throws IOException
    {
        FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
        workbook = new XSSFWorkbook(fileInputStream); //get my workbook
        worksheet=workbook.getSheet("Https");// get my sheet from workbook
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


}

