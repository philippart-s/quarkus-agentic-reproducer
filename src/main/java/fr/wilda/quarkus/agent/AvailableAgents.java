package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.declarative.ActivationCondition;
import dev.langchain4j.agentic.declarative.ConditionalAgent;
import io.quarkus.logging.Log;

public interface AvailableAgents {

  @ConditionalAgent(
      subAgents = {
          GitHubAgent.class,
          TimeAgent.class,
          ChatAgent.class,
      },
      outputKey = "response"
  )
  String executeAgent(String userInput, ClassifierAgent.SubCommand subCommand);

  @ActivationCondition(GitHubAgent.class)
  static boolean activateOVHcloudAgent(ClassifierAgent.SubCommand subCommand) {
    Log.info("Activation of GitHubAgent: " + subCommand);
    Log.info(subCommand.equals(ClassifierAgent.SubCommand.MCP));
    return subCommand.equals(ClassifierAgent.SubCommand.MCP);
  }

  @ActivationCondition(TimeAgent.class)
  static boolean activateRagAgent(ClassifierAgent.SubCommand subCommand) {
    Log.info("Activation of TimeAgent : " + subCommand);
    Log.info(subCommand.equals(ClassifierAgent.SubCommand.TIME));
    return subCommand.equals(ClassifierAgent.SubCommand.TIME);
  }

  @ActivationCondition(ChatAgent.class)
  static boolean activateJarvisAgent(ClassifierAgent.SubCommand subCommand) {
    Log.info("Activation of ChatAgent : " + subCommand);
    Log.info(subCommand.equals(ClassifierAgent.SubCommand.CHAT));
    return subCommand.equals(ClassifierAgent.SubCommand.CHAT);
  }

}
