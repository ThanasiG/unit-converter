package ca.thanasi.unitconverter.energy;

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

public class EnergyFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView gigajouleTextView, megajouleTextView, kilojouleTextView, jouleTextView, ergsTextView,
            kilocalorieTextView, calorieTextView, nutritionalcalorieTextView, kilowatthourTextView,
            watthourTextView, footpoundTextView, inchpoundTextView, btuTextView, electronvoltTextView;

    public EnergyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_energy, container, false);

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

        if (currentUnit.equals("Joule")) {

            updateUnitTypesUsingSqMeter(EnergyConversions.Energy.joule);

        } else {

            if (currentUnit.equals("Gigajoule")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.gigajoule);

            } else if (currentUnit.equals("Megajoule")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.megajoule);

            } else if (currentUnit.equals("Kilojoule")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.kilojoule);

            } else if (currentUnit.equals("Ergs")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.ergs);

            } else if (currentUnit.equals("Kilocalorie")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.kilocalorie);

            } else if (currentUnit.equals("Calorie")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.calorie);

            } else if (currentUnit.equals("Nutritional Calorie")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.nutritionalcalorie);

            } else if (currentUnit.equals("Kilowatt-Hour")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.kilowatthour);

            } else if (currentUnit.equals("Watt-Hour")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.watthour);

            } else if (currentUnit.equals("Foot-Pound")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.footpound);

            } else if (currentUnit.equals("Inch-Pound")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.inchpound);

            } else if (currentUnit.equals("BTU")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.btu);

            } else if (currentUnit.equals("Electronvolt")) {

                updateUnitTypesUsingOther(EnergyConversions.Energy.electronvolt);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingSqMeter(EnergyConversions.Energy currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String jouleValueAndUnit = doubleToConvert + " joule";

            // Change the value for the teaspoon TextView
            jouleTextView.setText(jouleValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.gigajoule, gigajouleTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.megajoule, megajouleTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.kilojoule, kilojouleTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.joule, jouleTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.ergs, ergsTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.kilocalorie, kilocalorieTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.calorie, calorieTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.nutritionalcalorie, nutritionalcalorieTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.kilowatthour, kilowatthourTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.watthour, watthourTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.footpound, footpoundTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.inchpound, inchpoundTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.btu, btuTextView);
            updateUnitTextFieldUsingSqMeter(doubleToConvert, EnergyConversions.Energy.electronvolt, electronvoltTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingSqMeter(double doubleToConvert, EnergyConversions.Energy unitConvertingTo,
                                                TextView theTextView) {

        EnergyConversions unitQuantity = new EnergyConversions(doubleToConvert, EnergyConversions.Energy.joule);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(EnergyConversions.Energy currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            EnergyConversions currentQuantitySelected = new EnergyConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInJoules = currentQuantitySelected.to(EnergyConversions.Energy.joule).toString();

            // Set the text for the sq meter TextView
            jouleTextView.setText(valueInJoules);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.gigajoule, gigajouleTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.megajoule, megajouleTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.kilojoule, kilojouleTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.joule, jouleTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.ergs, ergsTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.kilocalorie, kilocalorieTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.calorie, calorieTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.nutritionalcalorie, nutritionalcalorieTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.kilowatthour, kilowatthourTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.watthour, watthourTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.footpound, footpoundTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.inchpound, inchpoundTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.btu, btuTextView);

            updateUnitTextFieldUsingSqMeter(doubleToConvert, currentUnit,
                    EnergyConversions.Energy.electronvolt, electronvoltTextView);


            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.energy.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.energy.name() +
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

    public void updateUnitTextFieldUsingSqMeter(double doubleToConvert, EnergyConversions.Energy currentUnit,
                                                EnergyConversions.Energy preferredUnit, TextView targetTextView) {

        EnergyConversions currentQuantitySelected = new EnergyConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(EnergyConversions.Energy.joule).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingJoule(EnergyConversions.Energy currentEnergy) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String jouleValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.energy_conversions, android.R.layout.simple_spinner_item);

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
        gigajouleTextView = (TextView) view.findViewById(R.id.gigajoule_text_view);
        megajouleTextView = (TextView) view.findViewById(R.id.megajoule_text_view);
        kilojouleTextView = (TextView) view.findViewById(R.id.kilojoule_text_view);
        jouleTextView = (TextView) view.findViewById(R.id.joule_text_view);
        ergsTextView = (TextView) view.findViewById(R.id.ergs_text_view);
        kilocalorieTextView = (TextView) view.findViewById(R.id.kilocalorie_text_view);
        calorieTextView = (TextView) view.findViewById(R.id.calorie_text_view);
        nutritionalcalorieTextView = (TextView) view.findViewById(R.id.nutritionalcalorie_text_view);
        kilowatthourTextView = (TextView) view.findViewById(R.id.kilowatthour_text_view);
        watthourTextView = (TextView) view.findViewById(R.id.watthour_text_view);
        footpoundTextView = (TextView) view.findViewById(R.id.footpound_text_view);
        inchpoundTextView = (TextView) view.findViewById(R.id.inchpound_text_view);
        btuTextView = (TextView) view.findViewById(R.id.btu_text_view);
        electronvoltTextView = (TextView) view.findViewById(R.id.electronvolt_text_view);

        jouleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        gigajouleTextView.setText("");
        megajouleTextView.setText("");
        kilojouleTextView.setText("");
        jouleTextView.setText("");
        ergsTextView.setText("");
        kilocalorieTextView.setText("");
        calorieTextView.setText("");
        nutritionalcalorieTextView.setText("");
        kilowatthourTextView.setText("");
        watthourTextView.setText("");
        footpoundTextView.setText("");
        inchpoundTextView.setText("");
        btuTextView.setText("");
        electronvoltTextView.setText("");
    }
}