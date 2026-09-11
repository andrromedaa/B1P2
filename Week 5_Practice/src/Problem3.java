import java.util.ArrayList;

class PatientVitals {

    private ArrayList<Double> readings;

    // Constructor
    PatientVitals(double[] initialReadings) {

        readings = new ArrayList<Double>();

        for (int i = 0; i < initialReadings.length; i++) {
            recordReading(initialReadings[i]);
        }
    }

    // Add a reading
    void recordReading(double reading) {

        if (reading > 0 && reading <= 45) {
            readings.add(reading);
        }
    }

    // Calculate average
    double getAverage() {

        if (readings.size() == 0) {
            return 0;
        }

        double sum = 0;

        for (int i = 0; i < readings.size(); i++) {
            sum = sum + readings.get(i);
        }

        return sum / readings.size();
    }

    // Return a copy
    double[] getAllReadings() {

        double[] result = new double[readings.size()];

        for (int i = 0; i < readings.size(); i++) {
            result[i] = readings.get(i);
        }

        return result;
    }
}


public class Problem3 {

    public static void main(String[] args) {

        // Initial readings
        double[] initial = {36.5, -2, 37.1};

        PatientVitals v = new PatientVitals(initial);

        // Print readings
        double[] readings = v.getAllReadings();

        for (int i = 0; i < readings.length; i++) {
            System.out.print(readings[i] + " ");
        }

        System.out.println();

        // Test defensive copy
        double[] copy = v.getAllReadings();

        copy[0] = 999;

        double[] original = v.getAllReadings();

        System.out.println(original[0]);

        // Test invalid reading
        v.recordReading(50);

        // Test valid reading
        v.recordReading(38);

        System.out.println(v.getAverage());
    }
}
