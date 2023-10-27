public class ChocolateBoilerSingleton {
    private boolean empty;
    private boolean boiled;
    private static ChocolateBoilerSingleton UNIQUE_INSTANCE;


    private ChocolateBoilerSingleton() {
        empty = true;
        boiled = false;
    }
    public static ChocolateBoilerSingleton getInstance() {
        if (UNIQUE_INSTANCE == null) {
            UNIQUE_INSTANCE = new ChocolateBoilerSingleton();
        }
        return UNIQUE_INSTANCE;
    }
    public void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            // fill the boiler with a milk/chocolate mixture
        }
    }
    public void drain() {
        if (!isEmpty() && isBoiled()) {
            // drain the boiler with a milk/chocolate mixture
            empty = true;
        }
    }
    public void boil() {
        if (!isEmpty() && !isBoiled()) {
            // bring the contents to a boil
            boiled = true;
        }
    }
    public boolean isEmpty() {
        return empty;
    }
    public boolean isBoiled() {
        return boiled;
    }
}
