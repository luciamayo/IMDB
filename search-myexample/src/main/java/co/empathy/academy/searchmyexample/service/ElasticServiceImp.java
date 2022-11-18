package co.empathy.academy.searchmyexample.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.empathy.academy.searchmyexample.imdb.IMDBData;
import co.empathy.academy.searchmyexample.imdb.IMDBReader;
import co.empathy.academy.searchmyexample.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElasticServiceImp implements ElasticService{

    @Autowired
    private final ElasticEngine engine;

    private IMDBReader imdb;
    public IMDBData data;
    int numMovies= 20000;

    public ElasticServiceImp(ElasticEngine engine)
    {

        this.engine=engine;
        this.data= new IMDBData();
    }

    @Override
    public String listIndex() throws IOException {
        return engine.listIndex();
    }

    @Override
    public boolean createIndex(String name, String body) {
        return engine.createIndex(name, body);
    }

    @Override
    public boolean deleteIndex(String name) {
        return engine.deleteIndex(name);
    }

    @Override
    public boolean indexDoc(String indexName, Movie movie) {
        return engine.indexDoc(indexName,movie);
    }

    @Override
    public Boolean indexMultipleDocs(String indexName, List<Movie> movies) throws IOException {
        return engine.indexMultipleDocs(indexName,movies);
    }

    @Override
    public Boolean indexIMDBData(MultipartFile basicsFile, MultipartFile akasFile, MultipartFile crewFile, MultipartFile principalsFile) throws IOException {
        imdb = new IMDBReader(basicsFile, akasFile,crewFile,principalsFile);
        imdb.readLines();


        List<Movie> movieList = new ArrayList<>();

        int countMovies = 0;


        //DO IT

        System.out.println("Indexed");
        return true;
    }
}
