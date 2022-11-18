package co.empathy.academy.searchmyexample.service;

import co.empathy.academy.searchmyexample.models.Movie;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ElasticService {

    String listIndex() throws IOException;
    boolean createIndex(String name, String body);
    boolean deleteIndex(String name);
    boolean indexDoc(String indexName, Movie movie);
    Boolean indexMultipleDocs(String indexName, List<Movie> movies) throws IOException;
    Boolean indexIMDBData(MultipartFile basicsFile,
                          MultipartFile akasFile,
                          MultipartFile crewFile,
                          MultipartFile principalsFile) throws IOException;

}
