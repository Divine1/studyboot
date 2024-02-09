package com.studyboot.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyboot.model.Image;
import com.studyboot.model.Images;
import kong.unirest.HttpResponse;

import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PictureService {

    Logger logger = LoggerFactory.getLogger(PictureService.class);

    @Value("${pixabay.api_key}")
    private String PIXABAY_API_KEY;

    @Value("${pixabay.api.url}")
    private String PIXABAY_API_URL;

    private RestTemplate restTemplate;

    public PictureService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    public JsonNode getPictureRT(String word){
        logger.info("in getPictureRT()");
        String url = getPixaBayUrl(word);

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode responseNode = response.getBody();
        logger.info("responseNode {} ",responseNode);
        return responseNode;
    }


    /*
    public kong.unirest.JsonNode getPictureUniRest(String word) throws ExecutionException, InterruptedException {
        logger.info("in getPictureUniRest()");
        CompletableFuture<kong.unirest.HttpResponse<kong.unirest.JsonNode>> future = Unirest.get(PIXABAY_API_URL)
                .queryString("key",PIXABAY_API_KEY)
                .queryString("word",word)
                .queryString("image_type","photo")
                .asJsonAsync();

        kong.unirest.JsonNode jsonNode = future.get().getBody();
        logger.info("jsonNode {} ",jsonNode);
        return  jsonNode;


    }

     */

    /**
     * This is a blocking code
     * @param word
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    /*
    public String getPictureUniRest(String word) throws ExecutionException, InterruptedException {

        logger.info("in getPictureUniRest()");
        CompletableFuture<kong.unirest.HttpResponse<String>> future = Unirest.get(PIXABAY_API_URL)
                .queryString("key",PIXABAY_API_KEY)
                .queryString("q",word)
                .queryString("image_type","photo")
                .asStringAsync();


        CompletableFuture<String> cf = future.thenApplyAsync((response)->response.getBody())
                .thenApplyAsync((stringResult)->{
                    ObjectMapper objectMapper = new ObjectMapper();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("<html><body>");
                    try {
                        Images images = objectMapper.readValue(stringResult, Images.class);
                        for(Image image : images.getHits()){
                            stringBuilder.append("<img src=\""+image.getWebformatURL()+"\" />");
                        }
                    } catch (JsonProcessingException e) {
                        stringBuilder.append("<p> ERROR during Deserialization</p>");
                        throw new RuntimeException(e);
                    }
                    stringBuilder.append("</body></html>");
                    return stringBuilder.toString();
                });
        String result = cf.get();
        //logger.info("result {} ",result);
        return  result;


    }

     */

    /**
     * This is a nonblocking code
     * @param word
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public CompletableFuture<String> getPictureUniRest(String word) throws ExecutionException, InterruptedException {

        logger.info("in getPictureUniRest()");
        CompletableFuture<kong.unirest.HttpResponse<String>> future = Unirest.get(PIXABAY_API_URL)
                .queryString("key",PIXABAY_API_KEY)
                .queryString("q",word)
                .queryString("image_type","photo")
                .asStringAsync();

        CompletableFuture<String> cf = future.thenApplyAsync((response)->response.getBody())
                .thenApplyAsync((stringResult)->{
                    ObjectMapper objectMapper = new ObjectMapper();
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("<html><body>");
                    try {
                        Images images = objectMapper.readValue(stringResult, Images.class);
                        for(Image image : images.getHits()){
                            stringBuilder.append("<img src=\""+image.getWebformatURL()+"\" />");
                        }
                    } catch (JsonProcessingException e) {
                        stringBuilder.append("<p> ERROR during Deserialization</p>");
                        throw new RuntimeException(e);
                    }
                    stringBuilder.append("</body></html>");
                    return stringBuilder.toString();
                });
        return  cf;
    }


    private String getPixaBayUrl(String word){
        String url = PIXABAY_API_URL+"?key="+PIXABAY_API_KEY+"&q="+word+"&image_type=photo";
        return url;
    }
}
