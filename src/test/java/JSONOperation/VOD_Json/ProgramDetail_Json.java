package JSONOperation.VOD_Json;

import data.Vod_Data.ProgramData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;

public class ProgramDetail_Json {

    public static Logger log = LogManager.getLogger("file");
    JSONObject json;

    public ProgramDetail_Json() {

    }

    public ArrayList<ProgramData> getProgram (String responseString)
        {
            ArrayList<ProgramData> results = new ArrayList<>();
            try {
                JSONObject responseObject = new JSONObject(responseString);
               // JSONArray array = new JSONArray(responseString);
                    ProgramData result = new ProgramData();
                    String pname = responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("programmename");

                    result.setProgName(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("programmename"));

                    result.setProgId(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("programmeid"));
                    result.setParentalRating(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("parentalrating"));
                    result.setProductionYear(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getInt("productionyear"));
                   // result.setDuration(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("duration"));
                    result.setSynopsis(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("synopsis"));

                result.setProgLanguage(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("programmelanguage"));
                result.setProgGenre(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("programmegenre"));
                result.setProgSubGenre(responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("programmesubgenre"));


                results.add(result);
                }
            catch(Exception e)
                {
                    log.error("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
                    Assert.fail("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
                }
                return results;
            }

    public ArrayList<ProgramData> getDirectorForProgramDetails (String responseString) {
        ArrayList<ProgramData> results = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseString);

            // JSONArray array = new JSONArray(responseString);
            ProgramData result = new ProgramData();
            String director = responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("director");
            // ArrayList<String> l = new ArrayList<String>(Arrays.asList(director.split(",")));
            result.setDirector(new ArrayList<String>(Arrays.asList(director.split(","))));
            results.add(result);
        }
        catch(Exception e)
        {
            log.error("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
        }
        return results;

    }

    public ArrayList<ProgramData> getCastForProgramDetails (String responseString) {
        ArrayList<ProgramData> results = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseString);

            // JSONArray array = new JSONArray(responseString);
            ProgramData result = new ProgramData();
            String cast = responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("cast");
             ArrayList<String> li = new ArrayList<String>(Arrays.asList(cast.split(",")));
             for(int i=0;i<li.size();i++)
             {
                 System.out.println(li.get(i));
             }
            result.setCast(li);
            results.add(result);
        }
        catch(Exception e)
        {
            log.error("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
        }
        return results;

    }

    public ArrayList<ProgramData> getoperatorname(String responseString) {
        ArrayList<ProgramData> results = new ArrayList<>();
        try {
            JSONObject responseObject = new JSONObject(responseString);

            // JSONArray array = new JSONArray(responseString);
            ProgramData result = new ProgramData();
            JSONArray array = responseObject.getJSONObject("programmeslist").getJSONArray("platforms");
            for (int i = 0; i < array.length(); i++) {
                //QCWorklistData result = new QCWorklistData();
                // String operatorname = (array.getJSONObject(i).getString("operatorname"));
                //System.out.println(operatorname);
                //int recordCount = (array.getJSONObject(i).getInt("recordcount"));

                //String cast = responseObject.getJSONObject("programmefulldetailsforapp").getJSONObject("programme").getString("cast");

                result.setOperatorName(array.getJSONObject(i).getString("operatorname"));
                results.add(result);
            }
        }
        catch(Exception e)
        {
            log.error("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
            Assert.fail("Exception Occured while fetching programme details! Message: " + e.getLocalizedMessage());
        }
        return results;

    }


        }
