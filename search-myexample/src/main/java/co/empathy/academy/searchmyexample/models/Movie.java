package co.empathy.academy.searchmyexample.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Movie {

    private  String id;
    private  String primaryTitle;
    private  String originalTitle;
    private  String runTimeMinutes;
    private  String titleType;
    private  String startYear;
    private  boolean isAdult;
    private  String[] genres;

    private String endYear;

    ArrayList<Akas> akas;
    ArrayList<Directors> directors;
    ArrayList<Starring> starring;

    public void setAkas(ArrayList<Akas> akas) {
        this.akas = akas;
    }

    public void setDirectors(ArrayList<Directors> directors) {
        this.directors = directors;
    }

    public void setStarring(ArrayList<Starring> starring) {
        this.starring = starring;
    }

    public ArrayList<Akas> getAkas() {
        return akas;
    }

    public ArrayList<Directors> getDirectors() {
        return directors;
    }

    public ArrayList<Starring> getStarring() {
        return starring;
    }

    public Movie(String id, String titleType, String primaryTitle, String originalTitle,
                 boolean isAdult, String startYear, String endYear, String runTimeMinutes,
                 String[] genres) {
        this.id = id.toString();
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.runTimeMinutes = runTimeMinutes;
        this.titleType = titleType;
        this.startYear = startYear;
        this.isAdult = isAdult;
        this.genres = genres;
        this.endYear=endYear;

    }

    public String getId() {
        return id;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public String getTitleType() {
        return titleType;
    }

    public String getStartYear() {
        return startYear;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String[] getGenres() {
        return genres;
    }

    public String getEndYear(){ return endYear;}


    public void setId(String id)
    {

        this.id=id;
    }


    public void setPrimaryTitle(String primaryTitle)
    {
        this.primaryTitle=primaryTitle;
    }


    public void setOriginalTitle(String originalTitle)
    {
        this.originalTitle=originalTitle;
    }


    public void setRunTimeMinutes(String runTimeMinutes)
    {
        this.runTimeMinutes=runTimeMinutes;
    }


    public void setTitleType(String titleType)
    {
        this.titleType=titleType;
    }


    public void setStartYear(String startYear)
    {
        this.startYear=startYear;
    }


    public void setAdult(Boolean isAdult)
    {
        this.isAdult=isAdult;
    }


    public void setGenres(String[] genres)
    {
        this.genres=genres;
    }

    public void setEndYear(String endYear)
    {
        this.endYear=endYear;
    }


    public Movie withId(String id)
    {
        return new Movie( this.id, this.titleType, this.primaryTitle, this.originalTitle,
        this.isAdult, this.startYear,this.endYear, this.runTimeMinutes,
            this.genres);
    }
}
