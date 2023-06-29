package com.example.project_master_utillisation_relation_semantique_dumls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.parser.ParseException;

//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;



public class SearchController {

    @FXML
    private Button closeSearchPage;
    @FXML
    private TextField searItemTextField;

    @FXML
    private Label labelReturnItemSearch;
    @FXML
    private Label Semantictype;
    @FXML
    private Label Abbreviation;

    public void closeButtonSearchAction (ActionEvent e){
        Stage stage = (Stage) closeSearchPage.getScene().getWindow();
        stage.close();
    }

    public void SearchButton(ActionEvent e){
        if(!searItemTextField.getText().isBlank()){
            validationLoginDta();
        } else{
            labelReturnItemSearch.setText("Please enter your username and password!");
        }
    }

    public void validationLoginDta(){

//        years old

        String text = searItemTextField.getText();

        try {

            //connection with umls api with OhkhttpClient bibliotheque
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            //create request and add the url with apikey of umls licence
            Request request = new Request.Builder()
                    .url("https://uts-ws.nlm.nih.gov/rest/search/current?apiKey=10e5f935-843e-4b56-9726-0bfcdef4b0e4&string="+text)
                    .method("GET", null)
                    .addHeader("Cookie", "AWSALB=HGHAa8RMc0zBlOpM5NAfMDVS3Ru9xAInY+AGKZyw3lwUSOVsnRVXOeg33LD9lMPIYMCrX2xO/2OACMyzhtoho91Gyh0dmujAEKUZuY7MZ4SbUHVuJhHuTfAKgmBU; AWSALBCORS=HGHAa8RMc0zBlOpM5NAfMDVS3Ru9xAInY+AGKZyw3lwUSOVsnRVXOeg33LD9lMPIYMCrX2xO/2OACMyzhtoho91Gyh0dmujAEKUZuY7MZ4SbUHVuJhHuTfAKgmBU")
                    .build();

            // get the response from the request
            Response response = client.newCall(request).execute();

            // the response will be return a String
            String stringToParse = response.body().string();

            //Convert the string response to JsonObject to help us to read it
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(stringToParse);

            // Get much information in single JsonObject
            JSONObject json1 = (JSONObject) json.get("result");

            //continue convert in other Json Object to simplifier it to find the link that we can get SemanticType from it
            JSONArray jsonarray = (JSONArray) json1.get("results");

//           make request with second url to get th name and new url content semantics type
            if(jsonarray.isEmpty()){
                Semantictype.setText("Not exist!");
                Abbreviation.setText("Not exist!");
                labelReturnItemSearch.setText("The Term not exist !");
            }else{

                JSONObject json3 = (JSONObject)  jsonarray.get(0);

                // uri content
                String uri = (String) json3.get("uri");

                // make request to get another JsonObject content the url that content semantic type
                try{
                    OkHttpClient client2 = new OkHttpClient().newBuilder()
                            .build();
                    //create request and add the uri with apikey of umls licence and get semantic type
                    Request request2 = new Request.Builder()
                            .url(uri+"?apiKey=10e5f935-843e-4b56-9726-0bfcdef4b0e4")
                            .method("GET", null)
                            .addHeader("Cookie", "AWSALB=HGHAa8RMc0zBlOpM5NAfMDVS3Ru9xAInY+AGKZyw3lwUSOVsnRVXOeg33LD9lMPIYMCrX2xO/2OACMyzhtoho91Gyh0dmujAEKUZuY7MZ4SbUHVuJhHuTfAKgmBU; AWSALBCORS=HGHAa8RMc0zBlOpM5NAfMDVS3Ru9xAInY+AGKZyw3lwUSOVsnRVXOeg33LD9lMPIYMCrX2xO/2OACMyzhtoho91Gyh0dmujAEKUZuY7MZ4SbUHVuJhHuTfAKgmBU")
                            .build();

                    // get the response from the request
                    Response response2 = client2.newCall(request2).execute();

                    // the response will be return a String
                    String stringToParse2 = response2.body().string();

                    //Convert the string response to JsonObject to help us to read it
                    JSONParser parser2 = new JSONParser();
                    JSONObject jsonn2 = (JSONObject) parser2.parse(stringToParse2);

                    // Get much information in single JsonObject
                    JSONObject jsonresult = (JSONObject) jsonn2.get("result");

                    JSONArray jsonSymanticTypeArray = (JSONArray)  jsonresult.get("semanticTypes");

                    JSONObject jsonSymanticType = (JSONObject) jsonSymanticTypeArray.get(0);


                    String uri2 = (String) jsonSymanticType.get("uri");

                    try{


                        OkHttpClient client3 = new OkHttpClient().newBuilder()
                                .build();
                        //create request and add the uri with apikey of umls licence and get semantic type
                        Request request3 = new Request.Builder()
                                .url(uri2+"?apiKey=10e5f935-843e-4b56-9726-0bfcdef4b0e4")
                                .method("GET", null)
                                .addHeader("Cookie", "AWSALB=HGHAa8RMc0zBlOpM5NAfMDVS3Ru9xAInY+AGKZyw3lwUSOVsnRVXOeg33LD9lMPIYMCrX2xO/2OACMyzhtoho91Gyh0dmujAEKUZuY7MZ4SbUHVuJhHuTfAKgmBU; AWSALBCORS=HGHAa8RMc0zBlOpM5NAfMDVS3Ru9xAInY+AGKZyw3lwUSOVsnRVXOeg33LD9lMPIYMCrX2xO/2OACMyzhtoho91Gyh0dmujAEKUZuY7MZ4SbUHVuJhHuTfAKgmBU")
                                .build();

                        // get the response from the request
                        Response response3 = client3.newCall(request3).execute();

                        // the response will be return a String
                        String stringToParse3 = response3.body().string();

                        //Convert the string response to JsonObject to help us to read it
                        JSONParser parser3 = new JSONParser();
                        JSONObject jsonn3 = (JSONObject) parser3.parse(stringToParse3);

                        // Get much information in single JsonObject
                        JSONObject jsonresult2 = (JSONObject) jsonn3.get("result");

                        String name = (String) jsonresult2.get("name");
                        String abbreviation = (String) jsonresult2.get("abbreviation");
                        String definition = (String) jsonresult2.get("definition");

                        labelReturnItemSearch.setText(definition);
                        Semantictype.setText(name);
                        Abbreviation.setText(abbreviation);


                    }catch(Exception e){e.getMessage();}

                }catch (Exception e){
                    labelReturnItemSearch.setText("Oppps!");
                    e.getMessage();
                }

            }

        } catch (IOException e) {
            labelReturnItemSearch.setText("Oppps!");
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
