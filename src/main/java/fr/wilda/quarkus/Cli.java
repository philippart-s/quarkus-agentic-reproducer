package fr.wilda.quarkus;

import fr.wilda.quarkus.agent.AgentWorkflow;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;

import java.util.concurrent.Callable;

// 🐛 Uncomment to see issue
//@TopCommand
//@CommandLine.Command(name="cli")
public class Cli implements Callable<Integer> {
  @Inject
  AgentWorkflow agentWorkflow;

  @Override
  public Integer call() throws Exception {
    Log.info(agentWorkflow.executeJarvisWorkflow("Why the sky is blue?"));
    return 0;
  }
}
