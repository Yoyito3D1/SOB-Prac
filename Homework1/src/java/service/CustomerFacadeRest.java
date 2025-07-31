package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

import model.entities.Customer;

@Path("/rest/api/v1/customer")
@Stateless
public class CustomerFacadeRest {

    @PersistenceContext
    private EntityManager entityManager;
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createCustomer(Customer newCustomer) {
        try {
            Customer existingCustomer = entityManager.find(Customer.class, newCustomer.getId());
            if (existingCustomer != null) {
                // Ja existeix un customer amb aquest ID, així que retornem un error
                return Response.status(Response.Status.CONFLICT)
                        .entity("Ja existeix un client amb l'ID " + newCustomer.getId())
                        .build();
            }

            entityManager.persist(newCustomer);

            return Response.created(URI.create("/rest/api/v1/customer/" + newCustomer.getId()))
                    .entity(newCustomer)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al crear el nou client: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerList() {
        try {
            List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
            return Response.ok(customers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtenir la llista de clients: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") Long id) {
        try {
            Customer customer = entityManager.find(Customer.class, id);
            if (customer != null) {
                return Response.ok(customer).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No s'ha trobat cap client amb l'ID: " + id)
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al obtenir el client amb ID " + id + ": " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateCustomerById(@PathParam("id") Long id, Customer updatedCustomer) {
        try {
            Customer existingCustomer = entityManager.find(Customer.class, id);
            if (existingCustomer != null) {
                existingCustomer.setNom(updatedCustomer.getNom());
                existingCustomer.setCognom(updatedCustomer.getCognom());
                return Response.ok().build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("No s'ha trobat cap client amb l'ID: " + id)
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error al actualitzar el client amb ID " + id + ": " + e.getMessage())
                    .build();
        }
    }
}
