// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WalletService.proto

package betpawa.test.demo.grpc;

/**
 * Protobuf type {@code betpawa.test.demo.grpc.Sum}
 */
public  final class Sum extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:betpawa.test.demo.grpc.Sum)
    SumOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Sum.newBuilder() to construct.
  private Sum(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Sum() {
    amount_ = "";
    currency_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Sum();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private Sum(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            amount_ = s;
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            currency_ = rawValue;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return betpawa.test.demo.grpc.WalletOuter.internal_static_betpawa_test_demo_grpc_Sum_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return betpawa.test.demo.grpc.WalletOuter.internal_static_betpawa_test_demo_grpc_Sum_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            betpawa.test.demo.grpc.Sum.class, betpawa.test.demo.grpc.Sum.Builder.class);
  }

  /**
   * Protobuf enum {@code betpawa.test.demo.grpc.Sum.Currency}
   */
  public enum Currency
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EUR = 0;</code>
     */
    EUR(0),
    /**
     * <code>GBP = 1;</code>
     */
    GBP(1),
    /**
     * <code>USD = 2;</code>
     */
    USD(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>EUR = 0;</code>
     */
    public static final int EUR_VALUE = 0;
    /**
     * <code>GBP = 1;</code>
     */
    public static final int GBP_VALUE = 1;
    /**
     * <code>USD = 2;</code>
     */
    public static final int USD_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Currency valueOf(int value) {
      return forNumber(value);
    }

    public static Currency forNumber(int value) {
      switch (value) {
        case 0: return EUR;
        case 1: return GBP;
        case 2: return USD;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Currency>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Currency> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Currency>() {
            public Currency findValueByNumber(int number) {
              return Currency.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return betpawa.test.demo.grpc.Sum.getDescriptor().getEnumTypes().get(0);
    }

    private static final Currency[] VALUES = values();

    public static Currency valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Currency(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:betpawa.test.demo.grpc.Sum.Currency)
  }

  public static final int AMOUNT_FIELD_NUMBER = 1;
  private volatile java.lang.Object amount_;
  /**
   * <code>string amount = 1;</code>
   */
  public java.lang.String getAmount() {
    java.lang.Object ref = amount_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      amount_ = s;
      return s;
    }
  }
  /**
   * <code>string amount = 1;</code>
   */
  public com.google.protobuf.ByteString
      getAmountBytes() {
    java.lang.Object ref = amount_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      amount_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CURRENCY_FIELD_NUMBER = 2;
  private int currency_;
  /**
   * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
   */
  public int getCurrencyValue() {
    return currency_;
  }
  /**
   * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
   */
  public betpawa.test.demo.grpc.Sum.Currency getCurrency() {
    @SuppressWarnings("deprecation")
    betpawa.test.demo.grpc.Sum.Currency result = betpawa.test.demo.grpc.Sum.Currency.valueOf(currency_);
    return result == null ? betpawa.test.demo.grpc.Sum.Currency.UNRECOGNIZED : result;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getAmountBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, amount_);
    }
    if (currency_ != betpawa.test.demo.grpc.Sum.Currency.EUR.getNumber()) {
      output.writeEnum(2, currency_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getAmountBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, amount_);
    }
    if (currency_ != betpawa.test.demo.grpc.Sum.Currency.EUR.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, currency_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof betpawa.test.demo.grpc.Sum)) {
      return super.equals(obj);
    }
    betpawa.test.demo.grpc.Sum other = (betpawa.test.demo.grpc.Sum) obj;

    if (!getAmount()
        .equals(other.getAmount())) return false;
    if (currency_ != other.currency_) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + getAmount().hashCode();
    hash = (37 * hash) + CURRENCY_FIELD_NUMBER;
    hash = (53 * hash) + currency_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static betpawa.test.demo.grpc.Sum parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static betpawa.test.demo.grpc.Sum parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static betpawa.test.demo.grpc.Sum parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static betpawa.test.demo.grpc.Sum parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(betpawa.test.demo.grpc.Sum prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code betpawa.test.demo.grpc.Sum}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:betpawa.test.demo.grpc.Sum)
      betpawa.test.demo.grpc.SumOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return betpawa.test.demo.grpc.WalletOuter.internal_static_betpawa_test_demo_grpc_Sum_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return betpawa.test.demo.grpc.WalletOuter.internal_static_betpawa_test_demo_grpc_Sum_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              betpawa.test.demo.grpc.Sum.class, betpawa.test.demo.grpc.Sum.Builder.class);
    }

    // Construct using betpawa.test.demo.grpc.Sum.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      amount_ = "";

      currency_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return betpawa.test.demo.grpc.WalletOuter.internal_static_betpawa_test_demo_grpc_Sum_descriptor;
    }

    @java.lang.Override
    public betpawa.test.demo.grpc.Sum getDefaultInstanceForType() {
      return betpawa.test.demo.grpc.Sum.getDefaultInstance();
    }

    @java.lang.Override
    public betpawa.test.demo.grpc.Sum build() {
      betpawa.test.demo.grpc.Sum result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public betpawa.test.demo.grpc.Sum buildPartial() {
      betpawa.test.demo.grpc.Sum result = new betpawa.test.demo.grpc.Sum(this);
      result.amount_ = amount_;
      result.currency_ = currency_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof betpawa.test.demo.grpc.Sum) {
        return mergeFrom((betpawa.test.demo.grpc.Sum)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(betpawa.test.demo.grpc.Sum other) {
      if (other == betpawa.test.demo.grpc.Sum.getDefaultInstance()) return this;
      if (!other.getAmount().isEmpty()) {
        amount_ = other.amount_;
        onChanged();
      }
      if (other.currency_ != 0) {
        setCurrencyValue(other.getCurrencyValue());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      betpawa.test.demo.grpc.Sum parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (betpawa.test.demo.grpc.Sum) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object amount_ = "";
    /**
     * <code>string amount = 1;</code>
     */
    public java.lang.String getAmount() {
      java.lang.Object ref = amount_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        amount_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string amount = 1;</code>
     */
    public com.google.protobuf.ByteString
        getAmountBytes() {
      java.lang.Object ref = amount_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        amount_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string amount = 1;</code>
     */
    public Builder setAmount(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      amount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string amount = 1;</code>
     */
    public Builder clearAmount() {
      
      amount_ = getDefaultInstance().getAmount();
      onChanged();
      return this;
    }
    /**
     * <code>string amount = 1;</code>
     */
    public Builder setAmountBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      amount_ = value;
      onChanged();
      return this;
    }

    private int currency_ = 0;
    /**
     * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
     */
    public int getCurrencyValue() {
      return currency_;
    }
    /**
     * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
     */
    public Builder setCurrencyValue(int value) {
      currency_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
     */
    public betpawa.test.demo.grpc.Sum.Currency getCurrency() {
      @SuppressWarnings("deprecation")
      betpawa.test.demo.grpc.Sum.Currency result = betpawa.test.demo.grpc.Sum.Currency.valueOf(currency_);
      return result == null ? betpawa.test.demo.grpc.Sum.Currency.UNRECOGNIZED : result;
    }
    /**
     * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
     */
    public Builder setCurrency(betpawa.test.demo.grpc.Sum.Currency value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      currency_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.betpawa.test.demo.grpc.Sum.Currency currency = 2;</code>
     */
    public Builder clearCurrency() {
      
      currency_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:betpawa.test.demo.grpc.Sum)
  }

  // @@protoc_insertion_point(class_scope:betpawa.test.demo.grpc.Sum)
  private static final betpawa.test.demo.grpc.Sum DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new betpawa.test.demo.grpc.Sum();
  }

  public static betpawa.test.demo.grpc.Sum getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Sum>
      PARSER = new com.google.protobuf.AbstractParser<Sum>() {
    @java.lang.Override
    public Sum parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Sum(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<Sum> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Sum> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public betpawa.test.demo.grpc.Sum getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

