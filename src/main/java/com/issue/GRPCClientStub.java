package com.issue;

import io.grpc.ManagedChannel;
import io.micronaut.context.annotation.Factory;
import io.micronaut.grpc.annotation.GrpcChannel;

import javax.inject.Singleton;

@Factory
public class GRPCClientStub {
    @Singleton
    GrpcClientServiceGrpc.GrpcClientServiceBlockingStub blockingStub(@GrpcChannel("clientaddress") ManagedChannel channel) {
        return GrpcClientServiceGrpc.newBlockingStub(channel);
    }
}