package com.taekwondo_server.grpcserver;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import proto_service.GreeterGrpc;
import proto_service.RequestMessage;
import proto_service.ResponseMessage;


import java.util.concurrent.TimeUnit;

public class GreetingClient {

  public static void main(String[] args) throws Exception {
    final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5555)
            .usePlaintext()
            .build();

    RequestMessage request = RequestMessage.newBuilder().setName("world").build();
    ResponseMessage response;
    try {
      response = GreeterGrpc.newBlockingStub(channel).sayHello(request);
      System.out.println(response.getMessage());
    } catch (StatusRuntimeException e) {
      return;
    } finally {
      channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
    }
  }
}
