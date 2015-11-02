package ca.thanasi.unitconverter.temperature;

import java.text.DecimalFormat;

public class TemperatureConversions {
    final double value;
    final Temperature temperature;

    public static enum Temperature {
        celsius(1.0d), fahrenheit(33.8d), kelvin(274.15d);

        final static Temperature baseTemperature = celsius;

        final double byBaseTemperature;

        private Temperature(double inJoule) {
            this.byBaseTemperature = inJoule;
        }

        public double toBaseTemperature(double value) {
            return value / byBaseTemperature;
        }

        public double fromBaseTemperature(double value) {
            return value * byBaseTemperature;
        }

    }

    public TemperatureConversions(double value, Temperature temperature) {
        super();
        this.value = value;
        this.temperature = temperature;
    }

    public TemperatureConversions to(Temperature newTemperature) {
        Temperature oldTemperature = this.temperature;
        return new TemperatureConversions(newTemperature.fromBaseTemperature(oldTemperature.toBaseTemperature(value)), newTemperature);

    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
