package DB.Vod_DB;

import Common.DBConnector;
import data.Vod_Data.ProgramData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class FilterCatalog_DB {
    public static Logger log = LogManager.getLogger("file");
    DBConnector db			= new DBConnector();

    public ArrayList<ProgramData> getOperatorNames(String operators, Connection connection) {
        ArrayList<ProgramData> results = new ArrayList<>();
        try {


            String query = "select Source_Name from WhatsOnIndiaDB1.Mediae2e.dbo.VCMS_VOD_SourceOperator_Mapping where OperatorId in (" + operators + ")";


            ResultSet rs = db.getResult(connection,query);

            // ArrayList<String> list = new ArrayList<>();


            while (rs.next()) {
                //list.add(rs.getString("Source_Name"));

                ProgramData programData = new ProgramData();

                programData.setOperatorName(rs.getString("Source_Name"));

                results.add(programData);
            }

        } catch (Exception e) {
            log.error("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
        }
        return results;
    }

}
