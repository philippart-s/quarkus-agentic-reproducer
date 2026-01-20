package fr.wilda.quarkus;

import fr.wilda.quarkus.agent.RagAgent;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    RagAgent ragAgent;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
      return ragAgent.askAQuestion("What is the schedule at AuroraTech Summit 2027");
    }
}
