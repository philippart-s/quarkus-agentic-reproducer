package fr.wilda.quarkus;

import fr.wilda.quarkus.agent.AgentWorkflow;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    AgentWorkflow agentWorkflow;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
      return agentWorkflow.executeJarvisWorkflow("Why the sky is blue?");
    }
}
