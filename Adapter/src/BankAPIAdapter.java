public interface BankAPIAdapter {
    double getBalance(String accountNumber);
    boolean sendMoney(String from, String to, double amount);
}
