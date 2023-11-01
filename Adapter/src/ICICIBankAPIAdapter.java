import thirdparty.iciciBankAPI;

public class ICICIBankAPIAdapter implements BankAPIAdapter {

    private iciciBankAPI iciciBankAPI = new iciciBankAPI();
    @Override
    public boolean sendMoney(String from, String to, double amount) {
        return false;
    }

    @Override
    public double getBalance(String accountNumber) {
        return 0;
    }
}
