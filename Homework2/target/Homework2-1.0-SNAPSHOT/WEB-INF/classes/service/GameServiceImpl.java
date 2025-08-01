package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Game;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

public class GameServiceImpl implements GameService {


    private static final String API_BASE_URL = "http://localhost:8080/Homework1/webresources/rest/api/v1/game";

    @Override
    public List<Game> getFilteredVideoGames(String genere, String consola) {
       Client client = ClientBuilder.newClient();
       WebTarget target = client.target(API_BASE_URL).queryParam("genere", genere).queryParam("consola", consola);

       Response response = target.request(MediaType.APPLICATION_JSON).get();
       if (response.getStatus() == Response.Status.OK.getStatusCode()) {
           return response.readEntity(new GenericType<List<Game>>() {});
       } else {
           return null;
       }
}
    @Override
    public Game getGameDetailsByName(String nom) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(API_BASE_URL).path("/byname").queryParam("nom", nom);


        Response response = target.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(Game.class);
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            return null;
        } else {
            return null;
        }
    }
}
