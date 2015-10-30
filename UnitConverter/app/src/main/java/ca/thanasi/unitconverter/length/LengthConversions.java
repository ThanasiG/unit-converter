package ca.thanasi.unitconverter.length;

import java.text.DecimalFormat;

public class LengthConversions {
    final double value;
    final Length length;

    public static enum Length {
        kilometer(1.0d), meter(1000d), centimeter(100000d), millimeter(1000000d), micrometer(1000000000d),
        nanometer(1000000000000d), mile(0.6213712d),yard(1093.61329833771d), foot(3280.83989501312d), inch(39370.0787401575d),
        nauticalmile(0.539956803455724d), furlong(4.99709695379d), lightyear(0.000000000000106);

        final static Length baseLength = kilometer;

        final double byBaseLength;

        private Length(double inKilometer) {
            this.byBaseLength = inKilometer;
        }

        public double toBaseLength(double value) {
            return value / byBaseLength;
        }

        public double fromBaseLength(double value) {
            return value * byBaseLength;
        }

    }

    public LengthConversions(double value, Length length) {
        super();
        this.value = value;
        this.length = length;
    }

    public ca.thanasi.unitconverter.length.LengthConversions to(Length newLength) {
        Length oldLength = this.length;
        return new ca.thanasi.unitconverter.length.LengthConversions(newLength.fromBaseLength(oldLength.toBaseLength(value)), newLength);

    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
