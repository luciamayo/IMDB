package co.empathy.academy.searchmyexample.controller;

import co.empathy.academy.searchmyexample.models.Movie;
import co.empathy.academy.searchmyexample.service.ElasticServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class IndexControll {

    @Autowired
    private ElasticServiceImp elasticService;

    public IndexControll(ElasticServiceImp service) {

        this.elasticService = service;
    }

    @PostMapping("/index")
    public ResponseEntity<String> indexDoc(@RequestParam("basics") MultipartFile basicsFile,
                                           @RequestParam("akas") MultipartFile akasFile,
                                           @RequestParam("crew") MultipartFile crewFile,
                                           @RequestParam("principals") MultipartFile principalsFile
    ) throws IOException {

        Boolean done=elasticService.indexIMDBData(basicsFile,akasFile,crewFile,principalsFile);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);

    }

    @GetMapping("/_cat/indices")
    public ResponseEntity<String> showIndex() throws IOException {

        String indices = elasticService.listIndex();
        return new ResponseEntity<String>(indices, HttpStatus.OK);
    }

    @PutMapping("/{indexName}")
    public ResponseEntity createIndex(@PathVariable String indexName, @RequestBody String source) {

        if (elasticService.createIndex(indexName, source)) {

            return new ResponseEntity(HttpStatus.CREATED);
        } else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{indexName}/_doc")
    public ResponseEntity indexDoc(@PathVariable String indexName, @RequestBody Movie movie) throws IOException {
        boolean created = elasticService.indexDoc(indexName, movie);
        if (created)
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(movie);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

    }

}
