package com.vietqr.org.config;

import com.vietqr.org.grpc.client.bankaccountreceive.BankAccountReceiveClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {
    @Value("${grpc.client.port}")
    private int grpcPort;

    @Value("${grpc.client.host}")
    private String grpcHost;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }

    @Bean
    public BankAccountReceiveClient bankAccountReceiveClientBiz(ManagedChannel managedChannel) {
        return new BankAccountReceiveClient(managedChannel);
    }
}
