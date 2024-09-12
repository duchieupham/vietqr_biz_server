package com.vietqr.org.grpc;

import com.example.vietqr_biz.MessageRequest;
import com.example.vietqr_biz.MessageResponse;
import com.example.vietqr_biz.VietQRBizServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;

@Service
public class VietQRBizServiceImpl extends VietQRBizServiceGrpc.VietQRBizServiceImplBase {
    @Override
    public void sendMessage(MessageRequest request, StreamObserver<MessageResponse> responseObserver) {
        System.out.println("Received message from vietqr_server: " + request.getMessage());
        MessageResponse response = MessageResponse.newBuilder()
                .setReply("Hello vietqr_server")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}