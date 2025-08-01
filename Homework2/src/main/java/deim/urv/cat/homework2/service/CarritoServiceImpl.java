package deim.urv.cat.homework2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import deim.urv.cat.homework2.model.Rental;
import deim.urv.cat.homework2.model.Credentials;

import deim.urv.cat.homework2.model.Game;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

@SessionScoped
@Named("llistaCarrito")
public class CarritoServiceImpl implements CarritoService, Serializable {

    private static final String API_BASE_URL = "http://localhost:8080/Homework1/webresources/rest/api/v1/rental";
    private static final long serialVersionUID = 1L;

    private List<Game> videoGamesInCart;

    public CarritoServiceImpl() {
        initializeVideoGamesInCart();
    }

    private void initializeVideoGamesInCart() {
        if (videoGamesInCart == null) {
            videoGamesInCart = new ArrayList<>();
        }
    }

    @Override
    public List<Game> getVideoGamesInCart() {
        return videoGamesInCart;
    }

    @Override
    public void addVideoGameToCart(Game videoGame) {
        if (!videoGamesInCart.contains(videoGame)) {
            videoGamesInCart.add(videoGame);
        }
    }

    @Override
    public void removeVideoGameFromCart(Game videoGame) {

        for (Iterator<Game> iterator = videoGamesInCart.iterator(); iterator.hasNext(); ) {
            Game gameInCart = iterator.next();
            if (gameInCart.getNom().equals(videoGame.getNom())) {
                iterator.remove();
                break;
            }
        }
    }


    @Override
    public Rental processCheckout(List<String> gameNames, Credentials credentials) {

        if (credentials != null) {
            videoGamesInCart.clear();

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(API_BASE_URL);

            String jsonBody = convertListToJson(gameNames);

            String usernamePassword = credentials.getUsername() + ":" + credentials.getPassword();
            String authorizationHeader = "Basic " + Base64.getEncoder().encodeToString(usernamePassword.getBytes());

            Response response = target
                    .request(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, authorizationHeader)
                    .post(Entity.json(jsonBody));

            if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
                return response.readEntity(Rental.class);
            } else {
                throw new RuntimeException("Error al realizar el checkout. Código de respuesta: " + response.getStatus());
            }
        } else {
            throw new RuntimeException("Error al realizar el checkout. Usuario no autenticado.");
        }
    }


    private String convertListToJson(List<String> gameNames) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(gameNames);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir la lista de nombres de videojuegos a JSON.", e);
        }
    }

    @Override
    public boolean isGameInCart(Game videoGame) {
        return videoGamesInCart.stream().anyMatch(cartItem -> cartItem.getNom().equals(videoGame.getNom()));
    }

}
