package com.vietqr.org.grpc.client.bankaccountreceive;

import com.example.grpc.bankaccountreceive.*;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class BankAccountReceiveClient {
    private static final Logger logger = Logger.getLogger(BankAccountReceiveClient.class);
    private final String LOG_ERROR = "Failed at BankAccountReceiveClient: ";
    private final BankAccountReceiveServiceGrpc.BankAccountReceiveServiceStub bankAccountReceiveServiceStub;

    public BankAccountReceiveClient(ManagedChannel channel) {
        bankAccountReceiveServiceStub = BankAccountReceiveServiceGrpc.newStub(channel);
    }

    public BankAccountReceiveDTO getBankAccountReceiveByBankId(String bankId) throws InterruptedException {
        RequestByBankId request = RequestByBankId.newBuilder().setBankId(bankId).build();
        CountDownLatch latch = new CountDownLatch(1);
        BankAccountReceiveDTO result = new BankAccountReceiveDTO();

        bankAccountReceiveServiceStub.getBankAccountReceiveByBankId(request, new StreamObserver<BankAccountReceive>() {
            @Override
            public void onNext(BankAccountReceive response) {
                result.setBankAccount(response.getBankAccount());
                result.setBankId(response.getId());
                result.setBankTypeId(response.getBankTypeId());
                result.setBankShortName(response.getBankShortName());
                result.setIsSync(response.getIsSync());
                result.setUserBankName(response.getUserBankName());
                result.setUserId(response.getUserId());
            }

            @Override
            public void onError(Throwable t) {
                logger.error(LOG_ERROR + "getBankAccountReceiveByBankId: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("getBankAccountReceiveByBankId: Stream completed");
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.MINUTES);

        return result;
    }

    public List<BankAccountReceiveDTO> getBankAccountReceiveByUserId(String userId) throws InterruptedException {
        RequestByUserId request = RequestByUserId.newBuilder().setUserId(userId).build();
        CountDownLatch latch = new CountDownLatch(1);
        List<BankAccountReceiveDTO> result = new ArrayList<>();

        bankAccountReceiveServiceStub.getBankAccountReceiveByUserId(request, new StreamObserver<BankAccountReceiveList>() {
            @Override
            public void onNext(BankAccountReceiveList response) {
                result.addAll(response.getBankAccountReceivesList()
                        .stream()
                        .map(item -> {
                            BankAccountReceiveDTO dto = new BankAccountReceiveDTO();
                            dto.setBankAccount(item.getBankAccount());
                            dto.setBankId(item.getId());
                            dto.setBankTypeId(item.getBankTypeId());
                            dto.setBankShortName(item.getBankShortName());
                            dto.setIsSync(item.getIsSync());
                            dto.setUserBankName(item.getUserBankName());
                            dto.setUserId(item.getUserId());
                            return dto;
                        })
                        .collect(Collectors.toList())
                );
            }

            @Override
            public void onError(Throwable t) {
                logger.error(LOG_ERROR + "getBankAccountReceiveByUserId: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("getBankAccountReceiveByUserId: Stream completed");
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.MINUTES);

        return result;
    }
}
