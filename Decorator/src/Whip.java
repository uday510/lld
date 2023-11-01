public class Whip implements Beverage {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double getCost() {
        return 20.0 + beverage.getCost();
    }

}
