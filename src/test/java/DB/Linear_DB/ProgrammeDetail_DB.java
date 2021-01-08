package DB.Linear_DB;

import Common.DBConnector;
import data.Vod_Data.ProgramData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProgrammeDetail_DB {
    public static Logger log = LogManager.getLogger("file");

    public ArrayList<ProgramData> getProgramDetails(String ProgID,Connection connection) {

        ArrayList<ProgramData> results = new ArrayList<>();
        try {

            String query1 = "select * from Programme where ProgrammeID = '" + ProgID + "'";
            String query2 = "select * from Language where LanguageID = (Select LanguageID from Programme where ProgrammeID = '" + ProgID + "')";
            String query3 = "select * from M2eCategory where M2eCategoryId = ( select M2eCategoryID from ProgrammeCategoryAssociation where ProgrammeID = '" + ProgID + "')";
            String query4 = "select * from M2eSubCategory where M2eSubCategoryID = ( select M2eSubCategoryID from ProgrammeCategoryAssociation where ProgrammeID = '" + ProgID + "')";

            String query5 = "Select  RM.RoleName,CastName ,[Character]   From RoleMaster RM\n" +
                    "                    Left Join\n" +
                    "                    (\n" +
                    "                        Select CRPM.RoleID, CM.Name as CastName ,[Character]\n" +
                    "                        From CastRoleProgramMapping CRPM With(NoLock)\n" +
                    "                        Inner Join CastMaster CM With(NoLock) On  CM.CastID = CRPM.CastID\n" +
                    "                        Where ProgramID ='" + ProgID + "'\n" +
                    "                    )CRPM On  RM.RoleID = CRPM.RoleID\n" +
                    "                    Where RM.RoleName in ('Director')";


           // ResultSet rs1 = stmt1.executeQuery(query1);
            DBConnector db			= new DBConnector();
            //Connection connection	= db.getDBConnection("VOD_Json");
            ResultSet	rs1			= db.getResult(connection, query1);
            ResultSet rs2 = db.getResult(connection, query2);
            ResultSet rs3 = db.getResult(connection, query3);
            ResultSet rs4 = db.getResult(connection, query4);
            while ((rs1.next()) && (rs2.next()) && (rs3.next()) && (rs4.next())) {
                ProgramData programData = new ProgramData();
                programData.setProgName(rs1.getString("ProgrammeName"));
                programData.setProgId(rs1.getString("ProgrammeID"));
                programData.setSynopsis(rs1.getString("Synopsis"));
                // programData.setDuration(rs1.getString("Duration"));
                programData.setProductionYear(rs1.getInt("ProductionYear"));
                programData.setParentalRating(rs1.getString("Certificate"));

                programData.setProgLanguage(rs2.getString("LanguageName"));

                programData.setProgGenre(rs3.getString("M2eCategoryName"));

                programData.setProgSubGenre(rs4.getString("M2eSubCategoryName"));

                results.add(programData);
            }
        } catch (Exception e) {
            log.error("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
        }
        return results;
    }
}