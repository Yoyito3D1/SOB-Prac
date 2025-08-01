package deim.urv.cat.homework2.controller;

import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

@Controller
@Path("LogOut")
public class LogoutController { 
    @Context
    private HttpServletRequest request;

    @GET
    @UriRef("log-me-out")
    public String logout() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/Main";
    }
}
