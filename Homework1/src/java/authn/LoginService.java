package authn;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

@Path("/login")
public class LoginService {

    @Context
    private HttpServletRequest servletRequest;

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(Credentials credentials) {
        try {
            
            TypedQuery<Credentials> query = entityManager.createNamedQuery("Credentials.findUser", Credentials.class);
            Credentials user = query.setParameter("username", credentials.getUsername()).getSingleResult();

            if (user.getPassword().equals(credentials.getPassword())) {
                HttpSession session = servletRequest.getSession(true);
                session.setAttribute("username", credentials.getUsername());

                NewCookie cookie = new NewCookie("JSESSIONID", session.getId(), null, null, null, -1, false);
                
                return Response.ok().entity("{\"message\": \"Login successful\"}").cookie(cookie).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
