# Native image not working with quarkus-langchain4j-agentic

## Steps to reproduce the issue
- `quarkus build --native`
- run `./target/quarkus-agentic-reproducer-0.0.1-SNAPSHOT-runner`
- run `curl http://localhost:8080/hello`
- See error:
```
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2026-01-21 15:28:17,794 INFO  [io.quarkus] (main) quarkus-agentic-reproducer 0.0.1-SNAPSHOT native (powered by Quarkus 3.30.6) started in 0.237s. Listening on: http://0.0.0.0:8080
2026-01-21 15:28:17,794 INFO  [io.quarkus] (main) Profile prod activated. 
2026-01-21 15:28:17,794 INFO  [io.quarkus] (main) Installed features: [cdi, langchain4j, langchain4j-openai, qute, rest, rest-client, rest-client-jackson, smallrye-context-propagation, vertx]
2026-01-21 15:28:22,056 ERROR [io.quarkus.vertx.http.runtime.QuarkusErrorHandler] (executor-thread-1) HTTP Request to /hello failed, error id: 732a58a8-96a6-4a05-9e7b-a4d5ee710bff-1: org.graalvm.nativeimage.MissingReflectionRegistrationError: Cannot reflectively access the proxy class inheriting ['fr.wilda.quarkus.agent.ChatAgent','dev.langchain4j.agentic.internal.AgentSpecification','dev.langchain4j.service.memory.ChatMemoryAccess','dev.langchain4j.agentic.internal.AgenticScopeOwner','dev.langchain4j.agentic.agent.ChatMessagesAccess']. To allow this operation, add the following to the 'reflection' section of 'reachability-metadata.json' and rebuild the native image:

  {
    "type": {
      "proxy": [
        "fr.wilda.quarkus.agent.ChatAgent",
        "dev.langchain4j.agentic.internal.AgentSpecification",
        "dev.langchain4j.service.memory.ChatMemoryAccess",
        "dev.langchain4j.agentic.internal.AgenticScopeOwner",
        "dev.langchain4j.agentic.agent.ChatMessagesAccess"
      ]
    }
  }

The 'reachability-metadata.json' file should be located in 'META-INF/native-image/<group-id>/<artifact-id>/' of your project. For further help, see https://www.graalvm.org/latest/reference-manual/native-image/metadata/#reflection
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.reflect.MissingReflectionRegistrationUtils.reportProxyAccess(MissingReflectionRegistrationUtils.java:131)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.reflect.proxy.DynamicProxySupport.getProxyClass(DynamicProxySupport.java:200)
        at java.base@25.0.1/java.lang.reflect.Proxy.getProxyConstructor(Proxy.java:48)
        at java.base@25.0.1/java.lang.reflect.Proxy.newProxyInstance(Proxy.java:924)
        at dev.langchain4j.agentic.agent.AgentBuilder.build(AgentBuilder.java:156)
        at dev.langchain4j.agentic.agent.AgentBuilder.build(AgentBuilder.java:112)
        at dev.langchain4j.agentic.AgenticServices.createAgenticSystem(AgenticServices.java:321)
        at io.quarkiverse.langchain4j.agentic.runtime.AgenticRecorder$1.apply(AgenticRecorder.java:52)
        at io.quarkiverse.langchain4j.agentic.runtime.AgenticRecorder$1.apply(AgenticRecorder.java:36)
        at fr.wilda.quarkus.agent.ChatAgent_73YaKq7N8v012keI1wNoKfVxsuM_Synthetic_Bean.createSynthetic(Unknown Source)
        at fr.wilda.quarkus.agent.ChatAgent_73YaKq7N8v012keI1wNoKfVxsuM_Synthetic_Bean.create(Unknown Source)
        at fr.wilda.quarkus.agent.ChatAgent_73YaKq7N8v012keI1wNoKfVxsuM_Synthetic_Bean.create(Unknown Source)
        at io.quarkus.arc.impl.AbstractSharedContext.createInstanceHandle(AbstractSharedContext.java:119)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:38)
        at io.quarkus.arc.impl.AbstractSharedContext$1.get(AbstractSharedContext.java:35)
        at io.quarkus.arc.generator.Default_jakarta_enterprise_context_ApplicationScoped_ContextInstances.ch3(Unknown Source)
        at io.quarkus.arc.generator.Default_jakarta_enterprise_context_ApplicationScoped_ContextInstances.computeIfAbsent(Unknown Source)
        at io.quarkus.arc.impl.AbstractSharedContext.get(AbstractSharedContext.java:35)
        at io.quarkus.arc.impl.ClientProxies.getApplicationScopedDelegate(ClientProxies.java:23)
        at fr.wilda.quarkus.agent.ChatAgent_73YaKq7N8v012keI1wNoKfVxsuM_Synthetic_ClientProxy.arc$delegate(Unknown Source)
        at fr.wilda.quarkus.agent.ChatAgent_73YaKq7N8v012keI1wNoKfVxsuM_Synthetic_ClientProxy.askAQuestion(Unknown Source)
        at fr.wilda.quarkus.GreetingResource.hello(GreetingResource.java:19)
        at fr.wilda.quarkus.GreetingResource$quarkusrestinvoker$hello_df324e1539083188359af68039a7afafb7b77cdb.invoke(Unknown Source)
        at org.jboss.resteasy.reactive.server.handlers.InvocationHandler.handle(InvocationHandler.java:29)
        at io.quarkus.resteasy.reactive.server.runtime.QuarkusResteasyReactiveRequestContext.invokeHandler(QuarkusResteasyReactiveRequestContext.java:183)
        at org.jboss.resteasy.reactive.common.core.AbstractResteasyReactiveContext.run(AbstractResteasyReactiveContext.java:147)
        at io.quarkus.vertx.core.runtime.VertxCoreRecorder$15.runWith(VertxCoreRecorder.java:645)
        at org.jboss.threads.EnhancedQueueExecutor$Task.doRunWith(EnhancedQueueExecutor.java:2651)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2630)
        at org.jboss.threads.EnhancedQueueExecutor.runThreadBody(EnhancedQueueExecutor.java:1622)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1589)
        at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:11)
        at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:11)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base@25.0.1/java.lang.Thread.runWith(Thread.java:1487)
        at java.base@25.0.1/java.lang.Thread.run(Thread.java:1474)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:832)
        at org.graalvm.nativeimage.builder/com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:808)

^C2026-01-21 15:29:48,734 INFO  [io.quarkus] (Shutdown thread) quarkus-agentic-reproducer stopped in 0.006s
```

## Same code works with non native build
- run `quarkus build`
- run ` java -jar ./target/quarkus-app/quarkus-run.jar`
- run `curl http://localhost:8080/hello`
- See output:
```
The sky looks blue because molecules in Earth’s atmosphere scatter sunlight. Sunlight contains all colors, but shorter (blue‑violet) wavelengths are scattered about 10 times more than longer (red) wavelengths. This scattered blue light reaches our eyes from every direction, making the sky appear blue. (At sunrise/sunset the light passes through more atmosphere, scattering away the blue and letting reds dominate.)
```
