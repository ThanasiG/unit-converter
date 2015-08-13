package ca.thanasi.unitconverter.weight;

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

import ca.thanasi.unitconverter.R;

public class WeightFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView metricTonTextView, kilogramTextView, hectogramTextView, dekagramTextView, gramTextView, caratTextView,
            decigramTextView, centigramTextView, milligramTextView, microgramTextView, longTonTextView, shortTonTextView,
            poundTextView, ounceTextView, dramTextView, grainTextView;

    public WeightFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weight, container, false);

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

    public void checkIfConvertingFromKg(String currentUnit) {

        if (currentUnit.equals("Kilogram")) {

            updateUnitTypesUsingKg(WeightConversions.Weight.kilogram);

        } else {

            if (currentUnit.equals("Metric Ton")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.metricTon);

            } else if (currentUnit.equals("Hectogram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.hectogram);

            } else if (currentUnit.equals("Dekagram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.dekagram);

            } else if (currentUnit.equals("Gram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.gram);

            } else if (currentUnit.equals("Carat")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.carat);

            } else if (currentUnit.equals("Decigram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.decigram);

            } else if (currentUnit.equals("Centigram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.centigram);

            } else if (currentUnit.equals("Milligram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.milligram);

            } else if (currentUnit.equals("Microgram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.microgram);

            } else if (currentUnit.equals("Long Ton")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.longTon);

            } else if (currentUnit.equals("Short Ton")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.shortTon);

            } else if (currentUnit.equals("Pound")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.pound);

            } else if (currentUnit.equals("Ounce")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.ounce);

            } else if (currentUnit.equals("Dram")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.dram);

            } else if (currentUnit.equals("Grain")) {

                updateUnitTypesUsingOther(WeightConversions.Weight.grain);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();

            }
        }

    }

    public void updateUnitTypesUsingKg(WeightConversions.Weight currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String kilogramValueAndUnit = doubleToConvert + "";

            // Change the value for the teaspoon TextView
            kilogramTextView.setText(kilogramValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.metricTon, metricTonTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.hectogram, hectogramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.dekagram, dekagramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.gram, gramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.carat, caratTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.decigram, decigramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.centigram, centigramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.milligram, milligramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.microgram, microgramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.longTon, longTonTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.shortTon, shortTonTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.pound, poundTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.ounce, ounceTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.dram, dramTextView);
            updateUnitTextFieldUsingKg(doubleToConvert, WeightConversions.Weight.grain, grainTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingKg(double doubleToConvert, WeightConversions.Weight unitConvertingTo,
                                           TextView theTextView) {

        WeightConversions unitQuantity = new WeightConversions(doubleToConvert, WeightConversions.Weight.kilogram);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(WeightConversions.Weight currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the teaspoon unit
            WeightConversions currentQuantitySelected = new WeightConversions(doubleToConvert, currentUnit);

            // Create the String for the teaspoon TextView
            String valueInKilograms = currentQuantitySelected.to(WeightConversions.Weight.kilogram).toString();

            // Set the text for the teaspoon TextView
            kilogramTextView.setText(valueInKilograms);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.metricTon, metricTonTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.hectogram, hectogramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.dekagram, dekagramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.gram, gramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.carat, caratTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.decigram, decigramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.centigram, centigramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.milligram, milligramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.microgram, microgramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.longTon, longTonTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.shortTon, shortTonTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.pound, poundTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.ounce, ounceTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.dram, dramTextView);

            updateUnitTextFieldUsingKg(doubleToConvert, currentUnit,
                    WeightConversions.Weight.grain, grainTextView);


            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.weight.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.weight.name() +
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

    public void updateUnitTextFieldUsingKg(double doubleToConvert, WeightConversions.Weight currentUnit,
                                           WeightConversions.Weight preferredUnit, TextView targetTextView) {

        WeightConversions currentQuantitySelected = new WeightConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(WeightConversions.Weight.kilogram).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingKg(WeightConversions.Weight currentWeight) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String kgValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.weight_conversions, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addListenerToUnitTypeSpinner() {

        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View arg1, int pos, long arg3) {
                // Get the item selected in the Spinner
                String itemSelectedInSpinner = parent.getItemAtPosition(pos).toString();
                // Verify if I'm converting from teaspoon so that I use the right
                // conversion algorithm
                checkIfConvertingFromKg(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void linkTextViewsToVars() {
        kilogramTextView = (TextView) view.findViewById(R.id.kilogram_text_view);
        metricTonTextView = (TextView) view.findViewById(R.id.metricTon_text_view);
        hectogramTextView = (TextView) view.findViewById(R.id.hectogram_text_view);
        dekagramTextView = (TextView) view.findViewById(R.id.dekagram_text_view);
        gramTextView = (TextView) view.findViewById(R.id.gram_text_view);
        caratTextView = (TextView) view.findViewById(R.id.carat_text_view);
        decigramTextView = (TextView) view.findViewById(R.id.decigram_text_view);
        centigramTextView = (TextView) view.findViewById(R.id.centigram_text_view);
        milligramTextView = (TextView) view.findViewById(R.id.milligram_text_view);
        microgramTextView = (TextView) view.findViewById(R.id.microgram_text_view);
        longTonTextView = (TextView) view.findViewById(R.id.longTon_text_view);
        shortTonTextView = (TextView) view.findViewById(R.id.shortTon_text_view);
        poundTextView = (TextView) view.findViewById(R.id.pound_text_view);
        ounceTextView = (TextView) view.findViewById(R.id.ounce_text_view);
        dramTextView = (TextView) view.findViewById(R.id.dram_text_view);
        grainTextView = (TextView) view.findViewById(R.id.grain_text_view);

        kilogramTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        kilogramTextView.setText("");
        metricTonTextView.setText("");
        hectogramTextView.setText("");
        dekagramTextView.setText("");
        gramTextView.setText("");
        caratTextView.setText("");
        decigramTextView.setText("");
        centigramTextView.setText("");
        milligramTextView.setText("");
        microgramTextView.setText("");
        longTonTextView.setText("");
        shortTonTextView.setText("");
        poundTextView.setText("");
        ounceTextView.setText("");
        dramTextView.setText("");
        grainTextView.setText("");
    }
}