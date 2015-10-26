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
    UnitTypeListAdapter mUnitsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnitsAdapter = new UnitTypeListAdapter(getActivity(), UnitListInfo.UNIT_IMAGES, UnitListInfo.UNIT_CATEGORIES, UnitListInfo.UNIT_DESCRIPTIONS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mListView = (ListView) rootView.findViewById(R.id.listview_units);


        mListView.setAdapter(mUnitsAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intentSecondActivity = new Intent(getActivity(), SecondActivity.class);
                intentSecondActivity.putExtra("positionclicked", position);
                startActivity(intentSecondActivity);

            }
        });

        return rootView;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
