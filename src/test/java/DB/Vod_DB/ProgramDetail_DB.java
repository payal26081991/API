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

public class ProgramDetail_DB {
    public static Logger log = LogManager.getLogger("file");
    DBConnector db			= new DBConnector();

    public ArrayList<ProgramData> getProgramDetails(String ProgID, Connection connection) {

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



            ResultSet rs1 = db.getResult(connection,query1);
            ResultSet rs2 = db.getResult(connection,query2);
            ResultSet rs3 = db.getResult(connection,query3);
            ResultSet rs4 = db.getResult(connection,query4);
            while ((rs1.next())&& (rs2.next()) && (rs3.next()) && (rs4.next())) {
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

    public ArrayList<ProgramData> getMultiLingualProgramDetails(String ProgID, String langName, Connection connection) {

        ArrayList<ProgramData> results = new ArrayList<>();
        try {

            String query6 = "select LanguageID from Language where LanguageName = '" + langName + "'";
            System.out.println(query6);

            ResultSet rs6 = db.getResult(connection,query6);
            while (rs6.next()) {
                String langid = rs6.getString("LanguageID");
                System.out.println(langid);

                String query2 = "Select ProgrammeID,ProgrammeName,ProgrammeCategory as Genre,ProgrammeSubCategory as SubGenre\n" +
                        "                            ,ProgrammeShortDescription as ShortDescription,Keywords,ProgrammeWapDescription as Synopsis\n" +
                        "                            ,RLM.LanguageName\n" +
                        "                            From [dbo].ProgrammeLanguageMapping PLM\n" +
                        "                            Inner Join Language LG on PLM.LanguageID = LG.LanguageID and LG.LanguageID = '" + langid + "' \n" +
                        "       Left join RegionalLanguageMaster RLM on RLM.LanguageID = LG.LanguageID  and RLM.MultilingualLanguageID = LG.LanguageID\n" +
                        "\t   where ProgrammeID ='" + ProgID + "'";

                System.out.println(query2);

                String query3 = "select * from Programme where ProgrammeID = '" + ProgID + "'";

                ResultSet rs2 = db.getResult(connection,query2);
                ResultSet rs3 = db.getResult(connection,query3);


                while ((rs2.next()) && (rs3.next())) {
                    ProgramData programData = new ProgramData();
                    programData.setProgName(rs2.getString("ProgrammeName"));
                    programData.setProgId(rs2.getString("ProgrammeID"));
                    programData.setSynopsis(rs2.getString("Synopsis"));
                    // programData.setDuration(rs1.getString("Duration"));
                    programData.setProductionYear(rs3.getInt("ProductionYear"));
                    programData.setParentalRating(rs3.getString("Certificate"));

                    programData.setProgLanguage(rs2.getString("LanguageName"));

                    programData.setProgGenre(rs2.getString("Genre"));
                    programData.setProgSubGenre(rs2.getString("SubGenre"));

                    results.add(programData);
                }
            }
        }catch (Exception e) {
            log.error("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
        }
        return results;
    }


    public ArrayList<ProgramData> getDirectorForProgramDetails(String ProgID, Connection connection) {

        ArrayList<ProgramData> results = new ArrayList<>();
        try {

            String query5 = "Select  RM.RoleName,CastName ,[Character]   From RoleMaster RM\n" +
                    "                    Left Join\n" +
                    "                    (\n" +
                    "                        Select CRPM.RoleID, CM.Name as CastName ,[Character]\n" +
                    "                        From CastRoleProgramMapping CRPM With(NoLock)\n" +
                    "                        Inner Join CastMaster CM With(NoLock) On  CM.CastID = CRPM.CastID\n" +
                    "                        Where ProgramID ='" + ProgID + "'\n" +
                    "                    )CRPM On  RM.RoleID = CRPM.RoleID\n" +
                    "                    Where RM.RoleName in ('Director')";


            ResultSet rs5 = db.getResult(connection,query5);
            ArrayList<String> list = new ArrayList<>();

            while (rs5.next()) {
                list.add(rs5.getString("CastName"));

                ProgramData programData = new ProgramData();

                programData.setDirector(list);

                results.add(programData);
            }

        } catch (Exception e) {
            log.error("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
        }
        return results;
    }

    public ArrayList<ProgramData> getCastForProgramDetails(String ProgID, Connection connection) {

        ArrayList<ProgramData> results = new ArrayList<>();
        try {

            String query6 = "Select  RM.RoleName,CastName ,[Character]   From RoleMaster RM\n" +
                    "                    Left Join\n" +
                    "                    (\n" +
                    "                        Select CRPM.RoleID, CM.Name as CastName ,[Character]\n" +
                    "                        From CastRoleProgramMapping CRPM With(NoLock)\n" +
                    "                        Inner Join CastMaster CM With(NoLock) On  CM.CastID = CRPM.CastID\n" +
                    "                        Where ProgramID ='" + ProgID + "'\n" +
                    "                    )CRPM On  RM.RoleID = CRPM.RoleID\n" +
                    "                    Where RM.RoleName in ('Actor')";


            ResultSet rs6 = db.getResult(connection,query6);
            ArrayList<String> list = new ArrayList<>();

            while (rs6.next()) {
                list.add(rs6.getString("CastName"));

                ProgramData programData = new ProgramData();

                programData.setDirector(list);

                results.add(programData);
            }

        } catch (Exception e) {
            log.error("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching Program Details! Message: " + e.getLocalizedMessage());
        }
        return results;
    }

}
