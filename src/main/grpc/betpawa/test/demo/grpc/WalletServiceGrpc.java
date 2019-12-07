package betpawa.test.demo.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.23.0)",
    comments = "Source: WalletService.proto")
public final class WalletServiceGrpc {

  private WalletServiceGrpc() {}

  public static final String SERVICE_NAME = "betpawa.test.demo.grpc.WalletService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<betpawa.test.demo.grpc.PaymentRequest,
      betpawa.test.demo.grpc.PaymentResponse> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = betpawa.test.demo.grpc.PaymentRequest.class,
      responseType = betpawa.test.demo.grpc.PaymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<betpawa.test.demo.grpc.PaymentRequest,
      betpawa.test.demo.grpc.PaymentResponse> getDepositMethod() {
    io.grpc.MethodDescriptor<betpawa.test.demo.grpc.PaymentRequest, betpawa.test.demo.grpc.PaymentResponse> getDepositMethod;
    if ((getDepositMethod = WalletServiceGrpc.getDepositMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getDepositMethod = WalletServiceGrpc.getDepositMethod) == null) {
          WalletServiceGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<betpawa.test.demo.grpc.PaymentRequest, betpawa.test.demo.grpc.PaymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  betpawa.test.demo.grpc.PaymentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  betpawa.test.demo.grpc.PaymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<betpawa.test.demo.grpc.PaymentRequest,
      betpawa.test.demo.grpc.PaymentResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = betpawa.test.demo.grpc.PaymentRequest.class,
      responseType = betpawa.test.demo.grpc.PaymentResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<betpawa.test.demo.grpc.PaymentRequest,
      betpawa.test.demo.grpc.PaymentResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<betpawa.test.demo.grpc.PaymentRequest, betpawa.test.demo.grpc.PaymentResponse> getWithdrawMethod;
    if ((getWithdrawMethod = WalletServiceGrpc.getWithdrawMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getWithdrawMethod = WalletServiceGrpc.getWithdrawMethod) == null) {
          WalletServiceGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<betpawa.test.demo.grpc.PaymentRequest, betpawa.test.demo.grpc.PaymentResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  betpawa.test.demo.grpc.PaymentRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  betpawa.test.demo.grpc.PaymentResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<betpawa.test.demo.grpc.BalanceRequest,
      betpawa.test.demo.grpc.BalanceResponse> getGetBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBalance",
      requestType = betpawa.test.demo.grpc.BalanceRequest.class,
      responseType = betpawa.test.demo.grpc.BalanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<betpawa.test.demo.grpc.BalanceRequest,
      betpawa.test.demo.grpc.BalanceResponse> getGetBalanceMethod() {
    io.grpc.MethodDescriptor<betpawa.test.demo.grpc.BalanceRequest, betpawa.test.demo.grpc.BalanceResponse> getGetBalanceMethod;
    if ((getGetBalanceMethod = WalletServiceGrpc.getGetBalanceMethod) == null) {
      synchronized (WalletServiceGrpc.class) {
        if ((getGetBalanceMethod = WalletServiceGrpc.getGetBalanceMethod) == null) {
          WalletServiceGrpc.getGetBalanceMethod = getGetBalanceMethod =
              io.grpc.MethodDescriptor.<betpawa.test.demo.grpc.BalanceRequest, betpawa.test.demo.grpc.BalanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  betpawa.test.demo.grpc.BalanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  betpawa.test.demo.grpc.BalanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WalletServiceMethodDescriptorSupplier("GetBalance"))
              .build();
        }
      }
    }
    return getGetBalanceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WalletServiceStub newStub(io.grpc.Channel channel) {
    return new WalletServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WalletServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new WalletServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WalletServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new WalletServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class WalletServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void deposit(betpawa.test.demo.grpc.PaymentRequest request,
        io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.PaymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    /**
     */
    public void withdraw(betpawa.test.demo.grpc.PaymentRequest request,
        io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.PaymentResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     */
    public void getBalance(betpawa.test.demo.grpc.BalanceRequest request,
        io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.BalanceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBalanceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                betpawa.test.demo.grpc.PaymentRequest,
                betpawa.test.demo.grpc.PaymentResponse>(
                  this, METHODID_DEPOSIT)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                betpawa.test.demo.grpc.PaymentRequest,
                betpawa.test.demo.grpc.PaymentResponse>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getGetBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                betpawa.test.demo.grpc.BalanceRequest,
                betpawa.test.demo.grpc.BalanceResponse>(
                  this, METHODID_GET_BALANCE)))
          .build();
    }
  }

  /**
   */
  public static final class WalletServiceStub extends io.grpc.stub.AbstractStub<WalletServiceStub> {
    private WalletServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletServiceStub(channel, callOptions);
    }

    /**
     */
    public void deposit(betpawa.test.demo.grpc.PaymentRequest request,
        io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.PaymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdraw(betpawa.test.demo.grpc.PaymentRequest request,
        io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.PaymentResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBalance(betpawa.test.demo.grpc.BalanceRequest request,
        io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.BalanceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class WalletServiceBlockingStub extends io.grpc.stub.AbstractStub<WalletServiceBlockingStub> {
    private WalletServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public betpawa.test.demo.grpc.PaymentResponse deposit(betpawa.test.demo.grpc.PaymentRequest request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }

    /**
     */
    public betpawa.test.demo.grpc.PaymentResponse withdraw(betpawa.test.demo.grpc.PaymentRequest request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public betpawa.test.demo.grpc.BalanceResponse getBalance(betpawa.test.demo.grpc.BalanceRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBalanceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class WalletServiceFutureStub extends io.grpc.stub.AbstractStub<WalletServiceFutureStub> {
    private WalletServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private WalletServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WalletServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new WalletServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<betpawa.test.demo.grpc.PaymentResponse> deposit(
        betpawa.test.demo.grpc.PaymentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<betpawa.test.demo.grpc.PaymentResponse> withdraw(
        betpawa.test.demo.grpc.PaymentRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<betpawa.test.demo.grpc.BalanceResponse> getBalance(
        betpawa.test.demo.grpc.BalanceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBalanceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DEPOSIT = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_GET_BALANCE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final WalletServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(WalletServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_DEPOSIT:
          serviceImpl.deposit((betpawa.test.demo.grpc.PaymentRequest) request,
              (io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.PaymentResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((betpawa.test.demo.grpc.PaymentRequest) request,
              (io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.PaymentResponse>) responseObserver);
          break;
        case METHODID_GET_BALANCE:
          serviceImpl.getBalance((betpawa.test.demo.grpc.BalanceRequest) request,
              (io.grpc.stub.StreamObserver<betpawa.test.demo.grpc.BalanceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class WalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WalletServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return betpawa.test.demo.grpc.WalletServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WalletService");
    }
  }

  private static final class WalletServiceFileDescriptorSupplier
      extends WalletServiceBaseDescriptorSupplier {
    WalletServiceFileDescriptorSupplier() {}
  }

  private static final class WalletServiceMethodDescriptorSupplier
      extends WalletServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    WalletServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (WalletServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WalletServiceFileDescriptorSupplier())
              .addMethod(getDepositMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getGetBalanceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
