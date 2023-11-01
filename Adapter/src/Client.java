public class Client {
    public static void main(String[] args) {
        String userInput = "ICICI";
        // Factory
        // BankAdapterFactory

        BankAPIAdapter bankAPIAdapter = new ICICIBankAPIAdapter();
        PhonePe account = new PhonePe(bankAPIAdapter);
    }
}
