package com.vietqr.org.grpc.client.qrgenerator;

import com.example.grpc.qrgenerator.QRGeneratorServiceGrpc;
import com.example.grpc.qrgenerator.RequestStaticQR;
import com.example.grpc.qrgenerator.RequestDynamicQR;
import com.example.grpc.qrgenerator.RequestSemiDynamicQR;
import com.example.grpc.qrgenerator.StaticQR;
import com.example.grpc.qrgenerator.DynamicQR;
import com.example.grpc.qrgenerator.SemiDynamicQR;
import io.grpc.ManagedChannel;
import io.grpc.stub.StreamObserver;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class QRGeneratorClient {
    private static final Logger logger = Logger.getLogger(QRGeneratorClient.class);
    private final String LOG_ERROR = "Failed at QRGeneratorClient: ";
    private final QRGeneratorServiceGrpc.QRGeneratorServiceStub qrGeneratorServiceStub;

    public QRGeneratorClient(ManagedChannel channel) {
        this.qrGeneratorServiceStub = QRGeneratorServiceGrpc.newStub(channel);
    }

    public StaticQRDTO generateStaticQR(RequestStaticQRDTO dto) throws InterruptedException {
        RequestStaticQR request = RequestStaticQR.newBuilder()
                .setAmount(dto.getAmount())
                .setBankAccount(dto.getBankAccount())
                .setContent(dto.getContent())
                .setBankCode(dto.getBankCode())
                .setTerminalCode(dto.getTerminalCode())
                .setTransType(dto.getTransType())
                .setToken(dto.getToken())
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        StaticQRDTO result = new StaticQRDTO();

        qrGeneratorServiceStub.generateStaticQR(request, new StreamObserver<StaticQR>() {
            @Override
            public void onNext(StaticQR response) {
                result.setBankCode(response.getBankCode());
                result.setBankName(response.getBankName());
                result.setBankAccount(response.getBankAccount());
                result.setUserBankName(response.getUserBankName());
                result.setAmount(response.getAmount());
                result.setContent(response.getContent());
                result.setQrCode(response.getQrCode());
                result.setImgId(response.getImgId());
                result.setTraceTransfer(response.getTraceTransfer());
            }

            @Override
            public void onError(Throwable t) {
                logger.error(LOG_ERROR + "generateStaticQR: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("generateStaticQR: Stream completed");
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.MINUTES);

        return result;
    }

    public DynamicQRDTO generateDynamicQR(RequestDynamicQRDTO dto) throws InterruptedException {
        RequestDynamicQR request = RequestDynamicQR.newBuilder()
                .setAmount(dto.getAmount())
                .setBankAccount(dto.getBankAccount())
                .setContent(dto.getContent())
                .setBankCode(dto.getBankCode())
                .setUserBankName(dto.getUserBankName())
                .setTerminalCode(dto.getTerminalCode())
                .setSubTerminalCode(dto.getSubTerminalCode())
                .setTransType(dto.getTransType())
                .setServiceCode(dto.getServiceCode())
                .setNote(dto.getNote())
                .setUrlLink(dto.getUrlLink())
                .setOrderId(dto.getOrderId())
                .setSign(dto.getSign())
                .setReconciliation(dto.getReconciliation())
                .setToken(dto.getToken())
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        DynamicQRDTO result = new DynamicQRDTO();

        qrGeneratorServiceStub.generateDynamicQR(request, new StreamObserver<DynamicQR>() {
            @Override
            public void onNext(DynamicQR response) {
                result.setBankCode(response.getBankCode());
                result.setBankName(response.getBankName());
                result.setBankAccount(response.getBankAccount());
                result.setUserBankName(response.getUserBankName());
                result.setAmount(response.getAmount());
                result.setContent(response.getContent());
                result.setQrCode(response.getQrCode());
                result.setImgId(response.getImgId());
                result.setExisting(response.getExisting());
                result.setTransactionRefId(response.getTransactionRefId());
                result.setQrLink(response.getQrLink());
                result.setServiceCode(response.getServiceCode());
                result.setOrderId(response.getOrderId());
            }

            @Override
            public void onError(Throwable t) {
                logger.error(LOG_ERROR + "generateDynamicQR: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("generateDynamicQR: Stream completed");
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.MINUTES);

        return result;
    }

    public SemiDynamicQRDTO generateSemiDynamicQR(RequestSemiDynamicQRDTO dto) throws InterruptedException {
        RequestSemiDynamicQR request = RequestSemiDynamicQR.newBuilder()
                .setAmount(dto.getAmount())
                .setBankAccount(dto.getBankAccount())
                .setContent(dto.getContent())
                .setBankCode(dto.getBankCode())
                .setTerminalCode(dto.getTerminalCode())
                .setTransType(dto.getTransType())
                .setServiceCode(dto.getServiceCode())
                .setToken(dto.getToken())
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        SemiDynamicQRDTO result = new SemiDynamicQRDTO();

        qrGeneratorServiceStub.generateSemiDynamicQR(request, new StreamObserver<SemiDynamicQR>() {
            @Override
            public void onNext(SemiDynamicQR response) {
                result.setBankCode(response.getBankCode());
                result.setBankName(response.getBankName());
                result.setBankAccount(response.getBankAccount());
                result.setUserBankName(response.getUserBankName());
                result.setAmount(response.getAmount());
                result.setContent(response.getContent());
                result.setQrCode(response.getQrCode());
                result.setImgId(response.getImgId());
                result.setTraceTransfer(response.getTraceTransfer());
            }

            @Override
            public void onError(Throwable t) {
                logger.error(LOG_ERROR + "generateSemiDynamicQR: ", t);
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("generateSemiDynamicQR: Stream completed");
                latch.countDown();
            }
        });
        latch.await(1, TimeUnit.MINUTES);

        return result;
    }
}
