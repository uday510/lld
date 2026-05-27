package com.app.patterns.behavioral.strategy;

public class PaymentResponse {
    private final boolean success;
    private final String transactionId;
    private final String errorCode;
    private final String errorMessage;

    private PaymentResponse(
            boolean success,
            String txnId,
            String code,
            String msg
    ) {
        this.success = success;
        this.transactionId = txnId;
        this.errorCode = code;
        this.errorMessage = msg;
    }

    public static PaymentResponse success(String transactionId) {
        return new PaymentResponse(true, transactionId, null, null);
    }

    public static PaymentResponse failure(String code, String msg) {
        return new PaymentResponse(false, null, code, msg);
    }

    public boolean isSuccess() { return success; }

    public String getTransactionId() { return transactionId; }

    public String getErrorCode() {return errorCode;}

    public String getErrorMessage() { return errorMessage; }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "success=" + success +
                ", transactionId='" + transactionId + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
