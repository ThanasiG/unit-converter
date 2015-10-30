package ca.thanasi.unitconverter.fuelconsumption;

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

public class FuelConsumptionFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView usmpgTextView, impmpgTextView, kmlTextView, lkmTextView, mplTextView;

    public FuelConsumptionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fuel_consumption, container, false);

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

    public void checkIfConvertingFromUsmpg(String currentUnit) {

        if (currentUnit.equals("US MPG")) {

            updateUnitTypesUsingUsmpg(FuelConsumptionConversions.FuelConsumption.usmpg);

        } else {

            if (currentUnit.equals("Imperial MPG")) {

                updateUnitTypesUsingOther(FuelConsumptionConversions.FuelConsumption.impmpg);

            } else if (currentUnit.equals("km/L")) {

                updateUnitTypesUsingOther(FuelConsumptionConversions.FuelConsumption.kml);

            } else if (currentUnit.equals("L/100km")) {

                updateUnitTypesUsingOther(FuelConsumptionConversions.FuelConsumption.lkm);

            } else if (currentUnit.equals("mi/L")) {

                updateUnitTypesUsingOther(FuelConsumptionConversions.FuelConsumption.mpl);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingUsmpg(FuelConsumptionConversions.FuelConsumption currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String usmpgValueAndUnit = doubleToConvert + " us mpg";

            // Change the value for the teaspoon TextView
            usmpgTextView.setText(usmpgValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingUsmpg(doubleToConvert, FuelConsumptionConversions.FuelConsumption.usmpg, usmpgTextView);
            updateUnitTextFieldUsingUsmpg(doubleToConvert, FuelConsumptionConversions.FuelConsumption.impmpg, impmpgTextView);
            updateUnitTextFieldUsingUsmpg(doubleToConvert, FuelConsumptionConversions.FuelConsumption.kml, kmlTextView);
            updateUnitTextFieldUsingUsmpg(doubleToConvert, FuelConsumptionConversions.FuelConsumption.lkm, lkmTextView);
            updateUnitTextFieldUsingUsmpg(doubleToConvert, FuelConsumptionConversions.FuelConsumption.mpl, mplTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingUsmpg(double doubleToConvert, FuelConsumptionConversions.FuelConsumption unitConvertingTo,
                                                TextView theTextView) {

        FuelConsumptionConversions unitQuantity = new FuelConsumptionConversions(doubleToConvert, FuelConsumptionConversions.FuelConsumption.usmpg);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(FuelConsumptionConversions.FuelConsumption currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            FuelConsumptionConversions currentQuantitySelected = new FuelConsumptionConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInUsmpg = currentQuantitySelected.to(FuelConsumptionConversions.FuelConsumption.usmpg).toString();

            // Set the text for the sq meter TextView
            usmpgTextView.setText(valueInUsmpg);

            updateUnitTextFieldUsingUsmpg(doubleToConvert, currentUnit,
                    FuelConsumptionConversions.FuelConsumption.impmpg, impmpgTextView);

            updateUnitTextFieldUsingUsmpg(doubleToConvert, currentUnit,
                    FuelConsumptionConversions.FuelConsumption.kml, kmlTextView);

            updateUnitTextFieldUsingUsmpg(doubleToConvert, currentUnit,
                    FuelConsumptionConversions.FuelConsumption.lkm, lkmTextView);

            updateUnitTextFieldUsingUsmpg(doubleToConvert, currentUnit,
                    FuelConsumptionConversions.FuelConsumption.mpl, mplTextView);

            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.fuelConsumption.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.fuelConsumption.name() +
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

    public void updateUnitTextFieldUsingUsmpg(double doubleToConvert, FuelConsumptionConversions.FuelConsumption currentUnit,
                                                FuelConsumptionConversions.FuelConsumption preferredUnit, TextView targetTextView) {

        FuelConsumptionConversions currentQuantitySelected = new FuelConsumptionConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(FuelConsumptionConversions.FuelConsumption.usmpg).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingCelsius(FuelConsumptionConversions.FuelConsumption currentUsmpg) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String usmpgValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.fuel_consumption_conversions, android.R.layout.simple_spinner_item);

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
                checkIfConvertingFromUsmpg(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void linkTextViewsToVars() {
        usmpgTextView = (TextView) view.findViewById(R.id.usmpg_text_view);
        impmpgTextView = (TextView) view.findViewById(R.id.impmpg_text_view);
        kmlTextView = (TextView) view.findViewById(R.id.kml_text_view);
        lkmTextView = (TextView) view.findViewById(R.id.lkm_text_view);
        mplTextView = (TextView) view.findViewById(R.id.mpl_text_view);

        usmpgTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        usmpgTextView.setText("");
        impmpgTextView.setText("");
        kmlTextView.setText("");
        lkmTextView.setText("");
        mplTextView.setText("");
    }
}