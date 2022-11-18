package co.empathy.academy.searchmyexample.imdb;

import co.empathy.academy.searchmyexample.models.Akas;
import co.empathy.academy.searchmyexample.models.Directors;
import co.empathy.academy.searchmyexample.models.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IMDBReader {


    private final BufferedReader basicsReader;
    private final BufferedReader ratingsReader;
    private final BufferedReader akasReader;
    private final BufferedReader crewReader;
    private final int documentsSize = 20000;
    private boolean moreLines=true;

    private IMDBData data;
    String ratingLine;
    String akasLine;
    String crewLine;
    String principalsLine;

    public IMDBReader(MultipartFile basicsFile, MultipartFile ratingsFile, MultipartFile akasFile, MultipartFile crewFile) {
        try {
            this.basicsReader = new BufferedReader(new InputStreamReader(basicsFile.getInputStream()));
            this.ratingsReader = new BufferedReader(new InputStreamReader(ratingsFile.getInputStream()));
            this.akasReader = new BufferedReader(new InputStreamReader(akasFile.getInputStream()));
            this.crewReader = new BufferedReader(new InputStreamReader(crewFile.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        readLines();
    }

    public void readLines()
    {
        try {
            basicsReader.readLine();
            ratingsReader.readLine();
            akasReader.readLine();
            crewReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Movie> readDocuments() {
        List<Movie> result = new ArrayList<>();
        int LinesRead = 0;
        boolean isAdult;
        String ratingsLine = null;
        try {
            ratingsLine = ratingsReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (LinesRead < documentsSize) {
            try {
                String basicsLine = basicsReader.readLine();

                if (basicsLine == null) {
                    System.out.println("No hay peliculas por leer");
                    return result;
                }

                String[] basics = basicsLine.split("\t");

                if(basics[4]=="1")
                {
                     isAdult=true;
                }else {
                     isAdult= false;
                }


                Movie movie = new Movie(basics[0], basics[1], basics[2], basics[3], isAdult,
                        basics[5],basics[6],basics[7], basics[8].split(","));

                result.add(movie);

                LinesRead++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
