package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface ClassifierAgent {

  enum SubCommand {
    RAG,
    MCP,
    CHAT
  }

  @SystemMessage("""
      You are a classifier that determines which type of sub-command should be called in Jarvis.
     Analyze the question and return ONLY one of the following words: RAG, MCP, or CHAT.

     Classification rules:
       -  RAG: questions mentioning documents, files, PDFs, “in the document”, “according to the file”
       - MCP: questions about OVHcloud services via MCP, cloud projects, cloud resources
       - CHAT: all other questions that do not fall into the two previous categories

     IMPORTANT: Reply ONLY with the word representing the sub-command, in uppercase, and nothing else.
     No explanation, no sentence, just the word.

     Examples:
      - “Give me the number of Quarkusio repository” → MCP
      - “What does the document say about X?” → RAG
      - “What’s the weather like?” → CHAT
      - “Summarize the content of the PDF file” → RAG
      - “Who is the President of France?” → CHAT
      """)
  @UserMessage("{userInput}")
  @Agent(outputKey = "subCommand")
  SubCommand classify(String userInput);
}

