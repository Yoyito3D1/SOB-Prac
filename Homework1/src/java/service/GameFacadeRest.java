package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import model.entities.Game;

@Path("/rest/api/v1/game")
public class GameFacadeRest {

    @PersistenceContext
    private EntityManager entityManager;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createGame(Game newGame) {
        try {
            Game existingGame = entityManager.find(Game.class, newGame.getId());
            if (existingGame != null) {
                
                return Response.status(Response.Status.CONFLICT)
                        .entity("Ja existeix un joc amb l'ID " + newGame.getId())
                        .build();
            }

            entityManager.persist(newGame);

            return Response.created(URI.create("/rest/api/v1/game/" + newGame.getId()))
                    .entity(newGame)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el nou joc: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGamesFiltrats(
            @QueryParam("genere") String genere,
            @QueryParam("consola") String consola
    ) {
    try {
        StringBuilder queryBuilder = new StringBuilder("SELECT g FROM Game g WHERE 1 = 1");
        if (genere != null && !genere.isEmpty()) {
            queryBuilder.append(" AND g.genere = :genere");
        }
        if (consola != null && !consola.isEmpty()) {
            queryBuilder.append(" AND g.consola = :consola");
        }
        Query query = entityManager.createQuery(queryBuilder.toString(), Game.class);
        if (genere != null && !genere.isEmpty()) {
            query.setParameter("genere", genere);
        }
        if (consola != null && !consola.isEmpty()) {
            query.setParameter("consola", consola);
        }
        List<Game> games = query.getResultList();
        return Response.ok(games).build();
    } catch (Exception e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Error fetching games")
                .build();
    }
    }
    
    @GET
    @Path("/byname")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getGameByName(@QueryParam("nom") String nom) {
        try {
            if (nom != null) {
                Query query = entityManager.createQuery("SELECT g FROM Game g WHERE g.nom = :nom");
                query.setParameter("nom", nom);

                List<Game> result = query.getResultList();

                if (result.isEmpty()) {
                    return Response.status(Response.Status.NOT_FOUND).entity("Juego no encontrado").build();
                } else {
                    return Response.ok(result.get(0)).build();
                }
            } else {
                return Response.status(Response.Status.BAD_REQUEST).entity("El parámetro 'nom' es requerido").build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error en la búsqueda del juego por nombre").build();
        }
    }
}
