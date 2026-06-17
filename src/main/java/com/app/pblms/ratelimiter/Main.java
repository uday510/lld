package com.app.pblms.ratelimiter;

public class Main {


    static void main() throws InterruptedException {

        UserTierService tierService = new StubUserTierService();
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(tierService);

        System.out.println("=== FREE user burst (15 requests) ===");
        int freeAllowed = 0, freeRejected = 0;
        for (int i = 0; i < 15; i++) {
            if (limiter.allow("free-alice")) freeAllowed++;
            else freeRejected++;
        }


        System.out.println("FREE: allowed=" + freeAllowed + ", rejected=" + freeRejected);

        System.out.println("=== PRO user burst (150 requests) ===");
        int proAllowed = 0, proRejected = 0;
        for (int i = 0; i < 150; i++) {
            if (limiter.allow("pro-bob")) proAllowed++;
            else proRejected++;
        }


        System.out.println("PRO: allowed=" + proAllowed + ", rejected=" + proRejected);


        System.out.println("=== ENTERPRISE user burst (1500 requests) ===");
        int enterpriseAllowed = 0, enterpriseRejected = 0;
        for (int i = 0; i < 1500; i++) {
            if (limiter.allow("enterprise-charlie")) enterpriseAllowed++;
            else enterpriseRejected++;
        }


        System.out.println("ENTERPRISE: allowed=" + enterpriseAllowed + ", rejected=" + enterpriseRejected);


        System.out.println("\n=== Wait 1 second - FREE bucket refills ===");
        Thread.sleep(1000);
        int afterAllowed = 0;
        for (int i = 0; i < 15; i++) {
            if (limiter.allow("free-alice")) afterAllowed++;
        }

        System.out.println("FREE after wait: allowed=" + afterAllowed);
    }
}
