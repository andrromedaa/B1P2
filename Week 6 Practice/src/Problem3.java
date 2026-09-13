import java.util.ArrayList;

class PatientVitals3 {

    private ArrayList<Double> bodyTemperature;
    private ArrayList<Integer> heartRate;

    // Constructor
    public PatientVitals3(double[] temperatures, int[] heartRates) {

        bodyTemperature = new ArrayList<Double>();
        heartRate = new ArrayList<Integer>();

        for (int i = 0; i < temperatures.length; i++) {
            recordReading(temperatures[i], heartRates[i]);
        }
    }

    // Add a reading
    public void recordReading(double temperature, int rate) {

        if (temperature > 0 && temperature <= 45 &&
                rate > 0) {

            bodyTemperature.add(temperature);
            heartRate.add(rate);
        }
    }

    // Return all temperatures
    public double[] getAllReadings() {

        double[] result = new double[bodyTemperature.size()];

        for (int i = 0; i < bodyTemperature.size(); i++) {
            result[i] = bodyTemperature.get(i);
        }

        return result;
    }

    // Average temperature
    public double getAverageTemperature() {

        if (bodyTemperature.size() == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < bodyTemperature.size(); i++) {
            sum = sum + bodyTemperature.get(i);
        }

        return sum / bodyTemperature.size();
    }
}


public class Problem3 {

    public static void main(String[] args) {

        double[] temperatures = {
                36.5, -2, 37.1
        };

        int[] heartRates = {
                72, 80, 75
        };

        PatientVitals3 p =
                new PatientVitals3(temperatures, heartRates);

        // Print valid readings
        double[] readings = p.getAllReadings();

        for (int i = 0; i < readings.length; i++) {
            System.out.print(readings[i] + " ");
        }

        System.out.println();

        // Test defensive copy
        double[] copy = p.getAllReadings();

        copy[0] = 999;

        System.out.println(p.getAllReadings()[0]);

        // Test invalid reading
        p.recordReading(50, 90);

        // Test valid reading
        p.recordReading(38, 80);

        System.out.println(p.getAverageTemperature());
    }
}