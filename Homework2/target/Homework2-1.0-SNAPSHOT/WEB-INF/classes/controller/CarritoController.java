package deim.urv.cat.homework2.controller;
import deim.urv.cat.homework2.model.Credentials;
import deim.urv.cat.homework2.model.AlertMessage;
import deim.urv.cat.homework2.model.Game;
import deim.urv.cat.homework2.model.Rental;
import deim.urv.cat.homework2.service.CarritoService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import java.util.List;
import java.util.stream.Collectors;
import deim.urv.cat.homework2.service.GameService;

@Controller
@Path("Carrito")
public class CarritoController {
    @Context
    private HttpServletRequest request;
    @Inject
    private HttpSession session;

    @Inject
    private Models models;

    @Inject
    private CarritoService shoppingCartService;

    @Inject
    private GameService videoGameService;

    @GET
    @UriRef("carrito-details")
    public String showShoppingCart(@Context HttpHeaders headers) {
        
        HttpSession sesio = request.getSession();

        Cookie sessionCookie = headers.getCookies().get("JSESSIONID");

        Cookie persistedSessionCookie = (Cookie) sesio.getAttribute("sessionCookie");

        if (persistedSessionCookie != null && persistedSessionCookie.getValue().equals(sessionCookie.getValue())) {
            
            List<Game> videoGamesInCart = shoppingCartService.getVideoGamesInCart();
            models.put("videoGamesInCart", videoGamesInCart);
            return "carrito.jsp";
            
        } else {
            return "redirect:/Main";
        }
       
    }

    @POST
    @UriRef("carrito/add")
    public String addToCart(
            @FormParam("videoGameName") String videoGameName,
            @Context HttpHeaders headers) {

        HttpSession sesio;
        sesio = request.getSession();
        Cookie sessionCookie = headers.getCookies().get("JSESSIONID");
        Cookie persistedSessionCookie = (Cookie) sesio.getAttribute("sessionCookie");

        if (persistedSessionCookie != null && persistedSessionCookie.getValue().equals(sessionCookie.getValue())) {
            Game videogame = videoGameService.getGameDetailsByName(videoGameName);

            if (shoppingCartService.isGameInCart(videogame) || videogame.getDisponibilitat().equals("no")) {
                models.put("message", "El joc ja està al carret");
                return "redirect:/Main";
            }else{
              
                shoppingCartService.addVideoGameToCart(videogame);
                List<Game> cartItems = shoppingCartService.getVideoGamesInCart();
                sesio.setAttribute("cartItems", cartItems);
                models.put("message", "Joc afegit correctament");
                return "redirect:/Main";
            }
        } else {
            return "login.jsp";
        }
    }

    @POST
    @Path("remove")
    public String removeFromCart(@FormParam("videoGameName") String videoGameName) {
        Game videoGame = videoGameService.getGameDetailsByName(videoGameName);
        shoppingCartService.removeVideoGameFromCart(videoGame);
        List<Game> cartItems = shoppingCartService.getVideoGamesInCart();
        session.setAttribute("cartItems", cartItems);
        return "carrito.jsp";
    }
    
    @POST
    @Path("carrito/checkout")
    public String checkout(@Context HttpHeaders headers) {
        List<Game> cartItems = shoppingCartService.getVideoGamesInCart();

        if (cartItems.isEmpty()) {
            AlertMessage.warning("Cap joc al carrito, afegeix algun abans de continuar.");
            return "carrito.jsp";
        }

        HttpSession sesio = request.getSession();
        Credentials usernameCredentials = (Credentials) sesio.getAttribute("credentialsDTO");

        List<String> gameNames = cartItems.stream()
            .map(Game::getNom)
            .collect(Collectors.toList());

        Rental rental = shoppingCartService.processCheckout(gameNames, usernameCredentials);
        models.put("rental", rental);
        return "carrito.jsp";
    }


    
}
