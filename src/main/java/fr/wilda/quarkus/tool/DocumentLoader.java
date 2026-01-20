package fr.wilda.quarkus.tool;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.nio.file.Files;
import java.nio.file.Path;

import static dev.langchain4j.data.document.splitter.DocumentSplitters.recursive;

@Singleton
@Startup
public class DocumentLoader {
  @Inject
  EmbeddingStore<TextSegment> store;

  @Inject
  EmbeddingModel embeddingModel;

  @PostConstruct
  void loadDocument() throws Exception {
    Log.info("📜 Load documents...");
    var content = Files
        .readString(Path.of("src/main/resources/rag/aurora-tech-schedule.md"));

    var doc = Document.document(content);
    EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor
        .builder()
        .embeddingStore(store)
        .embeddingModel(embeddingModel)
        .documentSplitter(recursive(500, 0))
        .build();
    ingestor.ingest(doc);
  }
}
