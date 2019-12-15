package betpawa.test.demo.service;

import betpawa.test.demo.UserRepository;
import betpawa.test.demo.grpc.*;
import betpawa.test.demo.model.Currency;
import betpawa.test.demo.model.User;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@GRpcService
public class WalletService extends WalletServiceGrpc.WalletServiceImplBase
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void deposit(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver)
    {
        Currency requestCurrency;
        BigDecimal requestAmount;
        try
        {
            requestCurrency = Currency.valueOf(request.getCurrency().name());
            requestAmount = new BigDecimal(request.getAmount());
        }
        catch (Exception e)
        {
            responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("Unknown currency").withCause(e)));
            return;
        }

        Optional<User> user = userRepository.findById(request.getUserId());

        user.map(User::getWallets).flatMap(x -> x.stream()
                .filter(it -> it.getCurrency() == requestCurrency)
                .findAny())
                .ifPresent(x -> x.setAmount(x.getAmount().add(requestAmount)));
        user.ifPresent(x -> userRepository.save(x));

        responseObserver.onCompleted();
    }

    @Override
    public void withdraw(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver)
    {
        Currency requestCurrency;
        BigDecimal requestAmount;
        try
        {
            requestCurrency = Currency.valueOf(request.getCurrency().name());
            requestAmount = new BigDecimal(request.getAmount());
        }
        catch (Exception e)
        {
            responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("Unknown currency").withCause(e)));
            return;
        }

        Optional<User> user = userRepository.findById(request.getUserId());

        Optional<BigDecimal> leftover = user.map(User::getWallets).flatMap(x -> x.stream()
                .filter(it -> it.getCurrency() == requestCurrency)
                .findAny())
                .map(wallet -> wallet.getAmount().subtract(requestAmount));

        if (leftover.isPresent() && leftover.get().compareTo(BigDecimal.ZERO) < 0)
        {
            responseObserver.onError(new StatusRuntimeException(Status.ABORTED.withDescription("insufficient_funds")));
        }
        else
        {
            //wallet.setAmount(leftover);
            userRepository.save(user.get());
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getBalance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver)
    {
        userRepository.findById(request.getUserId()).map(x -> x.getWallets().stream())
                .ifPresent(wallets -> responseObserver.onNext(BalanceResponse.newBuilder().addAllAmount(wallets.map(balance ->
                        PaymentRequest.newBuilder()
                                .setAmount(balance.getAmount().toPlainString())
                                .setCurrency(PaymentRequest.Currency.valueOf(balance.getCurrency().name()))
                                .build())
                        .collect(Collectors.toList()))
                        .build())
                );
        responseObserver.onCompleted();
    }
}
