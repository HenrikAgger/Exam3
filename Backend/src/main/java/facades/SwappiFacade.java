/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.JokeDTO;
import dto.JokesDTO;
import entities.Category;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONObject;

/**
 *
 * @author Henrik
 */
public class SwappiFacade {
    public JokeDTO getSwappiData(String category, String apiurl) throws MalformedURLException, IOException{
        URL url = new URL(apiurl + category);
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
        
        JSONObject jsonObject = new JSONObject(jsonStr);
        
        return new JokeDTO(jsonObject.getJSONArray("categories").get(0).toString(), jsonObject.getString("value"));
      }

    public JokesDTO getAll(List<Category> categories) throws InterruptedException, ExecutionException{
        ExecutorService executor = Executors.newCachedThreadPool();
        
        List<Future<JokeDTO>> futures = new ArrayList();
        
        for (Category category : categories) {
            final String cat = category.getName();
            futures.add(executor.submit(new Callable<JokeDTO>() {
                
                @Override
                public JokeDTO call() throws Exception {
                    return getSwappiData(cat, "https://api.chucknorris.io/jokes/random?category=");
                }
            }));
            
        }
        List<JokeDTO> result = new ArrayList();
        for (Future<JokeDTO> future : futures) {
            result.add(future.get());
        }
        
        executor.shutdown();
        return new JokesDTO(result, "api.chocknorris.io");
    }
}
