package fr.wilda.quarkus;

import fr.wilda.quarkus.agent.ChatAgent;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    ChatAgent chatAgent;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
      return chatAgent.askAQuestion("Why the sky is blue?");
    }
}
