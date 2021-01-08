package data.Vod_Data;

import java.util.ArrayList;

public class ProgramData {

    String progId;
    String progName;
    String parentalRating;
    String duration;
    Integer ProductionYear;
    String synopsis;
    String progLanguage;
    String progGenre;
    String progSubGenre;
    ArrayList<String> director;
    ArrayList<String> cast;
   String operatorName;

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String opeartorName) {
        this.operatorName = opeartorName;
    }



    public ArrayList<String> getDirector() {
        return director;
    }

    public void setDirector(ArrayList<String> director) {
        this.director = director;
    }



    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public String getProgLanguage() {
        return progLanguage;
    }

    public void setProgLanguage(String progLanguage) {
        this.progLanguage = progLanguage;
    }

    public String getProgGenre() {
        return progGenre;
    }

    public void setProgGenre(String progGenre) {
        this.progGenre = progGenre;
    }

    public String getProgSubGenre() {
        return progSubGenre;
    }

    public void setProgSubGenre(String progSubGenre) {
        this.progSubGenre = progSubGenre;
    }

    public String getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(String parentalRating) {
        this.parentalRating = parentalRating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getProductionYear() {
        return ProductionYear;
    }

    public void setProductionYear(Integer productionYear) {
        ProductionYear = productionYear;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getProgId() { return progId;}

    public void setProgId(String progId) {
        this.progId = progId;
    }

    public String getProgName() { return progName; }

    public void setProgName(String progName) { this.progName=progName; }



}
