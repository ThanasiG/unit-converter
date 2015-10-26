package ca.thanasi.unitconverter.energy;

import java.text.DecimalFormat;

public class EnergyConversions {
    final double value;
    final Energy energy;

    public static enum Energy {
        gigajoule(0.000000001d), megajoule(0.000001d), kilojoule(0.001d), joule(1.0d), ergs(10000000d),
        kilocalorie(0.0002388458966275d), calorie(0.2388458966275d), nutritionalcalorie(0.0002388458966275d),
        kilowatthour(0.0000002777778d), watthour(0.0002777777777778d), footpound(0.7375621492773d),
        inchpound(8.8507457913276d), btu(0.0009478169879134d), electronvolt(6241509744512000000d);

        final static Energy baseEnergy = joule;

        final double byBaseEnergy;

        private Energy(double inJoule) {
            this.byBaseEnergy = inJoule;
        }

        public double toBaseEnergy(double value) {
            return value / byBaseEnergy;
        }

        public double fromBaseEnergy(double value) {
            return value * byBaseEnergy;
        }

    }

    public EnergyConversions(double value, Energy energy) {
        super();
        this.value = value;
        this.energy = energy;
    }

    public EnergyConversions to(Energy newEnergy) {
        Energy oldEnergy = this.energy;
        return new EnergyConversions(newEnergy.fromBaseEnergy(oldEnergy.toBaseEnergy(value)), newEnergy);

    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
