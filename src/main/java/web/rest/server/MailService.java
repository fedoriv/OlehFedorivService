package web.rest.server;

import web.Mail;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/RESTService")
public interface MailService {

    @POST
    @Path("/mail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response send(Mail mail);

    @DELETE
    @Path("/mail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response remove(List<Mail> mail);

    @DELETE
    @Path("/mails")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("application/json; charset=UTF-8")
    Response removeAll();

    @GET
    @Path("/mails")
    @Produces("application/json; charset=UTF-8")
    Response getAll();

    @GET
    @Path("/mails/{email}")
    @Consumes("text/plain; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    Response findByEmail(@PathParam("email") String email);

    @GET
    @Path("/mails/{title}")
    @Consumes("text/plain; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    Response findByTitle(@PathParam("title") String title);

    @GET
    @Path("/mail/{id}")
    @Consumes("text/plain; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    Response findById(@PathParam("id") String id);
}
