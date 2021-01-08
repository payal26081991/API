package Validation.VodValidations;

import data.Vod_Data.ProgramData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;


public class ProgramDetailValidations {

    public static Logger log = LogManager.getLogger("file");

    public boolean validateException(String expected, String actual) {

        if (!expected.trim().equals(actual.trim())) {
            //Assert.fail("Expected :" + expected + "\nActual :" + actual);
            return false;
        } else return true;

    }

    public boolean validateProgramDetails(ArrayList<ProgramData> expected, ArrayList<ProgramData> actual) {
        if (expected.size() != actual.size()) {
            log.error("Image Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
            Assert.fail("Image Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
        }

        boolean mismatchFound, mismatchProgNameFound, mismatchDurationFound, mismatchRatingFound, mismatchYearFound, mismatchSynopsis, mismatchLanguage, mismatchGenre, mismatchSubGenre;
        mismatchFound = mismatchDurationFound = mismatchRatingFound = mismatchYearFound = mismatchProgNameFound = mismatchSynopsis = mismatchLanguage = mismatchGenre = mismatchSubGenre = false;
        for (int i = 0; i < expected.size(); i++) {
            boolean match = false;
            for (int j = 0; j < actual.size(); j++) {
                //System.out.println(expected.get(i).getProgId());
                //System.out.println(actual.get(j).getProgId());


                if (expected.get(i).getProgId().equals(actual.get(j).getProgId())) {
                    match = true;
                    if (!expected.get(i).getProgName().equals(actual.get(j).getProgName())) {
                        mismatchProgNameFound = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Name Mismatch! Expected: " + expected.get(i).getProgName() + " ;; Actual: " + actual.get(j).getProgName());
                    }
                    /*if (!expected.get(i).getDuration().equals(actual.get(j).getDuration())) {
                        mismatchDurationFound = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Name Mismatch! Expected: " + expected.get(i).getDuration() + " ;; Actual: " + actual.get(j).getDuration());
                    }*/
                    if (!expected.get(i).getParentalRating().equals(actual.get(j).getParentalRating())) {
                        mismatchRatingFound = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Parental Rating Mismatch! Expected: " + expected.get(i).getParentalRating() + " ;; Actual: " + actual.get(j).getParentalRating());
                    }
                    if (!expected.get(i).getProductionYear().equals(actual.get(j).getProductionYear())) {
                        mismatchYearFound = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Production YEAR Mismatch! Expected: " + expected.get(i).getProductionYear() + " ;; Actual: " + actual.get(j).getProductionYear());
                    }
                    if (!expected.get(i).getSynopsis().equals(actual.get(j).getSynopsis())) {
                        mismatchSynopsis = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Synopsis Mismatch! Expected: " + expected.get(i).getSynopsis() + " ;; Actual: " + actual.get(j).getSynopsis());
                    }
                    if (!expected.get(i).getProgLanguage().equals(actual.get(j).getProgLanguage())) {
                        mismatchSynopsis = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Programme Langugae Mismatch! Expected: " + expected.get(i).getProgLanguage() + " ;; Actual: " + actual.get(j).getProductionYear());
                    }
                    if (!expected.get(i).getProgGenre().equals(actual.get(j).getProgGenre())) {
                        mismatchSynopsis = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " Genre Mismatch! Expected: " + expected.get(i).getProgGenre() + " ;; Actual: " + actual.get(j).getProgGenre());
                    }
                    if (!expected.get(i).getProgSubGenre().equals(actual.get(j).getProgSubGenre())) {
                        mismatchSynopsis = true;
                        log.error("Account Id: " + expected.get(i).getProgId() + " SubGenre Mismatch! Expected: " + expected.get(i).getProgSubGenre() + " ;; Actual: " + actual.get(j).getProgSubGenre());
                    }
                }
            }
            if (!match) {
                log.error("FAIL: Prog id not found in search results: " + expected.get(i).getProgId());
                mismatchFound = true;
            }

        }
        if (mismatchFound) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validategetDirectorForProgramDetails(ArrayList<ProgramData> expected, ArrayList<ProgramData> actual) {
        if (expected.size() != actual.size()) {
            log.error("Image Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
            Assert.fail("Image Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
        }

        boolean mismatchDirectorFound;
        mismatchDirectorFound = false;
        for (int i = 0; i < expected.size(); i++) {
            boolean match = false;
            for (int j = 0; j < actual.size(); j++) {
                //log.info( expected.get(i).getDirector());
                //log.info( actual.get(i).getDirector());

                if (!expected.get(i).getDirector().equals(actual.get(j).getDirector())) {

                    mismatchDirectorFound = true;
                    log.error("Account Id: " + expected.get(i).getProgId() + " Director Mismatch! Expected: " + expected.get(i).getDirector() + " ;; Actual: " + actual.get(j).getDirector());
                }
            }
        }
        if (mismatchDirectorFound) {
            return false;
        } else {
            return true;
        }
    }


    public boolean validategetCastForProgramDetails(ArrayList<ProgramData> expected, ArrayList<ProgramData> actual) {
        if (expected.size() != actual.size()) {
            log.error("Cast Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
            Assert.fail("Cast Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
        }

        boolean mismatchCastFound;
        mismatchCastFound = false;
        for (int i = 0; i < expected.size(); i++) {
            boolean match = false;
            for (int j = 0; j < actual.size(); j++) {
                //log.info( expected.get(i).getDirector());
                //log.info( actual.get(i).getDirector());

                if (!expected.get(i).getCast().equals(actual.get(j).getCast())) {

                    mismatchCastFound = true;
                    log.error("Account Id: " + expected.get(i).getProgId() + " Cast Mismatch! Expected: " + expected.get(i).getCast() + " ;; Actual: " + actual.get(j).getCast());
                }
            }
        }
        if (mismatchCastFound) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validategetOperatorNames(ArrayList<ProgramData> expected, ArrayList<ProgramData> actual) {
        if (expected.size() != actual.size()) {
            log.error("operator Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
            Assert.fail("operator Count Mismatch!\nExpected count:" + expected.size() + "\nActual count:" + actual.size());
        }

        boolean mismatchCastFound;
        mismatchCastFound = false;
        
        for (int i = 0; i < expected.size(); i++) {
            boolean match = false;
            for (int j = 0; j < actual.size(); j++) {
                //log.info( expected.get(i).getDirector());
                //log.info( actual.get(i).getDirector());

                if (!expected.get(i).getOperatorName().contains(actual.get(j).getOperatorName())) {

                    mismatchCastFound = true;
                    log.error("Account Id: " + expected.get(i).getOperatorName() + " Cast Mismatch! Expected: " + expected.get(i).getOperatorName() + " ;; Actual: " + actual.get(j).getOperatorName());
                }
            }
        }
        if (mismatchCastFound) {
            return false;
        } else {
            return true;
        }
    }


}

