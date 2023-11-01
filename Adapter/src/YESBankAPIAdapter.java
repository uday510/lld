import thirdparty.yesBankAPI;

public class YESBankAPIAdapter implements BankAPIAdapter {
    private yesBankAPI yesBankAPI = new yesBankAPI();

    @Override
    public double getBalance(String accountNumber) {
        return 0;
    }

    @Override
    public boolean sendMoney(String from, String to, double amount) {
        return false;
    }
}
