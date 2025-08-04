package com.app.oops.paymentservice;

import com.app.PaymentType;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {

    Map<PaymentType, PaymentMethod> paymentMethods = new HashMap<>();

    public void addPaymentMethod(PaymentType paymentType, PaymentMethod paymentMethod) {
        paymentMethods.put(paymentType, paymentMethod);
    }

    public void makePayment(PaymentType paymentType) {
        PaymentMethod paymentMethod = paymentMethods.get(paymentType);
        paymentMethod.pay(); // Run Time Polymorphism
    }

}
