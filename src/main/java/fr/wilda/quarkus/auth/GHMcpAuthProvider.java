package fr.wilda.quarkus.auth;


import io.quarkiverse.langchain4j.mcp.auth.McpClientAuthProvider;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class GHMcpAuthProvider implements McpClientAuthProvider {

  @ConfigProperty(name = "github.pat")
  String githubPat;

  @Override
  public String getAuthorization(Input input) {
    return "Bearer " + githubPat;
  }
}
