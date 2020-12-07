package Utilities;

import Models.AnimalAdoptionInfo;
import Models.HighLevelInfo;
import Models.PetJsonResponse;
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



//    public static PetJsonResponse callDataAPI(String species) throws IOException, InterruptedException {
//
//        String uri = uri = "https://test1-api.rescuegroups.org/v5/public/animals/search/available/"+species+"/haspic/?limit=10";
//
//        String jsonLocation = "src/Utilities/petInfo.json";
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(uri))
//                .setHeader("Content-Type","application/vnd.api+json")
//                .setHeader("Authorization","1EKPYyAi")
//                .build();
//        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(jsonLocation)));
//
//        return getResultsFromJSON(new File(jsonLocation));
//    }
//
//    public static PetJsonResponse getResultsFromJSON(File jsonFile)
//    {
//        Gson gson = new Gson();
//        PetJsonResponse searchResult = null;
//
//        //using try with resources (auto closes everything, so don't need a finally)
//        try(
//                FileReader fileReader = new FileReader(jsonFile);
//                JsonReader jsonReader = new JsonReader(fileReader);
//        )
//        {
//            searchResult = gson.fromJson(jsonReader, PetJsonResponse.class);
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return searchResult;
//    }
//
//    public static HighLevelInfo callPetAPI(String species) throws IOException, InterruptedException {
//
//        String uri = uri = "https://test1-api.rescuegroups.org/v5/public/animals/search/available/"+species+"/haspic/?limit=10";
//
//
//        String jsonLocation = "src/Utilities/petInfo.json";
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(uri))
//                .setHeader("Content-Type","application/vnd.api+json")
//                .setHeader("Authorization","1EKPYyAi")
//                .build();
//        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(jsonLocation)));
//
//        return getHighLevelFromJSON(new File(jsonLocation));
//    }
//
//    public static HighLevelInfo getHighLevelFromJSON(File jsonFile)
//    {
//        Gson gson = new Gson();
//        HighLevelInfo searchResult = null;
//
//        //using try with resources (auto closes everything, so don't need a finally)
//        try(
//                FileReader fileReader = new FileReader(jsonFile);
//                JsonReader jsonReader = new JsonReader(fileReader);
//        )
//        {
//            searchResult = gson.fromJson(jsonReader, HighLevelInfo.class);
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return searchResult;
//    }
//
//
//
//    public static AnimalAdoptionInfo callAdoptionAPI(String species) throws IOException, InterruptedException {
//
//        String uri = uri = "https://test1-api.rescuegroups.org/v5/public/animals/search/available/"+species+"/haspic/?limit=10";
//
//
//        String jsonLocation = "src/Utilities/petInfo.json";
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(uri))
//                .setHeader("Content-Type","application/vnd.api+json")
//                .setHeader("Authorization","1EKPYyAi")
//                .build();
//        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(jsonLocation)));
//
//        return getPetsFromJSON(new File(jsonLocation));
//    }
//
//    public static AnimalAdoptionInfo getPetsFromJSON(File jsonFile)
//    {
//        Gson gson = new Gson();
//        AnimalAdoptionInfo petSearchResult = null;
//
//        //using try with resources (auto closes everything, so don't need a finally)
//        try(
//                FileReader fileReader = new FileReader(jsonFile);
//                JsonReader jsonReader = new JsonReader(fileReader);
//        )
//        {
//            petSearchResult = gson.fromJson(jsonReader, AnimalAdoptionInfo.class);
//        } catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        return petSearchResult;
//    }





    //API Call Tester
//    public static void main(String[] args) throws IOException, InterruptedException {
//        callAdoptionAPI("dogs");
//    }
}
