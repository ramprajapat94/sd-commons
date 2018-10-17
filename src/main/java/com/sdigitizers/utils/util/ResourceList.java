package com.sdigitizers.utils.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class contains various lists of resources needed often in the project development
 * @author Shriram Prajapat
 */
public class ResourceList {

    private static final Logger LOGGER = LogManager.getLogger(ResourceList.class);
    //private static final String DEFAULT_FILE_PATH_PREFIX = "src/main/resources/listvalues/";
    private static final String DEFAULT_FILE_PATH_PREFIX = "listvalues/";
    /**
     * @param year Starting from the given year to current year
     * @return List of Years Eg.:(givenYear...,2001,2002,.....currentYear)
     */
    public static List<Integer> getAllYearsFrom(int year) {
        List<Integer> list = new ArrayList<>(20);
        int yearFromDate = DateUtil.getYearFromDate(DateUtil.getCurrentDateTime());
        for(; yearFromDate>=year; yearFromDate--){
            list.add(yearFromDate);
        }
        return list;
    }
    /**
     * @param year Starting from the given year to current year
     * @return List of Years Eg.:(currentYear...,2022,2023,.....givenYear)
     */
    public static List<Integer> getAllYearsUpto(int year) {
        List<Integer> list = new ArrayList<>(20);
        int yearFromDate = DateUtil.getYearFromDate(DateUtil.getCurrentDateTime());
        for(; yearFromDate<=year; yearFromDate++){
            list.add(yearFromDate);
        }
        return list;
    }
    
