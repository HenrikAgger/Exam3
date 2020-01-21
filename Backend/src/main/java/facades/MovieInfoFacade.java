/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.JokeDTO;
import dto.MovieInfoDTO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.persistence.EntityManagerFactory;
import org.json.JSONObject;

/**
 *
 * @author Henrik
 */
public class MovieInfoFacade {
    private static MovieInfoFacade instance;
    
    //Private Constructor to ensure Singleton
    private MovieInfoFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieInfoFacade getMovieInfoFacade() {
        if (instance == null) {
            instance = new MovieInfoFacade();
        }
        return instance;
    }

    
    private String getData(String endpoint, String title) throws MalformedURLException, IOException{
        URL url = new URL("http://exam-1187.mydemos.dk/" + endpoint + "/" + title.trim().replace(" ", "%20"));
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json;charset=UTF-8");
        con.setRequestProperty("User-Agent", "server"); //remember if you are using SWAPI
        Scanner scan = new Scanner(con.getInputStream());
        String jsonStr = null;
        if (scan.hasNext()) {
          jsonStr = scan.nextLine();
        }        
       scan.close();
        
       return jsonStr;
    }
    
    public MovieInfoDTO getMovieInfo(String title) throws MalformedURLException, IOException{
        String movieInfo = getData("movieInfo", title);
        String moviePoster = getData("moviePoster", title);
        
        JsonObject movieInfoJson = new JsonParser().parse(movieInfo).getAsJsonObject();
        JsonObject moviePosterJson = new JsonParser().parse(moviePoster).getAsJsonObject();
        
        return new MovieInfoDTO(movieInfoJson.get("title").getAsString(),
                                movieInfoJson.get("year").getAsInt(),
                                movieInfoJson.get("plot").getAsString(),
                                movieInfoJson.get("directors").getAsString(),
                                movieInfoJson.get("genres").getAsString(),
                                movieInfoJson.get("cast").getAsString(),
                                moviePosterJson.get("poster").getAsString());
     }
    
    public MovieInfoDTO getMovieInfoAll(String title) throws MalformedURLException, IOException{
        String movieInfo = getData("movieInfo", title);
        String moviePoster = getData("moviePoster", title);
        String imdbScore = getData("imdbScore", title);
        String tomatoesScore = getData("tomatoesScore", title);
        String metacriticScore = getData("metacriticScore", title);
        
        
        
        JsonObject movieInfoJson = new JsonParser().parse(movieInfo).getAsJsonObject();
        JsonObject moviePosterJson = new JsonParser().parse(moviePoster).getAsJsonObject();
        JsonObject imdbScoreJson = new JsonParser().parse(imdbScore).getAsJsonObject();
        JsonObject tomatoesScoreJson = new JsonParser().parse(tomatoesScore).getAsJsonObject();       
        JsonObject metacriticScoreJson = new JsonParser().parse(metacriticScore).getAsJsonObject();
        
        
        
        
        return new MovieInfoDTO(movieInfoJson.get("title").getAsString(),
                                movieInfoJson.get("year").getAsInt(),
                                movieInfoJson.get("plot").getAsString(),
                                movieInfoJson.get("directors").getAsString(),
                                movieInfoJson.get("genres").getAsString(),
                                movieInfoJson.get("cast").getAsString(),
                                moviePosterJson.get("poster").getAsString(),
                                imdbScoreJson.get("imdbRating").getAsDouble(),
                                tomatoesScoreJson.get("viewer").getAsJsonObject().get("rating").getAsDouble(),
                                tomatoesScoreJson.get("critic").getAsJsonObject().get("rating").getAsDouble(),
                                metacriticScoreJson.get("metacritic").getAsDouble());
     }
    
    public static void main(String[] args) throws IOException {
        MovieInfoFacade movieInfoFacade = new MovieInfoFacade();
        
        //System.out.println(movieInfoFacade.getMovieInfo("Die Hard"));
        System.out.println(movieInfoFacade.getMovieInfoAll("Die Hard"));
    }
}

