package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ClassifierAgent {

  enum SubCommand {
    TIME,
    MCP,
    CHAT
  }

  @SystemMessage("""
      You are a classifier that determines which type of sub-command should be called in Jarvis.
     Analyze the question and return ONLY one of the following words: TIME, MCP, or CHAT.

     Classification rules:
       - TIME: question mentioning time, date, ...
       - MCP: questions about GitHub services via MCP, cloud projects, cloud resources
       - CHAT: all other questions that do not fall into the two previous categories

     IMPORTANT: Reply ONLY with the word representing the sub-command, in uppercase, and nothing else.
     No explanation, no sentence, just the word.

     Examples:
      - “Give me the number of Quarkusio GitHub repository” → MCP
      - “What day is it today?” → RAG
      - “What’s the weather like?” → CHAT
      - “What is the current time?” → RAG
      - “Who is the President of France?” → CHAT
      """)
  @UserMessage("{userInput}")
  @Agent(outputKey = "subCommand")
  SubCommand classify(String userInput);
}

