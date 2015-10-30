package ca.thanasi.unitconverter.weight;

import java.text.DecimalFormat;

public class WeightConversions {
    final double value;
    final Weight weight;

    public static enum Weight {
        metricTon(0.001d), kilogram(1.0d), hectogram(10d), dekagram(100d), gram(1000d), carat(5000d),
        decigram(10000d), centigram(100000d), milligram(1000000d), microgram(1000000000d), longTon(0.000984207d),
        shortTon(0.00110231d), pound(2.20462d), ounce(35.2739619495804d), dram(564.383391d), grain(15432.3584d);

        final static Weight baseWeight = kilogram;

        final double byBaseWeight;

        private Weight(double inKg) {
            this.byBaseWeight = inKg;
        }

        public double toBaseWeight(double value) {
            return value / byBaseWeight;
        }

        public double fromBaseWeight(double value) {
            return value * byBaseWeight;
        }
    }

    public WeightConversions(double value, Weight weight) {
        super();
        this.value = value;
        this.weight = weight;
    }

    public WeightConversions to(Weight newWeight) {
        Weight oldWeight = this.weight;
        return new WeightConversions(newWeight.fromBaseWeight(oldWeight.toBaseWeight(value)), newWeight);

    }
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
