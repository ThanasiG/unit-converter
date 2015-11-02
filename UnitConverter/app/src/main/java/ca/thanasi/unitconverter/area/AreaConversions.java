package ca.thanasi.unitconverter.area;

import java.text.DecimalFormat;
import java.util.Arrays;

public class AreaConversions {
    final double value;
    final Area area;

    public static enum Area {
        sqKilometer(0.000001d), hectare(0.0001d), sqMeter(1.0d), sqCentimeter(10000d),
        sqMile(0.0000003861021585425d), acre(0.0002471053814672d), sqYard(1.19599004630108d),
        sqFoot(10.7639104167097d), sqInch(1550.0031000062d);

        final static Area baseArea = sqMeter;

        final double byBaseArea;

        private Area(double inSqMeter) {
            this.byBaseArea = inSqMeter;
        }

        public double toBaseArea(double value) {
            return value / byBaseArea;
        }

        public double fromBaseArea(double value) {
            return value * byBaseArea;
        }

    }

    public AreaConversions(double value, Area area) {
        super();
        this.value = value;
        this.area = area;
    }

    public AreaConversions to(Area newArea) {
        Area oldArea = this.area;
        return new AreaConversions(newArea.fromBaseArea(oldArea.toBaseArea(value)), newArea);

    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
