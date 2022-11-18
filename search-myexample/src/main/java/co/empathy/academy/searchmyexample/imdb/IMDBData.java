package co.empathy.academy.searchmyexample.imdb;

import co.empathy.academy.searchmyexample.models.*;

import java.util.ArrayList;

public class IMDBData {



    public Movie setLines(String line) {
        Movie movie = new Movie( null, null, null, null,
                false , null, null, null, null);
        if (line != null) {

            String[] fields = line.split("\t");
            movie.setId(fields[0]);
            movie.setTitleType(fields[1]);
            movie.setPrimaryTitle(fields[2]);
            movie.setOriginalTitle(fields[3]);
            movie.setAdult(Boolean.parseBoolean(fields[4]));
            movie.setStartYear(fields[5]);
            movie.setEndYear(fields[6]);
            movie.setRunTimeMinutes(fields[7]);
            movie.setGenres(fields[8].split(","));

        }

        return movie;
    }

    public Akas readAkas(String line){
        Akas aka= new Akas();
        if (line != null) {

            String[] fields = line.split("\t");

            aka.setTitle(fields[2]);
            aka.setRegion(fields[3]);
            aka.setLanguage(fields[4]);
            aka.setIsOriginalTitle(Boolean.parseBoolean(fields[5]));
        }
        return aka;
    }
    public void setAkas(Akas aka, Movie movie) {
        ArrayList<Akas> list;
        if (aka != null) {
            //if it is the first aka, create a new akas array
            if(movie.getAkas()==null) {
                list= new ArrayList();
                list.add(aka);

            }
            else{
                list=movie.getAkas();
                list.add(aka);

            }
            movie.setAkas(list);
        }
    }
    public Starring readStarring(String line){
        Starring starring= new Starring();
        if (line != null) {
            String[] fields = line.split("\t");
            Name name= new Name();
            name.setName(fields[2]);
            starring.setName(name);
            starring.setPersons(fields[5]);
        }
        return starring;
    }
    public void setStarring(Starring starring, Movie movie) {
        ArrayList<Starring> list;
        if (starring != null) {
            //if it is the first starring, create a new starring array
            if(movie.getStarring()==null) {
                list= new ArrayList();
                list.add(starring);
            }
            else{
                list=movie.getStarring();
                list.add(starring);

            }
            movie.setStarring(list);
        }
    }
    public void setDirector(String line,Movie movie){
        ArrayList<Directors> list= new ArrayList<>();
        if (line != null) {
            String[] fields = line.split("\t");
            //read the director field
            String [] directors= fields[1].split(",");
            //add each director to the list
            for (String directorString:directors){
                Directors director= new Directors();
                director.setName(directorString);
                list.add(director);
            }
            movie.setDirectors(list);
        }

    }

/*
    //only adds to the list the nonAdults movies
    public void moviesList(List<Movie> list, Movie movie) {
        if (movie != null) {
            if (movie.getIsAdult() != null && !movie.getIsAdult())
                list.add(movie);
        }

    }
*/

    //checks if 2 lines have the same id
    public boolean sameId(String line1, String line2){
        boolean result= false;
        if(line1!=null && line2!=null){
            String id1 = line1.split("\t")[0];
            String id2 = line2.split("\t")[0];
            if (id1.equalsIgnoreCase(id2))
                result = true;
        }
        return result;
    }
/*

    public String jsonMapping() throws IOException {
        InputStream mappingInputStream = new ClassPathResource("static/mapping.json").getInputStream();
        return new String(mappingInputStream.readAllBytes(), UTF_8);
    }
*/
}
