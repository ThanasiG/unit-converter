package ca.thanasi.unitconverter.fuelconsumption;

import java.text.DecimalFormat;

public class FuelConsumptionConversions {
    final double value;
    final FuelConsumption fuelConsumption;

    public static enum FuelConsumption {
        usmpg(1.0d), impmpg(1.20095499255398d), kml(0.42514370749052d), lkm(235.214582d), mpl(0.264172052d);

        final static FuelConsumption baseFuelConsumption = usmpg;

        final double byBaseFuelConsumption;

        private FuelConsumption(double inUsmpg) {
            this.byBaseFuelConsumption = inUsmpg;
        }

        public double toBaseFuelConsumption(double value) {
            return value / byBaseFuelConsumption;
        }

        public double fromBaseFuelConsumption(double value) {
            return value * byBaseFuelConsumption;
        }

    }

    public FuelConsumptionConversions(double value, FuelConsumption fuelConsumption) {
        super();
        this.value = value;
        this.fuelConsumption = fuelConsumption;
    }

    public FuelConsumptionConversions to(FuelConsumption newFuelConsumption) {
        FuelConsumption oldFuelConsumption = this.fuelConsumption;
        return new FuelConsumptionConversions(newFuelConsumption.fromBaseFuelConsumption(oldFuelConsumption.toBaseFuelConsumption(value)), newFuelConsumption);

    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
