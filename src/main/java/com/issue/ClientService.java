package com.issue;

import javax.inject.Inject;

public class ClientService {

    private final GrpcClientServiceGrpc.GrpcClientServiceBlockingStub stub;

    @Inject
    public ClientService(GrpcClientServiceGrpc.GrpcClientServiceBlockingStub stub) {
        this.stub = stub;
    }

    public String callStub() {
        final GrpcClientReply reply = stub.send(GrpcClientRequest.newBuilder().setName("Hello World").build());
        return reply.getMessage();
    }
}
