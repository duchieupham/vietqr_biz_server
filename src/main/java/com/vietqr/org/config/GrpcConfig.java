package com.vietqr.org.config;

import com.vietqr.org.grpc.VietQRBizServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class GrpcConfig {
    @Value("${grpc.server.port}")
    private int grpcPort;

    @Autowired
    private Server grpcServer;

    @Bean
    public Server grpcServer(VietQRBizServiceImpl vietQRBizService) {
        return ServerBuilder
                .forPort(grpcPort)
                .addService(vietQRBizService)
                .build();
    }

    // Khởi chạy gRPC server sau khi Spring Boot start
    @PostConstruct
    public void startGrpcServer() throws Exception {
        if (grpcServer != null) {
            grpcServer.start();
            System.out.println("gRPC server started on port: " + grpcServer.getPort());
            // awaitTermination -> giữ luồng server chạy mãi mãi
            grpcServer.awaitTermination();
        } else {
            System.err.println("Failed to start gRPC server, grpcServer is null");
        }
    }

    // Shutdown gRPC server
    @PreDestroy
    public void stopGrpcServer() {
        if (grpcServer != null) {
            grpcServer.shutdown();
            System.out.println("gRPC server stopped");
        }
    }
}