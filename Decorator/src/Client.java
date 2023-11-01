public class Client {
    public static void main(String[] args) {
        Beverage beverage = new Decaf();
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);

        System.out.println(beverage.getCost());
    }
}
