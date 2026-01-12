package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import fr.wilda.quarkus.tool.TimeAndDateTool;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.ToolBox;

@RegisterAiService
public interface TimeAgent {

  @SystemMessage("""
                 You are a specialist in time and date.
                 Use given tools to answer to question.
                 """)
  @UserMessage("{userInput}")
  @Agent(outputKey = "response")
  @ToolBox({TimeAndDateTool.class})
  String askAQuestionEvent(String userInput);
}
