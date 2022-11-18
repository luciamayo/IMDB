package co.empathy.academy.searchmyexample.service;

import co.empathy.academy.searchmyexample.models.Movie;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public interface ElasticEngine {

    String listIndex() throws IOException;
    boolean createIndex(String name, String body);
    boolean deleteIndex(String name);
    boolean indexDoc(String indexName, Movie movie);

    Boolean indexMultipleDocs(String indexName, List<Movie> movies) throws IOException;


}
