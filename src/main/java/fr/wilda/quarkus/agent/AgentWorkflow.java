package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.declarative.SequenceAgent;

public interface AgentWorkflow {

  @SequenceAgent(outputKey = "response", subAgents =
      {
          ClassifierAgent.class,
          AvailableAgents.class
      })
  String executeJarvisWorkflow(String userInput);

}

