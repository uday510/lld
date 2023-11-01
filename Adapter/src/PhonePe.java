public class PhonePe {
    private BankAPIAdapter bankAPIAdapter;

    public PhonePe(BankAPIAdapter bankAPIAdapter) {
        this.bankAPIAdapter = bankAPIAdapter;
    }

    double doSomething() throws InterruptedException {
        double currBalance = bankAPIAdapter.getBalance("accountNumber");
        return currBalance * 2;
    }
}
