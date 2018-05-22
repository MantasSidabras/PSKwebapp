package rest;

import entities.Author;
import persistence.AuthorsDAO;
import usecases.Authors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/authors")
public class AuthorsController {
    @Inject
    private AuthorsDAO authorsDAO;

    @Inject
    private Authors authors;

    @Path("/")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(Author newAuthor) {
        try {
            authors.createNewAuthor(newAuthor);
            return Response.ok().build();
        } catch(Exception e) {
            return Response.serverError().build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Author getById(@PathParam("id") final Integer id) {
        return authorsDAO.findOne(id);
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer authorId,
            Author authorData
    ) {
        try {
            Author existingAuthor = authorsDAO.findOne(authorId);
            if (existingAuthor == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingAuthor.setNickname(authorData.getNickname());
            existingAuthor.setName(authorData.getName());
            authorsDAO.update(existingAuthor);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}