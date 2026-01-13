package fr.wilda.quarkus;

import fr.wilda.quarkus.agent.AgentWorkflow;
import io.quarkus.logging.Log;
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
      //return agentWorkflow.executeJarvisWorkflow("Give me the time.");
      //return agentWorkflow.executeJarvisWorkflow("Why the sky is blue?");
      return agentWorkflow.executeJarvisWorkflow("Give me the stars number for the GitHub repository quarkus-langchain4j de l'organisation quarkiverse");
    }
}
