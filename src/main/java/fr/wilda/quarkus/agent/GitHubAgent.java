package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;

public interface GitHubAgent {

  @SystemMessage("""
      You are a GitHub specialist agent.
     To access information, use the tools available to you.
     If you don’t know how to answer the question, say that you don’t know.
                 """)
  @UserMessage("{userInput}")
  @ToolBox(fr.wilda.quarkus.tool.TimeAndDateTool.class)
  @McpToolBox
  @Agent(outputKey = "response")
  String askAQuestionEvent(String userInput);
}
