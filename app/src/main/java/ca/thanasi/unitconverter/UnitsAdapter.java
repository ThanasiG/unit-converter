package ca.thanasi.unitconverter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UnitsAdapter extends ArrayAdapter<String> {
    Activity mActivityContext;
    String[] mUnitCategory;
    String[] mUnitDesc;
    int[] mImage;

    public UnitsAdapter(Activity context, int[] image, String[] unitCategory, String[] unitDesc) {
        super(context, R.layout.custom_list_item, unitCategory);

        this.mActivityContext = context;
        this.mImage = image;
        this.mUnitCategory = unitCategory;
        this.mUnitDesc = unitDesc;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = mActivityContext.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_item, null, true);

        ImageView ivUnitType = (ImageView) rowView.findViewById(R.id.list_item_icon);
        TextView tvUnitName = ((TextView) rowView.findViewById(R.id.unit_type_text));
        TextView tvUnitDesc = ((TextView) rowView.findViewById(R.id.unit_desc_text));

        ivUnitType.setImageResource(mImage[position]);
        tvUnitName.setText(mUnitCategory[position]);
        tvUnitDesc.setText(mUnitDesc[position]);

        return rowView;
    }


}