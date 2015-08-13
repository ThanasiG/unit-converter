package ca.thanasi.unitconverter.area;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import java.util.Arrays;
import java.util.List;

import ca.thanasi.unitconverter.R;

public class AreaFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView sqKilometerTextView, hectareTextView, sqMeterTextView, sqCentimeterTextView,
            sqMileTextView, acreTextView, sqYardTextView, sqFootTextView, sqInchTextView;

    public AreaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_area, container, false);

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

    public void checkIfConvertingFromSqMeter(String currentUnit) {

        if (currentUnit.equals("Square Meter")) {

            updateUnitTypesUsingSqMeter(AreaConversions.Area.sqMeter);

        } else {

            if (currentUnit.equals("Square Kilometer")) {

                updateUnitTypesUsingOther(AreaConversions.Area.sqKilometer);

            } else if (currentUnit.equals("Hectare")) {

                updateUnitTypesUsingOther(AreaConversions.Area.hectare);

            } else if (currentUnit.equals("Square Centimeter")) {

                updateUnitTypesUsingOther(AreaConversions.Area.sqCentimeter);

            } else if (currentUnit.equals("Square Mile")) {

                updateUnitTypesUsingOther(AreaConversions.Area.sqMile);

            } else if (currentUnit.equals("Acre")) {

                updateUnitTypesUsingOther(AreaConversions.Area.acre);

            } else if (currentUnit.equals("Square Yard")) {

                updateUnitTypesUsingOther(AreaConversions.Area.sqYard);

            } else if (currentUnit.equals("Square Foot")) {

                updateUnitTypesUsingOther(AreaConversions.Area.sqFoot);

            } else if (currentUnit.equals("Square Inch")) {

                updateUnitTypesUsingOther(AreaConversions.Area.sqInch);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingSqMeter(AreaConversions.Area currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String sqMeterValueAndUnit = doubleToConvert + " sq meter";

            // Change the value for the teaspoon TextView
            sqMeterTextView.setText(sqMeterValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqKilometer, sqKilometerTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.hectare, hectareTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqMeter, sqMeterTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqCentimeter, sqCentimeterTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqMile, sqMileTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.acre, acreTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqYard, sqYardTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqFoot, sqFootTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, AreaConversions.Area.sqInch, sqInchTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingSqMeter(double doubleToConvert, AreaConversions.Area unitConvertingTo,
                                                TextView theTextView) {

        AreaConversions unitQuantity = new AreaConversions(doubleToConvert, AreaConversions.Area.sqMeter);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(AreaConversions.Area currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            AreaConversions currentQuantitySelected = new AreaConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInSqMeters = currentQuantitySelected.to(AreaConversions.Area.sqMeter).toString();

            // Set the text for the sq meter TextView
            sqMeterTextView.setText(valueInSqMeters);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqKilometer, sqKilometerTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.hectare, hectareTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqMeter, sqMeterTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqCentimeter, sqCentimeterTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqMile, sqMileTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.acre, acreTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqYard, sqYardTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqFoot, sqFootTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    AreaConversions.Area.sqInch, sqInchTextView);


            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.area.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.area.name() +
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

    public void updateUnitTextFieldUsingSqMeter(double doubleToConvert, AreaConversions.Area currentUnit,
                                                AreaConversions.Area preferredUnit, TextView targetTextView) {

        AreaConversions currentQuantitySelected = new AreaConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(AreaConversions.Area.sqMeter).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingSqMeter(AreaConversions.Area currentArea) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String sqMeterValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.area_conversions, android.R.layout.simple_spinner_item);

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
                checkIfConvertingFromSqMeter(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void linkTextViewsToVars() {
        sqKilometerTextView = (TextView) view.findViewById(R.id.sqKilometer_text_view);
        hectareTextView = (TextView) view.findViewById(R.id.hectare_text_view);
        sqMeterTextView = (TextView) view.findViewById(R.id.sqMeter_text_view);
        sqCentimeterTextView = (TextView) view.findViewById(R.id.sqCentimeter_text_view);
        sqMileTextView = (TextView) view.findViewById(R.id.sqMile_text_view);
        acreTextView = (TextView) view.findViewById(R.id.acre_text_view);
        sqYardTextView = (TextView) view.findViewById(R.id.sqYard_text_view);
        sqFootTextView = (TextView) view.findViewById(R.id.sqFoot_text_view);
        sqInchTextView = (TextView) view.findViewById(R.id.sqInch_text_view);

        sqMeterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        sqKilometerTextView.setText("");
        hectareTextView.setText("");
        sqMeterTextView.setText("");
        sqCentimeterTextView.setText("");
        sqMileTextView.setText("");
        acreTextView.setText("");
        sqYardTextView.setText("");
        sqFootTextView.setText("");
        sqInchTextView.setText("");
    }
}