package ca.thanasi.unitconverter.digitalstorage;

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

public class DigitalStorageFragment extends Fragment {

    View view;
    private Spinner unitTypeSpinner;
    ArrayAdapter<CharSequence> unitTypeSpinnerAdapter;
    private EditText amountTextView;
    TextView bitTextView, byteTextView, kilobitTextView, kilobyteTextView, megabitTextView,
            megabyteTextView, gigabitTextView, gigabyteTextView, terabitTextView, terabyteTextView;

    public DigitalStorageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_digital_storage, container, false);

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

    public void checkIfConvertingFromMegabyte(String currentUnit) {

        if (currentUnit.equals("Megabyte")) {

            updateUnitTypesUsingMb(DigitalStorageConversions.DigitalStorage.megabyte);

        } else {

            if (currentUnit.equals("Terabyte")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.terabyte);

            } else if (currentUnit.equals("Terabit")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.terabit);

            } else if (currentUnit.equals("Gigabyte")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.gigabyte);

            } else if (currentUnit.equals("Gigabit")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.gigabit);

            } else if (currentUnit.equals("Megabyte")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.megabyte);

            } else if (currentUnit.equals("Megabit")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.megabit);

            } else if (currentUnit.equals("Kilobyte")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.kilobyte);

            } else if (currentUnit.equals("Kilobit")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.kilobit);

            } else if (currentUnit.equals("Byte")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.byteVar);

            } else if (currentUnit.equals("Bit")) {

                updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage.bit);

            } else {
                Toast.makeText(getActivity(), "Error converting", Toast.LENGTH_SHORT).show();
            }

        }

    }

    public void updateUnitTypesUsingMb(DigitalStorageConversions.DigitalStorage currentUnit) {

        // Convert the value in the EditText box to a double
        try {
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());


            // Combine value to unit
            String megabyteValueAndUnit = doubleToConvert + " megabyte";

            // Change the value for the teaspoon TextView
            megabyteTextView.setText(megabyteValueAndUnit);

            // Update all the Unit Text Fields
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.terabyte, terabyteTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.terabit, terabitTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.gigabyte, gigabyteTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.gigabit, gigabitTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.megabyte, megabyteTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.megabit, megabitTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.kilobyte, kilobyteTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.kilobit, kilobitTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.byteVar, byteTextView);
            updateUnitTextFieldUsingMegabyte(doubleToConvert, DigitalStorageConversions.DigitalStorage.bit, bitTextView);
        } catch (NumberFormatException nfe) {
        }
    }

    public void updateUnitTextFieldUsingMegabyte(double doubleToConvert, DigitalStorageConversions.DigitalStorage unitConvertingTo,
                                                TextView theTextView) {

        DigitalStorageConversions unitQuantity = new DigitalStorageConversions(doubleToConvert, DigitalStorageConversions.DigitalStorage.megabyte);

        String tempUnit = unitQuantity.to(unitConvertingTo).toString();

        theTextView.setText(tempUnit);

    }

    public void updateUnitTypesUsingOther(DigitalStorageConversions.DigitalStorage currentUnit) {
        try {
            // Convert the value in the EditText box to a double
            double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());

            // Create a Quantity using the sq metter unit
            DigitalStorageConversions currentQuantitySelected = new DigitalStorageConversions(doubleToConvert, currentUnit);

            // Create the String for the sq meter TextView
            String valueInMegabytes = currentQuantitySelected.to(DigitalStorageConversions.DigitalStorage.megabyte).toString();

            // Set the text for the sq meter TextView
            megabyteTextView.setText(valueInMegabytes);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.terabyte, terabyteTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.terabit, terabitTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.gigabyte, gigabyteTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.gigabit, gigabitTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.megabyte, megabitTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.megabit, megabitTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.kilobyte, kilobyteTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.kilobit, kilobitTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.byteVar, byteTextView);

            updateUnitTextFieldUsingMegabyte(doubleToConvert, currentUnit,
                    DigitalStorageConversions.DigitalStorage.bit, bitTextView);


            // Set the currently selected unit to the number in the EditText
            if (currentUnit.name().equals(currentQuantitySelected.digitalStorage.name())) {

                // Create the TextView text by taking the value in EditText and adding
                // on the currently selected unit in the spinner
                String currentUnitTextViewText = doubleToConvert + "";

                // Create the TextView name to change by getting the currently
                // selected quantities unit name and tacking on _text_view
                String currentTextViewName = currentQuantitySelected.digitalStorage.name() +
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

    public void updateUnitTextFieldUsingMegabyte(double doubleToConvert, DigitalStorageConversions.DigitalStorage currentUnit,
                                                 DigitalStorageConversions.DigitalStorage preferredUnit, TextView targetTextView) {

        DigitalStorageConversions currentQuantitySelected = new DigitalStorageConversions(doubleToConvert, currentUnit);

        // Algorithm used quantityInTbs.to(Unit.tsp).to(Unit.ounce)

        String tempTextViewText = currentQuantitySelected.to(DigitalStorageConversions.DigitalStorage.megabyte).
                to(preferredUnit).toString();

        targetTextView.setText(tempTextViewText);


    }


    public void updateUnitTypeUsingMegabyte(DigitalStorageConversions.DigitalStorage currentDigitalStorage) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String megabyteValueStr = String.valueOf(doubleToConvert);


    }

    public void addItemsToUnitTypeSpinner() {

        // Get a reference to the spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.digital_storage_conversions, android.R.layout.simple_spinner_item);

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
                checkIfConvertingFromMegabyte(itemSelectedInSpinner);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void linkTextViewsToVars() {
        terabyteTextView = (TextView) view.findViewById(R.id.terabyte_text_view);
        terabitTextView = (TextView) view.findViewById(R.id.terabit_text_view);
        gigabyteTextView = (TextView) view.findViewById(R.id.gigabyte_text_view);
        gigabitTextView = (TextView) view.findViewById(R.id.gigabit_text_view);
        megabyteTextView = (TextView) view.findViewById(R.id.megabyte_text_view);
        megabitTextView = (TextView) view.findViewById(R.id.megabit_text_view);
        kilobyteTextView = (TextView) view.findViewById(R.id.kilobyte_text_view);
        kilobitTextView = (TextView) view.findViewById(R.id.kilobit_text_view);
        byteTextView = (TextView) view.findViewById(R.id.byteVar_text_view);
        bitTextView = (TextView) view.findViewById(R.id.bit_text_view);

        megabyteTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                // ClipData clipData = ClipData.newPlainText("unitText", kilogramTextView.getText().toString());
            }
        });

    }

    public void clearTextViews() {
        terabyteTextView.setText("");
        terabitTextView.setText("");
        gigabyteTextView.setText("");
        gigabitTextView.setText("");
        megabyteTextView.setText("");
        megabitTextView.setText("");
        kilobyteTextView.setText("");
        kilobitTextView.setText("");
        byteTextView.setText("");
        bitTextView.setText("");
    }
}