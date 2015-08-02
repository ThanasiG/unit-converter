package ca.thanasi.unitconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    ListView mListView;
    UnitsAdapter mUnitsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnitsAdapter = new UnitsAdapter(getActivity(), UnitListInfo.UNIT_IMAGES, UnitListInfo.UNIT_CATEGORIES, UnitListInfo.UNIT_DESCRIPTIONS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_units);


        mListView.setAdapter(mUnitsAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intentWeight = new Intent(getActivity(), SecondActivity.class);
                    intentWeight.putExtra("positionclicked", position);
                    startActivity(intentWeight);
                } else if (position == 1) {
                    Toast.makeText(getActivity(), "Position " + position + " clicked", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Position " + position + " clicked", Toast.LENGTH_LONG).show();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
