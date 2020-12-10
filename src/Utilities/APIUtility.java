package Utilities;


import Models.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class APIUtility {

    /***
     * Calls the RescueGroups.Org's Animal Adoption API to search for pets with the inputted parameters.
     * @param species
     * @param postalCode
     * @param gender
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public static APIResponse callResponseAPI(String species, String postalCode, String gender) throws IOException, InterruptedException {

        String uri = "https://test1-api.rescuegroups.org/v5/public/animals/search/available/"+species+"/haspic/?limit=15";

        String jsonLocation = "src/Utilities/petInfo.json";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .setHeader("Content-Type","application/vnd.api+json")
                .setHeader("Authorization","1EKPYyAi")    //clientID assigned by api owner, i.e. api key
                .build();
        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(jsonLocation)));

        return getResponseFromJSON(new File(jsonLocation));
    }

    /***
     * Parses the json file returned from the API into compatible format using gson
     * @param jsonFile
     * @return
     */
    public static APIResponse getResponseFromJSON(File jsonFile)
    {
        Gson gson = new Gson();
        APIResponse responseSearchResult = null;

        //using try with resources to auto-close everything
        try(
                FileReader fileReader = new FileReader(jsonFile);
                JsonReader jsonReader = new JsonReader(fileReader);
        )
        {
            responseSearchResult = gson.fromJson(jsonReader, APIResponse.class);
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        return responseSearchResult;
    }


    //API Call Tester - used to test if the api call is returning expected results
//    public static void main(String[] args) throws IOException, InterruptedException {
//        callResponseAPI("dogs", "L0M1B1", "female");
//    }

}
