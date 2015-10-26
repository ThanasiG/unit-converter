package ca.thanasi.unitconverter.digitalstorage;

import java.text.DecimalFormat;

public class DigitalStorageConversions {
    final double value;
    final DigitalStorage digitalStorage;

    public static enum DigitalStorage {
        terabyte(0.0000009537109375d), terabit(0.00000762939453125d), gigabyte(0.0009765625d), gigabit(0.0078125d), megabyte(1.0d),
        megabit(8.0d), kilobyte(1024d), kilobit(8192d), byteVar(1048576d), bit(8388608d);

        final static DigitalStorage baseDigitalStorage = megabyte;

        final double byBaseDigitalStorage;

        private DigitalStorage(double inMb) {
            this.byBaseDigitalStorage = inMb;
        }

        public double toBaseDigitalStorage(double value) {
            return value / byBaseDigitalStorage;
        }

        public double fromBaseDigitalStorage(double value) {
            return value * byBaseDigitalStorage;
        }
    }

    public DigitalStorageConversions(double value, DigitalStorage digitalStorage) {
        super();
        this.value = value;
        this.digitalStorage = digitalStorage;
    }

    public DigitalStorageConversions to(DigitalStorage newDigitalStorage) {
        DigitalStorage oldWeight = this.digitalStorage;
        return new DigitalStorageConversions(newDigitalStorage.fromBaseDigitalStorage(oldWeight.toBaseDigitalStorage(value)), newDigitalStorage);

    }
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
