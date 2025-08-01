package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Game;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import java.util.logging.Logger;
import deim.urv.cat.homework2.service.GameService;

@Controller
@Path("GameInfo")
public class GameInfoController {

    @Inject 
    private Models models;

    @Inject
    private GameService videoGameService;

    @GET
    @UriRef("gameInfo")
    public String showGameDetails(@QueryParam("nom") String nom) {
        Game gameInfo = videoGameService.getGameDetailsByName(nom);
        
        if (gameInfo == null) { return "error-page.jsp"; }

        models.put("gameInfo", gameInfo);
        return "gameInfo.jsp";
    }
    
}



