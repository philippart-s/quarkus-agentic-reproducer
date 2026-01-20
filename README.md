# How to reproduce

## RAG not working: 
- `quarkus dev`
- `curl http://localhost:8080/hello`
- result: `I cannot find this information in the provided documents.`

## RAG working:
- comment line 43 in [RagAgent.java](./src/main/java/fr/wilda/quarkus/agent/RagAgent.java)
- uncomment line 14 in [DocumentRetriever.java](./src/main/java/fr/wilda/quarkus/tool/DocumentRetriever.java)
- `curl http://localhost:8080/hello`
- result: 
```
**AuroraTech Summit 2027 – Schedule (September 14,2027)**  

| Time   | Event | Speaker | Title / Description |
|--------|-------|---------|----------------------|
| 09:00  | Opening Keynote | Dr.Elena Vorvik | *Software That Ages Gracefully* – Architectural decisions that are never documented; long‑term impact of undocumented conventions; techniques for making architectural intent explicit |
| 13:00  | Session 2 | Amina Solberg | *Governing AI Without Freezing Innovation* – Lightweight governance models for internal AI tools; auditability vs. developer velocity; case study of the “Glass Ledger” decision‑log framework |
| 15:00  | Session 3 | (Speaker not listed) | (Details of the session are not provided in the documents) |

*Reference: AuroraTech Summit 2027 schedule information.*
```
