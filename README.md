# micronaut-grpc-client

This project demostrate the issue when Micronaut gRPC Client does not read the channel configuration from properties file.

### Task List

- [ ] Steps to reproduce provided
- [ ] Stacktrace (if present) provided
- [ ] Example that reproduces the problem uploaded to Github
- [ ] Full description of the issue provided (see Expected Behaviour)

### Steps to Reproduce

1. There is a [unit test](https://github.com/UsamaAmjad/micronaut-grpc-client/blob/dcb5a497278738ba84eaefe293e09b388b6f5e5e/src/test/java/com/issue/ClientServiceTest.java#L16) in `ClientServiceTest.java` to reproduce the exception, running that unit test will call the gRPC client and will give you the attached (at the end) exception.

### Expected Behaviour

As per the [Micronaut gRPC Client documentation](https://micronaut-projects.github.io/micronaut-grpc/snapshot/guide/index.html#client) you can define `channel` configuration in properties file such as below.
```propertie
grpc:
  channels:
    clientaddress:
      address: 'http://localhost:8186'
      plaintext: true
      max-retry-attempts: 10
```
Once you define the above configuation then you can use it inside the annotation `@GrpcChannel("clientaddress")` it will elemenate the need of writing server address inside the annotation and will read it from properties file.

### Actual Behaviour

It is not able to read the `clientaddress` configuration from the properties file and throw a runtime exception with a below message (Stacktrace at the end of file).
```
UNAVAILABLE: Unable to resolve host clientaddress
```
However if I define the server address inside annotation `@GrpcChannel("http://localhost:8186")` then it works completely fine.

### Environment Information

- **Operating System**: MacOS v11.2.3
- **Micronaut Version:** 2.5.0
- **JDK Version:** 11

### Example Application

- [micronaut-grpc-client](https://github.com/UsamaAmjad/micronaut-grpc-client)

### Stacktrace
```java
io.grpc.StatusRuntimeException: UNAVAILABLE: Unable to resolve host clientaddress
        at io.grpc.stub.ClientCalls.toStatusRuntimeException(ClientCalls.java:262)
        at io.grpc.stub.ClientCalls.getUnchecked(ClientCalls.java:243)
        at io.grpc.stub.ClientCalls.blockingUnaryCall(ClientCalls.java:156)
        at com.issue.GrpcClientServiceGrpc$GrpcClientServiceBlockingStub.send(GrpcClientServiceGrpc.java:169)
        at com.issue.ClientService.callStub(ClientService.java:15)
        at com.issue.ClientServiceTest.verifyCleintOk(ClientServiceTest.java:17)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.junit.platform.commons.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:688)
        at org.junit.jupiter.engine.execution.MethodInvocation.proceed(MethodInvocation.java:60)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$ValidatingInvocation.proceed(InvocationInterceptorChain.java:131)
        at org.junit.jupiter.api.extension.InvocationInterceptor.interceptTestMethod(InvocationInterceptor.java:117)
        at org.junit.jupiter.engine.execution.ExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(ExecutableInvoker.java:115)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.lambda$invoke$0(ExecutableInvoker.java:105)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.extension.TimeoutExtension.intercept(TimeoutExtension.java:149)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestableMethod(TimeoutExtension.java:140)
        at org.junit.jupiter.engine.extension.TimeoutExtension.interceptTestMethod(TimeoutExtension.java:84)
        at org.junit.jupiter.engine.execution.ExecutableInvoker$ReflectiveInterceptorCall.lambda$ofVoidMethod$0(ExecutableInvoker.java:115)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.lambda$invoke$0(ExecutableInvoker.java:105)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain$InterceptedInvocation.proceed(InvocationInterceptorChain.java:106)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.proceed(InvocationInterceptorChain.java:64)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.chainAndInvoke(InvocationInterceptorChain.java:45)
        at org.junit.jupiter.engine.execution.InvocationInterceptorChain.invoke(InvocationInterceptorChain.java:37)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:104)
        at org.junit.jupiter.engine.execution.ExecutableInvoker.invoke(ExecutableInvoker.java:98)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$invokeTestMethod$6(TestMethodTestDescriptor.java:210)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.invokeTestMethod(TestMethodTestDescriptor.java:206)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:131)
        at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.execute(TestMethodTestDescriptor.java:65)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:139)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:38)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$5(NodeTestTask.java:143)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$7(NodeTestTask.java:129)
        at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:127)
        at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:126)
        at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:84)
        at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:32)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
        at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:51)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:108)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:88)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:54)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:67)
        at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:52)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:96)
        at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:75)
        at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.processAllTestClasses(JUnitPlatformTestClassProcessor.java:99)
        at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.access$000(JUnitPlatformTestClassProcessor.java:79)
        at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor.stop(JUnitPlatformTestClassProcessor.java:75)
        at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.stop(SuiteTestClassProcessor.java:61)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
        at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
        at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
        at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:94)
        at com.sun.proxy.$Proxy2.stop(Unknown Source)
        at org.gradle.api.internal.tasks.testing.worker.TestWorker.stop(TestWorker.java:135)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
        at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
        at org.gradle.internal.remote.internal.hub.MessageHubBackedObjectConnection$DispatchWrapper.dispatch(MessageHubBackedObjectConnection.java:182)
        at org.gradle.internal.remote.internal.hub.MessageHubBackedObjectConnection$DispatchWrapper.dispatch(MessageHubBackedObjectConnection.java:164)
        at org.gradle.internal.remote.internal.hub.MessageHub$Handler.run(MessageHub.java:414)
        at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
        at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:56)
        at java.base/java.lang.Thread.run(Thread.java:834)
        Caused by: java.lang.RuntimeException: java.net.UnknownHostException: clientaddress: nodename nor servname provided, or not known
        at io.grpc.internal.DnsNameResolver.resolveAddresses(DnsNameResolver.java:223)
        at io.grpc.internal.DnsNameResolver.doResolve(DnsNameResolver.java:282)
        at io.grpc.internal.DnsNameResolver$Resolve.run(DnsNameResolver.java:318)
        at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
        at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
        ... 1 more
        Caused by: java.net.UnknownHostException: clientaddress: nodename nor servname provided, or not known
        at java.base/java.net.Inet6AddressImpl.lookupAllHostAddr(Native Method)
        at java.base/java.net.InetAddress$PlatformNameService.lookupAllHostAddr(InetAddress.java:929)
        at java.base/java.net.InetAddress.getAddressesFromNameService(InetAddress.java:1515)
        at java.base/java.net.InetAddress$NameServiceAddresses.get(InetAddress.java:848)
        at java.base/java.net.InetAddress.getAllByName0(InetAddress.java:1505)
        at java.base/java.net.InetAddress.getAllByName(InetAddress.java:1364)
        at java.base/java.net.InetAddress.getAllByName(InetAddress.java:1298)
        at io.grpc.internal.DnsNameResolver$JdkAddressResolver.resolveAddress(DnsNameResolver.java:631)
        at io.grpc.internal.DnsNameResolver.resolveAddresses(DnsNameResolver.java:219)
        ... 5 more
```