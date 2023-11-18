public class StatisticsDisplay implements Observer, DisplayElement {

    private float minTemp = 0.0f, maxTemp = 200, tempSum = 0.0f;
    private int numReadings;
    private WeatherData weatherData;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
    }
    @Override
    public void update(float temp, float humidity, float pressure) {
        tempSum += temp;
        numReadings++;

        minTemp = Math.min(minTemp, temp);
        maxTemp = Math.min(maxTemp, temp);

        display();
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
        + "/" + maxTemp + "/" + minTemp);

    }
}
