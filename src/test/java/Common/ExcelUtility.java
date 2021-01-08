package Common;

import com.aventstack.extentreports.utils.FileUtil;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ExcelUtility {
    XSSFWorkbook excelWorkbook;

    public String getData(LinkedHashMap<String, String> data, String key) {

        //System.out.println("getData");

        if (data.get(key) != null && data.get(key).length() > 0) {

            return data.get(key);



        } else {
            System.out.println("else");
            return "";
        }
    }

    public LinkedHashMap<String, String> getData(HashMap<Integer, LinkedHashMap<String ,String>> hashmap, int rowNumber){
        LinkedHashMap<String,String> hashData = null;
        hashData= hashmap.get(rowNumber);
        return  hashData;
    }


    public Object[][] getDataSetObjectArray(String strExcelPath, String sheetname, String testCaseName) throws IOException {
        HashMap<Integer,LinkedHashMap<String,String>> hashDataSet = makeTestData(strExcelPath,sheetname,testCaseName);

       // System.out.println("getDataSetObjectArray");
        Object[][] objArray = new Object[hashDataSet.size()][1];
        for(int i =0; i<hashDataSet.size();i++){
            objArray[i][0] = getData(hashDataSet,i);
        }
        return objArray;
    }

    HashMap<Integer,LinkedHashMap<String,String>> hashDataSet = new HashMap<Integer, LinkedHashMap<String, String>>();

   /* public HashMap<Integer, LinkedHashMap<String, String>> makeTestData1(String strExcelPath, String sheetName, String testCaseName){
        XSSFSheet excelSheet = null;
        try{
           // System.out.println("makeTestData");
            FileInputStream excelFileStream = new FileInputStream(strExcelPath);
            XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelFileStream);
            excelSheet = excelWorkbook.getSheet(sheetName);
            int numRows = excelSheet.getLastRowNum();
            int columnIndex = -1;
            for(int count = 0;count<=numRows;count++){
                //System.out.println("for");
          //  for(int count = 0;count<excelSheet.getRow(0).getLastCellNum();count++){
                if(excelSheet.getRow(count).getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName.trim())){
                    //System.out.println("if");
               // if(excelSheet.getRow(0).getCell(count).getStringCellValue().equalsIgnoreCase(testCaseName.trim())){
                    columnIndex = count;
                    int maxcolnum = excelSheet.getRow(columnIndex).getLastCellNum();
                    for(int i = 0;i<maxcolnum;i++ ){
                        hashDataSet.put(i, getRowData(excelSheet,columnIndex));
                        break;
                    }
                    //System.out.println(hashDataSet.get(1));

                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       // System.out.println("last");
        return hashDataSet;
    }
*/

    public HashMap<Integer, LinkedHashMap<String, String>> makeTestData(String strExcelPath, String sheetName,String testCasename) throws IOException {
        XSSFSheet excelSheet=null;
        try {
            FileInputStream excelFileStream=new FileInputStream(strExcelPath);
                    excelWorkbook= new XSSFWorkbook(excelFileStream);
            excelSheet = excelWorkbook.getSheet(sheetName);
            int numRows=excelSheet.getLastRowNum();
            int columnIndex=-1;
            for(int count=0;count<excelSheet.getRow(0).getLastCellNum();count++) {
                if(excelSheet.getRow(count).getCell(0).getStringCellValue().equalsIgnoreCase("TestCaseName")) {
                    columnIndex=count;
                    break;
                }
            }
            for(int rowcount=1,validrows=1;rowcount<=numRows;rowcount++) {
                String testc = excelSheet.getRow(rowcount).getCell(columnIndex).getStringCellValue();
                if(testc.substring(0,testc.indexOf('_')).equalsIgnoreCase(testCasename.trim())) {

                    hashDataSet.put(validrows-1,getRowData(excelSheet,rowcount));
                    validrows++;
                }

            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hashDataSet;
    }


    public LinkedHashMap<String,String> getRowData(XSSFSheet excelSheet, int rowCount){
        LinkedHashMap<String, String> hashRowData = new LinkedHashMap<String ,String >();
        XSSFRow headerRow = excelSheet.getRow(0);
        XSSFRow row = excelSheet.getRow(rowCount);
        int totalInputValues = row.getLastCellNum();
        for(int cellCount = 0; cellCount<totalInputValues;cellCount++){
           XSSFCell headerCell = headerRow.getCell(cellCount);
           XSSFCell cell = row.getCell(cellCount, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
           //cell.setCellType(CellType.STRING);
           //String cellValue = cell.getStringCellValue();
            String cellValue = getCellValue(cell,excelWorkbook);
            System.out.println(cellValue);
           hashRowData.put(headerCell.getStringCellValue(), cellValue);
        }

        return  hashRowData;

    }

    public static String getCellValue(Cell cell, Workbook wb)
    {

        String strCellValue = null;
        if(cell != null){
            switch (cell.getCellTypeEnum()){
                case STRING:
                    strCellValue= cell.toString();
                    break;

                case NUMERIC:
                    if(DateUtil.isCellDateFormatted(cell)){
                        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDDHHMM");
                        strCellValue = dateFormat.format(cell.getDateCellValue());
                    }
                    else {
                        Double value = cell.getNumericCellValue();
                        strCellValue = new String(value.toString());
                    }
                    break;

                case FORMULA:

                    FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

                        switch (evaluator.evaluateFormulaCell(cell)) {
                            case NUMERIC:
                                if(DateUtil.isCellDateFormatted(cell)){
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
                                    strCellValue = dateFormat.format(cell.getDateCellValue());
                                }
                                else {
                                    Double value = cell.getNumericCellValue();
                                    strCellValue = new String(value.toString());
                                }
                                break;
                            case STRING:
                                strCellValue =cell.getRichStringCellValue().toString();
                                break;
                        }
                    }

                    }
                    else
                        strCellValue = "";
        return strCellValue;
            }
        }

