package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;

@RegisterAiService
public interface RagAgent {

  @SystemMessage("""
                 You are a conference specialist.
                 Use given documents to answer to question.
                 """)
  @UserMessage("{userInput}")
  @Agent(outputKey = "agentResponse")
  @ToolBox({RagTool.class})
  String askAQuestionEvent(String userInput);
}
