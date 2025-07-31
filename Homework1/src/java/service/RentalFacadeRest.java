package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import java.util.Date;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Calendar;
import model.entities.Game;

import model.entities.Rental;

@Stateless
@Path("/rest/api/v1/rental")
public class RentalFacadeRest extends AbstractFacade<Rental>{

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager entityManager;

    public RentalFacadeRest() {
        super(Rental.class);
    }

    @POST
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRental(List<String> gameNames) {
        try {
            Rental rental = new Rental();
            Date rentalDate = new Date();
            rental.setDataAlquiler(rentalDate);


            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            Date returnDate = calendar.getTime();
            rental.setDataTornada(returnDate);

            List<Game> games = new ArrayList<>();
            for (String gameName : gameNames) {
                Game existingGame = findGameByName(gameName);
                if (existingGame != null && existingGame.getDisponibilitat().equals("yes")) {
                    games.add(existingGame);
                }
            }
            double precio = calcularPrecioTotal( games, rentalDate,  returnDate);
            rental.setPreuTotal(precio);
            rental.setVideojocs(games);
            super.create(rental);

            updateGamesAvailability(games, "no");

            return Response.status(Response.Status.CREATED)
                            .entity(rental)  
                            .build();

        } catch (Exception e) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error en la creación del alquiler").build();
        }
    }

    private void updateGamesAvailability(List<Game> games, String disponibilitat) {
        for (Game game : games) {
            game.setDisponibilitat(disponibilitat);
            entityManager.merge(game);
        }
    }

    @GET
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRentalDetails(@PathParam("id") Long id) {
        try {
            Rental rental = super.find(id);

            if (rental == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No se encontró el alquiler con ID: " + id).build();
            }
            return Response.status(Response.Status.OK).entity(rental).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtener los detalles del alquiler").build();
        }
    }

    private double calcularPrecioTotal(List<Game> games, Date rentalDate, Date returnDate) {
        double precioTotal = 0;
        if (rentalDate != null && returnDate != null) {
            long diffInMillis = returnDate.getTime() - rentalDate.getTime();
            int semanasAlquiler = (int) (diffInMillis / (7 * 24 * 60 * 60 * 1000));
            for (Game game : games) {
                precioTotal += game.getPreuLloguerSetmanal() * semanasAlquiler;
            }
        }
        return precioTotal;
    }

    private Game findGameByName(String nom) {
        try {
            TypedQuery<Game> query = entityManager.createQuery("SELECT g FROM Game g WHERE g.nom = :nom", Game.class);
            query.setParameter("nom", nom);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
