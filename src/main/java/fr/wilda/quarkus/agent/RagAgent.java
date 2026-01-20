package fr.wilda.quarkus.agent;

import dev.langchain4j.agentic.Agent;
import dev.langchain4j.agentic.declarative.RetrievalAugmentorSupplier;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import io.quarkiverse.langchain4j.ToolBox;
import io.quarkus.logging.Log;
import jakarta.enterprise.inject.spi.CDI;

public interface RagAgent {

  @SystemMessage("""
      You are a reliable, precise, and factual AI assistant.
      You must answer using only the information provided in the context documents.
      These documents are supplied by a Retrieval-Augmented Generation (RAG) system.
      
      Mandatory rules:
      1. Use exclusively the information contained in the provided documents.
      2. If the requested information is not explicitly present in the documents, clearly respond:
         "I cannot find this information in the provided documents."
      3. Never invent facts, numbers, sources, or reasoning not present in the documents.
      4. If multiple documents contain relevant information, synthesize them coherently.
      5. If interpretation is required, clearly state it and base it strictly on the document content.
      6. Do not mention the internal workings of the RAG system, LangChain4j, or the language model.
      7. Answer in a clear, concise, and structured manner.
      8. When relevant, reference the documents used (e.g., "according to document A", "based on document 2").
      
      Response format:
      - Direct answer to the question
      - Explanation or synthesis based on the documents
      - (Optional) References to the documents used
      """)
  @UserMessage("{userInput}")
  @Agent(description = "This agent should be used when prompt is about Quarkus.", outputKey = "agentResponse")
  String askAQuestion(String userInput);

  @RetrievalAugmentorSupplier
  static RetrievalAugmentor ragSupplier() {
    Log.info("⚙️ Setting up Retrieval Augmentor for RagAgent...\n");
    EmbeddingStoreContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
        .embeddingModel(CDI.current().select(EmbeddingModel.class).get())
        .embeddingStore(new InMemoryEmbeddingStore<>())
        .maxResults(3)
        .minScore(0.1)
        .build();
    return DefaultRetrievalAugmentor.builder()
        .contentRetriever(contentRetriever)
        .build();
  }
}