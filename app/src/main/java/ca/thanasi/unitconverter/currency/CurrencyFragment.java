package ca.thanasi.unitconverter.currency;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import ca.thanasi.unitconverter.R;

public class CurrencyFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    static String currencyInfo = "http://api.fixer.io/latest";
    static String unitedstates, british, canadian, australian, brazilian, bulgarian, chinese, croatian, czech,
            danish, hongkong, hungarian, indian, indonesian, israeli, japanese, malaysian,
            mexican, newzealand, norwegian, philippine, polish, romanian, russian, southafrican,
            southkorean, singapore, swedish, swiss, thai, turkish;

    static double unitedstatesCurr, britishCurr, canadianCurr, australianCurr, brazilianCurr, bulgarianCurr, chineseCurr, croatianCurr, czechCurr,
            danishCurr, hongkongCurr, hungarianCurr, indianCurr, indonesianCurr, israeliCurr, japaneseCurr, malaysianCurr,
            mexicanCurr, newzealandCurr, norwegianCurr, philippineCurr, polishCurr, romanianCurr, russianCurr, southafricanCurr,
            southkoreanCurr, singaporeCurr, swedishCurr, swissCurr, thaiCurr, turkishCurr;

    TextView unitedstatesTextView, euroTextView, britishTextView, canadianTextView, australianTextView,
            brazilianTextView, bulgarianTextView, chineseTextView, croatianTextView, czechTextView, danishTextView,
            hongkongTextView, hungarianTextView, indianTextView, indonesianTextView, israeliTextView,
            japaneseTextView, malaysianTextView, mexicanTextView, newzealandTextView, norwegianTextView,
            philippineTextView, polishTextView, romanianTextView, russianTextView, southafricanTextView,
            southkoreanTextView, singaporeTextView, swedishTextView, swissTextView, thaiTextView,
            turkishTextView;

    public CurrencyFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new MyAsyncTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_currency, container, false);

        amountTextView = (EditText) view.findViewById(R.id.amount_text_view);
        unitTypeSpinner = (Spinner) view.findViewById(R.id.unit_type_spinner);
        linkTextViewsToVars();


        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();
        //setCurrencyVariableValues();


        amountTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(amountTextView.getText().toString().equals(".")) && !amountTextView.getText().toString().isEmpty()) {
                    try {
                        double amountDouble = Double.parseDouble(amountTextView.getText().toString());
                        if (amountDouble > 0) {

                            int selectedUnit = unitTypeSpinner.getSelectedItemPosition();
                            unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);
                            unitTypeSpinner.setSelection(selectedUnit);
                            //addListenerToUnitTypeSpinner();
                        }
                    } catch (NumberFormatException nfe) {
                        Toast.makeText(getActivity(), "A number greater than 0 must be entered to convert.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    public void checkIfConvertingFromEuro(String currentUnit) {

        if (currentUnit.equals("Euro")) {

            updateUnitTypesUsingEuro(CurrencyConversions.Currency.euro);

        } else {
            if (currentUnit.equals("United States Dollar")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.unitedstates);

            } else if (currentUnit.equals("British Pound")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.british);

            } else if (currentUnit.equals("Canadian Dollar")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.canadian);

            } else if (currentUnit.equals("Australian Dollar")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.australian);

            } else if (currentUnit.equals("Brazilian Real")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.brazilian);

            } else if (currentUnit.equals("Bulgarian Lev")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.bulgarian);

            } else if (currentUnit.equals("Chinese Yuan")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.chinese);

            } else if (currentUnit.equals("Croatian Kuna")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.croatian);

            } else if (currentUnit.equals("Czech Koruna")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.czech);

            } else if (currentUnit.equals("Danish Krone")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.danish);

            } else if (currentUnit.equals("Hong Kong Dollar")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.hongkong);

            } else if (currentUnit.equals("Hungarian Forint")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.hungarian);

            } else if (currentUnit.equals("Indian Rupee")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.indian);

            } else if (currentUnit.equals("Indonesian Rupiah")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.indonesian);

            } else if (currentUnit.equals("Israeli New Sheqel")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.israeli);

            } else if (currentUnit.equals("Japanese Yen")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.japanese);

            } else if (currentUnit.equals("Malaysian Ringgit")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.malaysian);

            } else if (currentUnit.equals("Mexican Peso")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.mexican);

            } else if (currentUnit.equals("New Zealand Dollar")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.newzealand);

            } else if (currentUnit.equals("Norwegian Krone")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.norwegian);

            } else if (currentUnit.equals("Philippine Peso")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.philippine);

            } else if (currentUnit.equals("Polish Zloty")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.polish);

            } else if (currentUnit.equals("Romanian Leu")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.romanian);

            } else if (currentUnit.equals("Russian Ruble")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.russian);

            } else if (currentUnit.equals("South African Rand")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.southafrican);

            } else if (currentUnit.equals("South Korean Won")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.southkorean);

            } else if (currentUnit.equals("Singapore Dollar")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.singapore);

            } else if (currentUnit.equals("Swedish Krona")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.swedish);

            } else if (currentUnit.equals("Swiss Franc")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.swiss);

            } else if (currentUnit.equals("Thai Baht")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.thai);

            } else if (currentUnit.equals("Turkish Lira")) {

                updateUnitTypesUsingOther(CurrencyConversions.Currency.turkish);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingEuro(CurrencyConversions.Currency currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String euroValueAndUnit = doubleToConvert + " euro";

            // Change the value for the teaspoon TextView
            euroTextView.setText(euroValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.unitedstates, unitedstatesTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.euro, euroTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.british, britishTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.canadian, canadianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.australian, australianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.brazilian, brazilianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.bulgarian, bulgarianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.chinese, chineseTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.croatian, croatianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.czech, czechTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.danish, danishTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.hongkong, hongkongTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.hungarian, hungarianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.indian, indianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.indonesian, indonesianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.israeli, israeliTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.japanese, japaneseTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.malaysian, malaysianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.mexican, mexicanTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.newzealand, newzealandTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.norwegian, norwegianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.philippine, philippineTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.polish, polishTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.romanian, romanianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.russian, russianTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.southafrican, southafricanTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.southkorean, southkoreanTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.singapore, singaporeTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.swedish, swedishTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.swiss, swissTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.thai, thaiTextView);
            updateUnitTextFieldUsingEuro(doubleToConvert, CurrencyConversions.Currency.turkish, turkishTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingEuro(double doubleToConvert, CurrencyConversions.Currency unitConvertingTo,
                                             TextView theTextView) {

        CurrencyConversions unitQuantity = new CurrencyConversions(doubleToConvert, CurrencyConversions.Currency.euro);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(CurrencyConversions.Currency currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            CurrencyConversions currentQuantitySelected = new CurrencyConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInEuros = currentQuantitySelected.to(CurrencyConversions.Currency.euro).toString();

            // Set the text for the sq meter TextView
            euroTextView.setText(valueInEuros);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.unitedstates, unitedstatesTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.euro, euroTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.british, britishTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.canadian, canadianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.australian, australianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.brazilian, brazilianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.bulgarian, bulgarianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.chinese, chineseTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.croatian, croatianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.czech, czechTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.danish, danishTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.hongkong, hongkongTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.hungarian, hungarianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.indian, indianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.indonesian, indonesianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.israeli, israeliTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.japanese, japaneseTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.malaysian, malaysianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.mexican, mexicanTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.newzealand, newzealandTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.norwegian, norwegianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.philippine, philippineTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.polish, polishTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.romanian, romanianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.russian, russianTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.southafrican, southafricanTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.southkorean, southkoreanTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.singapore, singaporeTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.swedish, swedishTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.swiss, swissTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.thai, thaiTextView);

            updateUnitTextFieldUsingEuro(doubleToConvert, currentUnit,
                    CurrencyConversions.Currency.turkish, turkishTextView);


            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.currency.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.currency.name() +
                        "_text_view";

                // Get the resource id needed for the textView to use in findViewById
                int currentId = getResources().getIdentifier(currentTextViewName, "id",
                        getActivity().getPackageName());

                // Create an instance of the TextView we want to change
                TextView currentTextView = (TextView) view.findViewById(currentId);

                // Put the right data in the TextView
                currentTextView.setText(currentUnitTextViewText);

            }
        } catch (NumberFormatException e) {
        }
    }

    public void updateUnitTextFieldUsingEuro(double doubleToConvert, CurrencyConversions.Currency currentUnit,
                                             CurrencyConversions.Currency preferredUnit, TextView targetTextView) {

        CurrencyConversions currentQuantitySelected = new CurrencyConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(CurrencyConversions.Currency.euro).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingEuro(CurrencyConversions.Currency currentEuro) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String euroValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.currency_conversions, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner() {

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString(
                );
                // Verify if I'm converting from teaspoon so that I use the right
                // conversion algorithm
                checkIfConvertingFromEuro(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void linkTextViewsToVars() {
        unitedstatesTextView = (TextView) view.findViewById(R.id.unitedstates_text_view);
        euroTextView = (TextView) view.findViewById(R.id.euro_text_view);
        britishTextView = (TextView) view.findViewById(R.id.british_text_view);
        canadianTextView = (TextView) view.findViewById(R.id.canadian_text_view);
        australianTextView = (TextView) view.findViewById(R.id.australian_text_view);
        brazilianTextView = (TextView) view.findViewById(R.id.brazilian_text_view);
        bulgarianTextView = (TextView) view.findViewById(R.id.bulgarian_text_view);
        chineseTextView = (TextView) view.findViewById(R.id.chinese_text_view);
        croatianTextView = (TextView) view.findViewById(R.id.croatian_text_view);
        czechTextView = (TextView) view.findViewById(R.id.czech_text_view);
        danishTextView = (TextView) view.findViewById(R.id.danish_text_view);
        hongkongTextView = (TextView) view.findViewById(R.id.hongkong_text_view);
        hungarianTextView = (TextView) view.findViewById(R.id.hungarian_text_view);
        indianTextView = (TextView) view.findViewById(R.id.indian_text_view);
        indonesianTextView = (TextView) view.findViewById(R.id.indonesian_text_view);
        israeliTextView = (TextView) view.findViewById(R.id.israeli_text_view);
        japaneseTextView = (TextView) view.findViewById(R.id.japanese_text_view);
        malaysianTextView = (TextView) view.findViewById(R.id.malaysian_text_view);
        mexicanTextView = (TextView) view.findViewById(R.id.mexican_text_view);
        newzealandTextView = (TextView) view.findViewById(R.id.newzealand_text_view);
        norwegianTextView = (TextView) view.findViewById(R.id.norwegian_text_view);
        philippineTextView = (TextView) view.findViewById(R.id.philippine_text_view);
        polishTextView = (TextView) view.findViewById(R.id.polish_text_view);
        romanianTextView = (TextView) view.findViewById(R.id.romanian_text_view);
        russianTextView = (TextView) view.findViewById(R.id.russian_text_view);
        southafricanTextView = (TextView) view.findViewById(R.id.southafrican_text_view);
        southkoreanTextView = (TextView) view.findViewById(R.id.southkorean_text_view);
        singaporeTextView = (TextView) view.findViewById(R.id.singapore_text_view);
        swedishTextView = (TextView) view.findViewById(R.id.swedish_text_view);
        swissTextView = (TextView) view.findViewById(R.id.swiss_text_view);
        thaiTextView = (TextView) view.findViewById(R.id.thai_text_view);
        turkishTextView = (TextView) view.findViewById(R.id.turkish_text_view);

        euroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void setCurrencyVariableValues() {
        unitedstatesCurr = 1.096d;
        //Euro not needed. Its the base value, will always be 1.0d
        britishCurr = 0.7071d;
        canadianCurr = 1.4392d;
        australianCurr = 1.4884;
        brazilianCurr = 3.8559;
        bulgarianCurr = 1.9558;
        chineseCurr = 6.8058;
        croatianCurr = 7.531;
        czechCurr = 27.034;
        danishCurr = 7.4619;
        hongkongCurr = 8.4968;
        hungarianCurr = 311.27;
        indianCurr = 70.003;
        indonesianCurr = 1;//14823.56;
        israeliCurr = 4.1754;
        japaneseCurr = 136.67;
        malaysianCurr = 4.3144;
        mexicanCurr = 17.762;
        newzealandCurr = 1.6668;
        norwegianCurr = 9.026;
        philippineCurr = 50.213;
        polishCurr = 4.1968;
        romanianCurr = 4.4118;
        russianCurr = 70.41;
        southafricanCurr = 13.948;
        southkoreanCurr = 1275.09;
        singaporeCurr = 1.5191;
        swedishCurr = 9.5958;
        swissCurr = 1.08;
        thaiCurr = 38.513;
        turkishCurr = 3.0591;
    }

    public void clearTextViews() {
        unitedstatesTextView.setText("");
        euroTextView.setText("");
        britishTextView.setText("");
        canadianTextView.setText("");
        australianTextView.setText("");
        brazilianTextView.setText("");
        bulgarianTextView.setText("");
        chineseTextView.setText("");
        croatianTextView.setText("");
        czechTextView.setText("");
        danishTextView.setText("");
        hongkongTextView.setText("");
        hungarianTextView.setText("");
        indianTextView.setText("");
        indonesianTextView.setText("");
        israeliTextView.setText("");
        japaneseTextView.setText("");
        malaysianTextView.setText("");
        mexicanTextView.setText("");
        newzealandTextView.setText("");
        norwegianTextView.setText("");
        philippineTextView.setText("");
        polishTextView.setText("");
        romanianTextView.setText("");
        russianTextView.setText("");
        southafricanTextView.setText("");
        southkoreanTextView.setText("");
        singaporeTextView.setText("");
        swedishTextView.setText("");
        swissTextView.setText("");
        thaiTextView.setText("");
        turkishTextView.setText("");
    }

    public String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    private class MyAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String result = getJSON(currencyInfo, 5000);


            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject ratesJSONObject = jsonObject.getJSONObject("rates");

                unitedstates = ratesJSONObject.getString("USD");
                unitedstatesCurr = Double.parseDouble(unitedstates);

                //Euro not needed to be fetched. Its the base unit, will always be 1.0d

                british = ratesJSONObject.getString("GBP");
                britishCurr = Double.parseDouble(british);

                canadian = ratesJSONObject.getString("CAD");
                canadianCurr = Double.parseDouble(canadian);

                australian = ratesJSONObject.getString("AUD");
                australianCurr = Double.parseDouble(australian);

                brazilian = ratesJSONObject.getString("BRL");
                brazilianCurr = Double.parseDouble(brazilian);

                bulgarian = ratesJSONObject.getString("BGN");
                bulgarianCurr = Double.parseDouble(bulgarian);

                chinese = ratesJSONObject.getString("CNY");
                chineseCurr = Double.parseDouble(chinese);

                croatian = ratesJSONObject.getString("HRK");
                croatianCurr = Double.parseDouble(croatian);

                czech = ratesJSONObject.getString("CZK");
                czechCurr = Double.parseDouble(czech);

                danish = ratesJSONObject.getString("DKK");
                danishCurr = Double.parseDouble(danish);

                hongkong = ratesJSONObject.getString("HKD");
                hongkongCurr = Double.parseDouble(hongkong);

                hungarian = ratesJSONObject.getString("HUF");
                hungarianCurr = Double.parseDouble(hungarian);

                indian = ratesJSONObject.getString("INR");
                indianCurr = Double.parseDouble(indian);

                indonesian = ratesJSONObject.getString("IDR");
                indonesianCurr = Double.parseDouble(indonesian);

                israeli = ratesJSONObject.getString("ILS");
                israeliCurr = Double.parseDouble(israeli);

                japanese = ratesJSONObject.getString("JPY");
                japaneseCurr = Double.parseDouble(japanese);

                malaysian = ratesJSONObject.getString("MYR");
                malaysianCurr = Double.parseDouble(malaysian);

                mexican = ratesJSONObject.getString("MXN");
                mexicanCurr = Double.parseDouble(mexican);

                newzealand = ratesJSONObject.getString("NZD");
                newzealandCurr = Double.parseDouble(newzealand);

                norwegian = ratesJSONObject.getString("NOK");
                norwegianCurr = Double.parseDouble(norwegian);

                philippine = ratesJSONObject.getString("PHP");
                philippineCurr = Double.parseDouble(philippine);

                polish = ratesJSONObject.getString("PLN");
                polishCurr = Double.parseDouble(polish);

                romanian = ratesJSONObject.getString("RON");
                romanianCurr = Double.parseDouble(romanian);

                russian = ratesJSONObject.getString("RUB");
                russianCurr = Double.parseDouble(russian);

                southafrican = ratesJSONObject.getString("ZAR");
                southafricanCurr = Double.parseDouble(southafrican);

                southkorean = ratesJSONObject.getString("KRW");
                southkoreanCurr = Double.parseDouble(southkorean);

                singapore = ratesJSONObject.getString("SGD");
                singaporeCurr = Double.parseDouble(singapore);

                swedish = ratesJSONObject.getString("SEK");
                swedishCurr = Double.parseDouble(swedish);

                swiss = ratesJSONObject.getString("CHF");
                swissCurr = Double.parseDouble(swiss);

                thai = ratesJSONObject.getString("THB");
                thaiCurr = Double.parseDouble(thai);

                turkish = ratesJSONObject.getString("TRY");
                turkishCurr = Double.parseDouble(turkish);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}