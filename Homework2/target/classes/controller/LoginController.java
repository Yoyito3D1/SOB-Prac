package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Credentials;
import deim.urv.cat.homework2.service.CarritoService;
import deim.urv.cat.homework2.service.CarritoServiceImpl;
import deim.urv.cat.homework2.service.LoginService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.logging.Logger;

@Controller
@Path("Login")
public class LoginController {
    @Inject
    Logger log;
    // Inyectar CarritoService

    
    @Context
    private HttpServletRequest request;

    @Inject
    private LoginService loginService;

    @GET
    @UriRef("log-in-get")
    public String showLoginForm(@Context HttpHeaders headers) {
        HttpSession session = request.getSession();

        Cookie sessionCookie = headers.getCookies().get("JSESSIONID");

        Cookie persistedSessionCookie = (Cookie) session.getAttribute("sessionCookie");

        if (persistedSessionCookie != null && persistedSessionCookie.getValue().equals(sessionCookie.getValue())) {
            return "redirect:/Main";
        } else {
            return "login.jsp";
        }
    }

    @POST
    @UriRef("authenticate")
    public Response authenticateUser(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @Context HttpHeaders headers) {
        
        Credentials credentialsDTO = new Credentials(username, password);
        Response authenticationResponse = loginService.authenticateUser(credentialsDTO);

        if (authenticationResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            HttpSession session = request.getSession(true);
            Cookie sessionCookie = headers.getCookies().get("JSESSIONID");
            String authenticatedUsername = credentialsDTO.getUsername();

            session.setAttribute("usernameCredentials", authenticatedUsername);
            session.setAttribute("sessionCookie", sessionCookie);
            session.setAttribute("credentialsDTO", credentialsDTO);           
            CarritoService carritoEnSesion = new CarritoServiceImpl();
            session.setAttribute("carritoService", carritoEnSesion);
            
            return Response.seeOther(URI.create("Main"))
                    .cookie(new NewCookie(sessionCookie.getName(), sessionCookie.getValue()))
                    .build();
        } else {
            request.getSession().setAttribute("errorMessage", "Invalid username or password");
            return Response.status(Response.Status.UNAUTHORIZED).entity("login.jsp").build();
        }
    }
}


