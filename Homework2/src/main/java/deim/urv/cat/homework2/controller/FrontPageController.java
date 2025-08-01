package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Game;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;
import deim.urv.cat.homework2.service.GameService;

@Controller
@Path("Main")
public class FrontPageController {
    
    @Inject
    private Models models;
    
    @Inject
    private GameService videoGameService;

    @GET
    public String showFrontPage() {
        List<Game> allVideoGames = videoGameService.getFilteredVideoGames("", "");
        models.put("videoGames", allVideoGames);
        return "mainpage.jsp";
    }

    @POST
    @UriRef("mainpage")
    public String filterVideoGames(@BeanParam GameFiltre filterDTO) {
        models.put("filtered", filterDTO);
        List<Game> filteredVideoGames = videoGameService.getFilteredVideoGames(filterDTO.getFiltreGenere(), filterDTO.getFiltreConsola());
        models.put("videoGames", filteredVideoGames);
        return "mainpage.jsp";
    }
}
