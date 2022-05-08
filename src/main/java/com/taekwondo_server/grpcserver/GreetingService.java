package com.taekwondo_server.grpcserver;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import proto_service.GreeterGrpc;
import proto_service.RequestMessage;
import proto_service.ResponseMessage;

@GRpcService
public class GreetingService extends GreeterGrpc.GreeterImplBase{
  /**
   * Server side method to greet user on request.
   * @param req
   * @param responseObserver
   */
  @Override
  public void sayHello(RequestMessage req, StreamObserver<ResponseMessage> responseObserver) {
    ResponseMessage reply = ResponseMessage.newBuilder()
            .setMessage("Hello, " + req.getName() + "!").build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