    /**
     * @param month Starting from the January Upto Given month no
     * @return List of Years Eg.:(January, February, March,...July(7))
     */
    public static List<String> getMonthsNameUpto(int month) {
        if(month>12) month = 12;
        List<String> list = new ArrayList<>(month);
        for(int i=1; i<=month; i++){
            list.add(DateUtil.decodeMonth(i));
        }
        return list;
    }

    
    /**
     * Read list of values from file (comma separated values)
     * @param fileName
     * @return 
     */
    public static List<String> readResourceValuesFromFile(String fileName){
        List<String> list = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(ResourceList.class.getClassLoader().getResourceAsStream(fileName)))) {
            list = new ArrayList<>();
            String s = null;
            while ((s = br.readLine()) != null) {
                if(s.startsWith(","))s = s.substring(1);
                list.addAll(Arrays.asList(s.split(",")));
            }
        } catch (IOException e) {
            System.err.println(e);
            LOGGER.error("Error reading values from file - ",e);
        }
        return list;
    }

    
    /**
     * @return List of Salutations (eg. Mr., Mrs., Miss., etc.)
     */
    public static List<String> getEnglishSalutationsList() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"englishTitles.txt");
    }

    /**
     * @return List of Salutations (eg. Kumar, Kumari, Shri, etc.)
     */
    public static List<String> getHindiSalutationsList() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"hindiTitles.txt");
    }

    /**
     * @return List of all nationalities around the globe (eg. Indian, American, Australian, Chinese, etc.)
     */
    public static List<String> getAllNationalities() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"nationalities.txt");
    }

    /**
     * @return List of all Indian States (eg. Assam, Rajasthan, Bihar,.. etc.)
     */
    public static List<String> getAllIndianStates() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"indianStates.txt");
    }
    
    /**
     * @return List of Job-Posting locations of Company/ (eg. Delhi, Mumbai, Jaipur, etc.)
     */
    public static List<String> getJobPostingLocations() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"jobLocations.txt");
    }
    
    /**
     * @return List of all blood-groups (A-,A+,B-,B+,O-,O+,AB-,AB+,N/A)
     */
    public static List<String> getBloodGroupsList() {
        List<String> list = new ArrayList<>(9);
        list.add("A-");
        list.add("A+");
        list.add("B-");
        list.add("B+");
        list.add("O-");
        list.add("O+");
        list.add("AB-");
        list.add("AB+");
        list.add("N/A");
        return list;
    }

    /**
     * @return List of all marital status (Single, married, Widow, Divorcee, Widower, N/A)
     */
    public static List<String> getMaritalStatusList() {
        List<String> list = new ArrayList<>(6);
        list.add("Single");
        list.add("Married");
        list.add("Widow");
        list.add("Widower");
        list.add("Divorcee");
        list.add("N/A");
        return list;
    }

    /**
     * @return List of languages list from INDIA (Hindi, English, Rajasthani, Bengali, Assamese, Bihari, Gujrati, Punjabi etc.)
     */
    public static List<String> getMajorIndianLanguages() {
        List<String> list = new ArrayList<>(36);
        list.add("Hindi");
        list.add("English");
        list.add("Assamese");
        list.add("Bengali");
        list.add("Rajasthani");
        list.add("Bihari");
        list.add("Haryanvi");
        list.add("Punjabi");
        list.add("Sindhi");
        list.add("Telugu");
        list.add("Tamil");
        list.add("Tulu");
        list.add("Marathi");
        list.add("Malayalam");
        list.add("Maithili");
        list.add("Manipuri");
        list.add("Mundari");
        list.add("Urdu");
        list.add("Gujarati");
        list.add("Gondi");
        list.add("Bhili/Bhilodi");
        list.add("Bodo");
        list.add("Kannada");
        list.add("Oriya");
        list.add("Santali");
        list.add("Kashmiri");
        list.add("Kurukh");
        list.add("Khasi");
        list.add("Khandeshi");
        list.add("Konkani");
        list.add("Dogri");
        list.add("Nepali");
        list.add("Ho");
        list.add("Other");
        list.add("N/A");
        
        return list;
    }

    /**
     * @return List of all major religions around the globe (Hinduisn, Christianity, Islam, Jainism, etc.)
     */
    public static List<String> getMajorReligions() {
        List<String> list = new ArrayList<>(14);
        list.add("Buddhism");
        list.add("Christianity");
        list.add("Confucianism");
        list.add("Hinduism");
        list.add("Islam");
        list.add("Jainism");
        list.add("Judaism");
        list.add("Shinto");
        list.add("Sikhism");
        list.add("Taoism");
        list.add("Buddhism");
        list.add("Zoroastrianism");
        list.add("Other");
        list.add("N/A");
        return list;
    }

    /**
     * @return List of all major communities from  INDIA (Assamese, Bengali, Punjabi, Rajasthani, etc.)
     */
    public static List<String> getMajorIndianCommunities() {
        List<String> list = new ArrayList<>(28);
        list.add("Assamese");
        list.add("Bengali");
        list.add("Bihari");
        list.add("Bodo");
        list.add("Christian");
        list.add("Dogri");
        list.add("Gujarati");
        list.add("Kannada");
        list.add("Kashmiri");
        list.add("Konkani");
        list.add("Maithili");
        list.add("Malayalam");
        list.add("Manipuri");
        list.add("Marathi");
        list.add("Muslim");
        list.add("Nepali");
        list.add("Nagamese");
        list.add("Oriya");
        list.add("Punjabi");
        list.add("Rajasthani");
        list.add("Sanskrit");
        list.add("Santali");
        list.add("Sindhi");
        list.add("Tamil");
        list.add("Telugu");
        list.add("Other");
        list.add("N/A");
        
        return list;
    }

    /**
     * @return List of all major Caste from INDIA (General, OBC, MOBC, SC, ST(H), ST(P), N/A)
     */
    public static List<String> getIndianCasteCategories() {
        List<String> list = new ArrayList<>(7);
        list.add("General");
        list.add("OBC");
        list.add("MOBC");
        list.add("SC");
        list.add("ST(H)");
        list.add("ST(P)");
        list.add("N/A");
        return list;
    }

    /**
     * @return List of all educational board list from INDIA
     */
    public static List<String> getEducationalBoardsList() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"indianEducationBoards.txt");
    }

    /**
     * @return List of all countries around the globe
     */
    public static List<String> getAllCountriesList() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"countries.txt");
    }
    
    /**
     * @return List of all country codes around the globe
     */
    public static List<String> getAllCountryCodesList() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"country_codes.txt");
    }
    

    /**
     * @return List of all major banks around the globe
     */
    public static List<String> getBankList() {
        return readResourceValuesFromFile(DEFAULT_FILE_PATH_PREFIX+"banks.txt");
    }


    /**
     * @return List of all major subjects category
     */
    public static List<String> getSubjectsCategory() {
        List<String> list = new ArrayList<>(16);
        list.add("Mechanical");
        list.add("Civil");
        list.add("Electrical");
        list.add("Electronics");
        list.add("Computer & IT");
        list.add("Chemistry");
        list.add("Physics");
        list.add("Mathematics");
        list.add("Economics");
        list.add("Management");
        list.add("Finance");
        list.add("Business");
        list.add("Marketing");
        list.add("Environment");
        list.add("Ethics");
        list.add("Social");
        return list;
    }
    
    /**
     * @return List of subject's application type (Threory, Practical, Thesis, Training, etc.)
     */
    public static List<String> getSubjectsApplicationType() {
        List<String> list = new ArrayList<>(8);
        list.add("Theory");
        list.add("Practical");
        list.add("Thesis");
        list.add("Project");
        list.add("Training");
        list.add("Seminar");
        list.add("Internship");
        list.add("Presentation");
        return list;
    }
    
    /**
     * @return List of subject's selection type (Core, Elective-I, Elective-II, etc.)
     */
    public static List<String> getSubjectsSelectionType() {
        List<String> list = new ArrayList<>(7);
        list.add("Core");
        list.add("Elective-I");
        list.add("Elective-II");
        list.add("Elective-III");
        list.add("Elective-IV");
        list.add("Elective-V");
        list.add("Elective-VI");
        return list;
    }
    
    /**
     * @return List of Subject Credits
     */
    public static List<Integer> getSubjectCredits() {
        List<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        return list;
    }
    
    
    /**
     * @param school School name
     * @return List of Subject types based on school division
     */
    public List<String> getGeneralSubjectTypes(String school){
      List<String> list = new ArrayList(10);
        if(school.equalsIgnoreCase("SOB") || school.equalsIgnoreCase("SSS")){
            list.add("Entrepreneurship");
            list.add("Management");
            list.add("Taxation");
            list.add("Accounting");
            list.add("Finance");
            list.add("Business");
            list.add("Sociology");
            list.add("Social Work");
            list.add("Statistics");
            list.add("Marketing");
        }
        else{
            list.add("Maths");
            list.add("Physics");
            list.add("Chemistry");
            list.add("Computer");
            list.add("Civil");
            list.add("Workshop");
            list.add("Mechanical");
            list.add("Electronics");
            list.add("Electrical");
            list.add("English");
       }
        return list;
    }

}
