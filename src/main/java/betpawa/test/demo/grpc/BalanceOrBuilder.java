// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WalletService.proto

package betpawa.test.demo.grpc;

public interface BalanceOrBuilder extends
    // @@protoc_insertion_point(interface_extends:betpawa.test.demo.grpc.Balance)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .betpawa.test.demo.grpc.Sum amount = 1;</code>
   */
  java.util.List<betpawa.test.demo.grpc.Sum> 
      getAmountList();
  /**
   * <code>repeated .betpawa.test.demo.grpc.Sum amount = 1;</code>
   */
  betpawa.test.demo.grpc.Sum getAmount(int index);
  /**
   * <code>repeated .betpawa.test.demo.grpc.Sum amount = 1;</code>
   */
  int getAmountCount();
  /**
   * <code>repeated .betpawa.test.demo.grpc.Sum amount = 1;</code>
   */
  java.util.List<? extends betpawa.test.demo.grpc.SumOrBuilder> 
      getAmountOrBuilderList();
  /**
   * <code>repeated .betpawa.test.demo.grpc.Sum amount = 1;</code>
   */
  betpawa.test.demo.grpc.SumOrBuilder getAmountOrBuilder(
      int index);
}
