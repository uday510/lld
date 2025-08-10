package com.app.patterns.structural.facade;

public class GoodCode {

    public static void main(String[] args) {

        APIGateway apiGateway = new APIGateway();

        System.out.println(apiGateway.getFullOrderDetails("user12345", "order12345", "payment12345"));
    }
}
