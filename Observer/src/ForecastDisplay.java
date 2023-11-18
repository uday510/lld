public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f, lastPressure;
    private WeatherData weatherData;


    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;

    }

    @Override
    public void display() {

    }

    @Override
    public void update(float temp, float humidity, float pressure) {

    }
}
