package ca.thanasi.unitconverter.temperature;

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

public class TemperatureFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView celsiusTextView, fahrenheitTextView, kelvinTextView;

    public TemperatureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_temperature, container, false);

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

    public void checkIfConvertingFromCelsius(String currentUnit) {

        if (currentUnit.equals("Celsius")) {

            updateUnitTypesUsingCelsius(TemperatureConversions.Temperature.celsius);

        } else {

            if (currentUnit.equals("Fahrenheit")) {

                updateUnitTypesUsingOther(TemperatureConversions.Temperature.fahrenheit);

            } else if (currentUnit.equals("Kelvin")) {

                updateUnitTypesUsingOther(TemperatureConversions.Temperature.kelvin);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingCelsius(TemperatureConversions.Temperature currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String celsiusValueAndUnit = doubleToConvert + " celsius";

            // Change the value for the teaspoon TextView
            celsiusTextView.setText(celsiusValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingCelsius(doubleToConvert, TemperatureConversions.Temperature.celsius, celsiusTextView);
            updateUnitTextFieldUsingCelsius(doubleToConvert, TemperatureConversions.Temperature.fahrenheit, fahrenheitTextView);
            updateUnitTextFieldUsingCelsius(doubleToConvert, TemperatureConversions.Temperature.kelvin, kelvinTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingCelsius(double doubleToConvert, TemperatureConversions.Temperature unitConvertingTo,
                                                TextView theTextView) {

        TemperatureConversions unitQuantity = new TemperatureConversions(doubleToConvert, TemperatureConversions.Temperature.celsius);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(TemperatureConversions.Temperature currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            TemperatureConversions currentQuantitySelected = new TemperatureConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInCelsius = currentQuantitySelected.to(TemperatureConversions.Temperature.celsius).toString();

            // Set the text for the sq meter TextView
            celsiusTextView.setText(valueInCelsius);

            updateUnitTextFieldUsingCelsius(doubleToConvert, currentUnit,
                    TemperatureConversions.Temperature.fahrenheit, fahrenheitTextView);

            updateUnitTextFieldUsingCelsius(doubleToConvert, currentUnit,
                    TemperatureConversions.Temperature.kelvin, kelvinTextView);

            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.temperature.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.temperature.name() +
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

    public void updateUnitTextFieldUsingCelsius(double doubleToConvert, TemperatureConversions.Temperature currentUnit,
                                                TemperatureConversions.Temperature preferredUnit, TextView targetTextView) {

        TemperatureConversions currentQuantitySelected = new TemperatureConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(TemperatureConversions.Temperature.celsius).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingCelsius(TemperatureConversions.Temperature currentTemperature) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String celsiusValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.temperature_conversions, android.R.layout.simple_spinner_item);

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
                checkIfConvertingFromCelsius(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void linkTextViewsToVars() {
        celsiusTextView = (TextView) view.findViewById(R.id.celsius_text_view);
        fahrenheitTextView = (TextView) view.findViewById(R.id.fahrenheit_text_view);
        kelvinTextView = (TextView) view.findViewById(R.id.kelvin_text_view);

        celsiusTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        celsiusTextView.setText("");
        fahrenheitTextView.setText("");
        kelvinTextView.setText("");
    }
}