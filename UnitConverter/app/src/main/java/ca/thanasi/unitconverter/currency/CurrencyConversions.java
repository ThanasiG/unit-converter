package ca.thanasi.unitconverter.currency;

import java.text.DecimalFormat;

public class CurrencyConversions {
    final double value;
    final Currency currency;

    static double uniteds = CurrencyFragment.unitedstatesCurr;
    static double eur = 1.0d;
    static double brit = CurrencyFragment.britishCurr;
    static double can = CurrencyFragment.canadianCurr;
    static double aus = CurrencyFragment.australianCurr;
    static double braz = CurrencyFragment.brazilianCurr;
    static double bulg = CurrencyFragment.bulgarianCurr;
    static double chin = CurrencyFragment.chineseCurr;
    static double croat = CurrencyFragment.croatianCurr;
    static double cze = CurrencyFragment.czechCurr;
    static double dan = CurrencyFragment.danishCurr;
    static double hongk = CurrencyFragment.hongkongCurr;
    static double hunga = CurrencyFragment.hungarianCurr;
    static double indi = CurrencyFragment.indianCurr;
    static double indon = CurrencyFragment.indonesianCurr;
    static double isra = CurrencyFragment.israeliCurr;
    static double jap = CurrencyFragment.japaneseCurr;
    static double malay = CurrencyFragment.malaysianCurr;
    static double mex = CurrencyFragment.mexicanCurr;
    static double newz = CurrencyFragment.newzealandCurr;
    static double norw = CurrencyFragment.norwegianCurr;
    static double phil = CurrencyFragment.philippineCurr;
    static double pol = CurrencyFragment.polishCurr;
    static double rom = CurrencyFragment.romanianCurr;
    static double rus = CurrencyFragment.russianCurr;
    static double southaf = CurrencyFragment.southafricanCurr;
    static double southko = CurrencyFragment.southkoreanCurr;
    static double singa = CurrencyFragment.singaporeCurr;
    static double swed = CurrencyFragment.swedishCurr;
    static double swis = CurrencyFragment.swissCurr;
    static double tha = CurrencyFragment.thaiCurr;
    static double turk = CurrencyFragment.turkishCurr;


    public static enum Currency {
        unitedstates(uniteds), euro(eur), british(brit), canadian(can), australian(aus), brazilian(braz),
        bulgarian(bulg), chinese(chin), croatian(croat), czech(cze), danish(dan), hongkong(hongk), hungarian(hunga),
        indian(indi), indonesian(indon), israeli(isra), japanese(jap), malaysian(malay), mexican(mex),
        newzealand(newz), norwegian(norw), philippine(phil), polish(pol), romanian(rom), russian(rus),
        southafrican(southaf), southkorean(southko), singapore(singa), swedish(swed), swiss(swis),
        thai(tha), turkish(turk);

        final static Currency baseCurrency = euro;

        final double byBaseCurrency;

        private Currency(double inEuro) {
            this.byBaseCurrency = inEuro;
        }

        public double toBaseCurrency(double value) {
            return value / byBaseCurrency;
        }

        public double fromBaseCurrency(double value) {
            return value * byBaseCurrency;
        }

    }

    public CurrencyConversions(double value, Currency currency) {
        super();
        this.value = value;
        this.currency = currency;
    }

    public CurrencyConversions to(Currency newCurrency) {
        Currency oldCurrency = this.currency;
        return new CurrencyConversions(newCurrency.fromBaseCurrency(oldCurrency.toBaseCurrency(value)), newCurrency);

    }


    public String toString() {
        DecimalFormat df = new DecimalFormat("#.####");
        return df.format(value);
    }
}
