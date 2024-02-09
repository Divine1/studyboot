package com.studyboot.controller;

import com.studyboot.service.PictureService;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/pictures")
public class PictureController {

    Logger logger = LoggerFactory.getLogger(PictureController.class);


    private PictureService pictureService;

    public PictureController(PictureService pictureService){
        this.pictureService=pictureService;
    }

    @GetMapping("/{word}/rt")
    public ResponseEntity<JsonNode> findPicturesByWordRT(@PathVariable String word){

        JsonNode jsonNode = this.pictureService.getPictureRT(word);
        ResponseEntity<JsonNode> responseEntity = new ResponseEntity<>(jsonNode,null,200);
        return responseEntity;

    }

    /*
    @GetMapping("/{word}/unirest")
    public ResponseEntity<String> findPicturesByWordUnirest(@PathVariable String word)  {

        logger.info("in findPicturesByWordUnirest()");
        kong.unirest.JsonNode jsonNode = null;
        ResponseEntity<String> responseEntity =null;
        try {
            jsonNode = this.pictureService.getPictureUniRest(word);

            logger.info("controller jsonNode {} ",jsonNode);
            String output = jsonNode.toString();
            logger.info("output {}",output);
            responseEntity = new ResponseEntity<>(output,null,200);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return responseEntity;

    }

     */

    /**
     * this is a blocking code
     * @param word
     * @return
     */
    /*
    @GetMapping("/{word}/unirest")
    public ResponseEntity<String> findPicturesByWordUnirest(@PathVariable String word)  {

        logger.info("in findPicturesByWordUnirest()");
        String output = null;
        ResponseEntity<String> responseEntity =null;
        try {
            output = this.pictureService.getPictureUniRest(word);

            logger.info("controller output {}",output);
            responseEntity = new ResponseEntity<>(output,null,200);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }
    */


    /**
     * This is a nonblocking code
     * @param word
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping("/{word}/unirest")
    public CompletableFuture<String> findPicturesByWordUnirest(@PathVariable String word) throws ExecutionException, InterruptedException {

        logger.info("in findPicturesByWordUnirest()");
        return this.pictureService.getPictureUniRest(word);

    }

}
