package co.empathy.academy.searchmyexample.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.empathy.academy.searchmyexample.models.Movie;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.*;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.bucket.filter.Filters;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ElasticEngineImp implements ElasticEngine{
    @Autowired
    private ElasticsearchClient elasticClient;

    @Autowired
    private RestClient restClient;

    public ElasticEngineImp(ElasticsearchClient elasticClient)
    {
        this.elasticClient=elasticClient;
    }

    @Override
    public String listIndex() throws IOException {

        String responseStr;
        try{
            Request request= new Request("GET", "/_cat/indices");
            Response response = restClient.performRequest(request);
            responseStr = EntityUtils.toString(response.getEntity());
            return responseStr;

        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean createIndex(String name, String body)
    {

        try {

            GetIndexRequest existReq = new GetIndexRequest(name);
            CreateIndexRequest request = new CreateIndexRequest(name);
            if (body != null) {
                request.mapping(body, XContentType.JSON);
            }
            CreateIndexResponse createIndexResponse = elasticClient.indices().create(c -> c.index(name));
            return createIndexResponse.acknowledged();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public boolean deleteIndex(String name){

        try {

            DeleteIndexRequest request = new DeleteIndexRequest(name);
            DeleteIndexResponse deleteIndexResponse = elasticClient.indices().delete(c -> c.index(name));
            boolean result= deleteIndexResponse.acknowledged();
            if (result)
            {
                return true;
            }else{
                return false;
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean indexDoc(String indexName, Movie movie) {
        try{
            String id= movie.getId().toString();

            if(id!= null)
            {

                IndexResponse response= elasticClient.index(i -> i.index(indexName).id(id).document(movie));
                System.out.println("Indexed with version"+ response.version());

            }else {
                IndexResponse response=elasticClient.index(i -> i.index(indexName).document(movie));
                System.out.println("Indexed with version"+ response.version());
            }

            System.out.println("The film indexed"+movie.getPrimaryTitle());
            return true;

        }catch (IOException e){
            throw new RuntimeException(e);

        }

    }


    @Override
    public Boolean indexMultipleDocs(String indexName, List<Movie> movies) throws IOException{
        try {
            BulkRequest.Builder br = new BulkRequest.Builder();

            for (Movie movie : movies) {
                String id= movie.getId().toString();
                br.operations(op -> op.index(idx -> idx.index(indexName).id(id).document(movie))
                );
            }

            BulkResponse result = elasticClient.bulk(br.build());


            if (result.errors()) {
                System.out.println("Bulk error indexing multiple docs");
                return false;
            } else
            {
                return true;
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }



}
