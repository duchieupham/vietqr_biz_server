package com.vietqr.org.grpc.client.qrgenerator;

import com.example.grpc.qrgenerator.QRGeneratorServiceGrpc;
import com.example.grpc.qrgenerator.VietQR;
import com.example.grpc.qrgenerator.RequestStaticQR;
import com.example.grpc.qrgenerator.RequestDynamicQR;
import com.example.grpc.qrgenerator.RequestSemiDynamicQR;
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

    private QRGeneratorDTO mapQR(VietQR vietQR) {
        QRGeneratorDTO result = new QRGeneratorDTO();

        result.setBankCode(vietQR.getBankCode());
        result.setBankName(vietQR.getBankName());
        result.setBankAccount(vietQR.getBankAccount());
        result.setUserBankName(vietQR.getUserBankName());
        result.setAmount(vietQR.getAmount());
        result.setContent(vietQR.getContent());
        result.setQrCode(vietQR.getQrCode());
        result.setImgId(vietQR.getImgId());
        result.setExisting(vietQR.getExisting());
        result.setTransactionId(vietQR.getTransactionId());
        result.setTransactionRefId(vietQR.getTransactionRefId());
        result.setQrLink(vietQR.getQrLink());
        result.setTerminalCode(vietQR.getTerminalCode());
        result.setSubTerminalCode(vietQR.getSubTerminalCode());
        result.setServiceCode(vietQR.getServiceCode());
        result.setOrderId(vietQR.getOrderId());

        return result;
    }

    public QRGeneratorDTO generateStaticQR(StaticQRCreateDTO dto) throws InterruptedException {
        RequestStaticQR request = RequestStaticQR.newBuilder()
                .setAmount(dto.getAmount())
                .setBankAccount(dto.getBankAccount())
                .setContent(dto.getContent())
                .setBankCode(dto.getBankCode())
                .setTerminalCode(dto.getTerminalCode())
                .setCustomerBankCode(dto.getCustomerBankCode())
                .setTransType(dto.getTransType())
                .setToken(dto.getToken())
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        QRGeneratorDTO result = new QRGeneratorDTO();

        qrGeneratorServiceStub.generateStaticQR(request, new StreamObserver<VietQR>() {
            @Override
            public void onNext(VietQR response) {
                result.clone(mapQR(response));
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

    public QRGeneratorDTO generateDynamicQR(DynamicQRCreateDTO dto) throws InterruptedException {
        RequestDynamicQR request = RequestDynamicQR.newBuilder()
                .setAmount(dto.getAmount())
                .setBankAccount(dto.getBankAccount())
                .setContent(dto.getContent())
                .setBankCode(dto.getBankCode())
                .setUserBankName(dto.getUserBankName())
                .setTerminalCode(dto.getTerminalCode())
                .setSubTerminalCode(dto.getSubTerminalCode())
                .setCustomerBankAccount(dto.getCustomerBankAccount())
                .setCustomerName(dto.getCustomerName())
                .setCustomerBankCode(dto.getCustomerBankCode())
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
        QRGeneratorDTO result = new QRGeneratorDTO();

        qrGeneratorServiceStub.generateDynamicQR(request, new StreamObserver<VietQR>() {
            @Override
            public void onNext(VietQR response) {
                result.clone(mapQR(response));
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

    public QRGeneratorDTO generateSemiDynamicQR(SemiDynamicQRCreateDTO dto) throws InterruptedException {
        RequestSemiDynamicQR request = RequestSemiDynamicQR.newBuilder()
                .setAmount(dto.getAmount())
                .setBankAccount(dto.getBankAccount())
                .setContent(dto.getContent())
                .setBankCode(dto.getBankCode())
                .setTerminalCode(dto.getTerminalCode())
                .setCustomerBankCode(dto.getCustomerBankCode())
                .setTransType(dto.getTransType())
                .setServiceCode(dto.getServiceCode())
                .setToken(dto.getToken())
                .build();
        CountDownLatch latch = new CountDownLatch(1);
        QRGeneratorDTO result = new QRGeneratorDTO();

        qrGeneratorServiceStub.generateSemiDynamicQR(request, new StreamObserver<VietQR>() {
            @Override
            public void onNext(VietQR response) {
                result.clone(mapQR(response));
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
