package com.vietqr.org.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class GrpcServerConfig {

    // Inject giá trị cổng từ application.properties
    @Value("${grpc.server.port}")
    private int grpcPort;

    private Server grpcServer;
    @Bean
    public Server grpcServer(VietQRBizServiceImpl vietQRBizService) {
        // Tạo và trả về gRPC Server
        return ServerBuilder.forPort(grpcPort)
                .addService(vietQRBizService) // Đăng ký service của bạn vào server
                .build();
    }
    // Khởi chạy gRPC server sau khi Spring Boot khởi động
    @PostConstruct
    public void startGrpcServer() throws Exception {
        if (grpcServer != null) {
            grpcServer.start();
            System.out.println("gRPC server started on port: " + grpcServer.getPort());
            // Bạn có thể không cần awaitTermination nếu không muốn giữ luồng server chạy mãi mãi
            grpcServer.awaitTermination();
        } else {
            System.err.println("Failed to start gRPC server, grpcServer is null");
        }
    }
}