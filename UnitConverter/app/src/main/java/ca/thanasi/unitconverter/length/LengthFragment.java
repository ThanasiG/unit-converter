package ca.thanasi.unitconverter.length;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import ca.thanasi.unitconverter.R;

public class LengthFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView kilometerTextView, meterTextView, centimeterTextView, millimeterTextView, micrometerTextView,
            nanometerTextView, mileTextView, yardTextView, footTextView, inchTextView,
            nauticalmileTextView, furlongTextView, lightyearTextView;

    public LengthFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_length, container, false);

        amountTextView = (EditText) view.findViewById(R.id.amount_text_view);
        unitTypeSpinner = (Spinner) view.findViewById(R.id.unit_type_spinner);
        linkTextViewsToVars();


        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();


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

    public void checkIfConvertingFromKilometer(String currentUnit) {

        if (currentUnit.equals("Kilometer")) {

            updateUnitTypesUsingKilometer(LengthConversions.Length.kilometer);

        } else {

            if (currentUnit.equals("Meter")) {

                updateUnitTypesUsingOther(LengthConversions.Length.meter);

            } else if (currentUnit.equals("Centimeter")) {

                updateUnitTypesUsingOther(LengthConversions.Length.centimeter);

            } else if (currentUnit.equals("Millimeter")) {

                updateUnitTypesUsingOther(LengthConversions.Length.millimeter);

            } else if (currentUnit.equals("Micrometer")) {

                updateUnitTypesUsingOther(LengthConversions.Length.micrometer);

            } else if (currentUnit.equals("Nanometer")) {

                updateUnitTypesUsingOther(LengthConversions.Length.nanometer);

            } else if (currentUnit.equals("Mile")) {

                updateUnitTypesUsingOther(LengthConversions.Length.mile);

            } else if (currentUnit.equals("Yard")) {

                updateUnitTypesUsingOther(LengthConversions.Length.yard);

            } else if (currentUnit.equals("Foot")) {

                updateUnitTypesUsingOther(LengthConversions.Length.foot);

            } else if (currentUnit.equals("Inch")) {

                updateUnitTypesUsingOther(LengthConversions.Length.inch);

            } else if (currentUnit.equals("Nautical Mile")) {

                updateUnitTypesUsingOther(LengthConversions.Length.nauticalmile);

            } else if (currentUnit.equals("Furlong")) {

                updateUnitTypesUsingOther(LengthConversions.Length.furlong);

            } else if (currentUnit.equals("Light Year")) {

                updateUnitTypesUsingOther(LengthConversions.Length.lightyear);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingKilometer(LengthConversions.Length currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Combine value to unit
            String kilometerValueAndUnit = doubleToConvert + " kilometer";

            // Change the value for the teaspoon TextView
            kilometerTextView.setText(kilometerValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.kilometer, kilometerTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.meter, meterTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.centimeter, centimeterTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.millimeter, millimeterTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.micrometer, micrometerTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.nanometer, nanometerTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.mile, mileTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.yard, yardTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.foot, footTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.inch, inchTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.nauticalmile, nauticalmileTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.furlong, furlongTextView);
            updateUnitTextFieldUsingKilometer(doubleToConvert, LengthConversions.Length.lightyear, lightyearTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingKilometer(double doubleToConvert, LengthConversions.Length unitConvertingTo,
                                                TextView theTextView) {

        LengthConversions unitQuantity = new LengthConversions(doubleToConvert, LengthConversions.Length.kilometer);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(LengthConversions.Length currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            LengthConversions currentQuantitySelected = new LengthConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInKilometer = currentQuantitySelected.to(LengthConversions.Length.kilometer).toString();

            // Set the text for the sq meter TextView
            kilometerTextView.setText(valueInKilometer);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.meter, meterTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.millimeter, millimeterTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.micrometer, micrometerTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.nanometer, nanometerTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.mile, mileTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.yard, yardTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.foot, footTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.inch, inchTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.nauticalmile, nauticalmileTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.furlong, furlongTextView);

            updateUnitTextFieldUsingKilometer(doubleToConvert, currentUnit,
                    LengthConversions.Length.lightyear, lightyearTextView);


            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.length.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.length.name() +
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

    public void updateUnitTextFieldUsingKilometer(double doubleToConvert, LengthConversions.Length currentUnit,
                                                LengthConversions.Length preferredUnit, TextView targetTextView) {

        LengthConversions currentQuantitySelected = new LengthConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(LengthConversions.Length.kilometer).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingCelsius(LengthConversions.Length currentLength) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String celsiusValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.length_conversions, android.R.layout.simple_spinner_item);

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
                checkIfConvertingFromKilometer(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public void linkTextViewsToVars() {
        kilometerTextView = (TextView) view.findViewById(R.id.kilometer_text_view);
        meterTextView = (TextView) view.findViewById(R.id.meter_text_view);
        centimeterTextView = (TextView) view.findViewById(R.id.centimeter_text_view);
        millimeterTextView = (TextView) view.findViewById(R.id.millimeter_text_view);
        micrometerTextView = (TextView) view.findViewById(R.id.micrometer_text_view);
        nanometerTextView = (TextView) view.findViewById(R.id.nanometer_text_view);
        mileTextView = (TextView) view.findViewById(R.id.mile_text_view);
        yardTextView = (TextView) view.findViewById(R.id.yard_text_view);
        footTextView = (TextView) view.findViewById(R.id.foot_text_view);
        inchTextView = (TextView) view.findViewById(R.id.inch_text_view);
        nauticalmileTextView = (TextView) view.findViewById(R.id.nauticalmile_text_view);
        furlongTextView = (TextView) view.findViewById(R.id.furlong_text_view);
        lightyearTextView = (TextView) view.findViewById(R.id.lightyear_text_view);

        kilometerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        kilometerTextView.setText("");
        meterTextView.setText("");
        centimeterTextView.setText("");
        millimeterTextView.setText("");
        micrometerTextView.setText("");
        nanometerTextView.setText("");
        mileTextView.setText("");
        yardTextView.setText("");
        footTextView.setText("");
        inchTextView.setText("");
        nauticalmileTextView.setText("");
        furlongTextView.setText("");
        lightyearTextView.setText("");
    }
}