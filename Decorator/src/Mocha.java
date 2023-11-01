public class Mocha implements Beverage {
    Beverage beverage;
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double getCost() {
        return 25.0 + beverage.getCost();
    }
}
