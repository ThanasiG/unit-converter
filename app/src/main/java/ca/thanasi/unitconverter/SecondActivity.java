package ca.thanasi.unitconverter;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import ca.thanasi.unitconverter.area.AreaFragment;
import ca.thanasi.unitconverter.currency.CurrencyFragment;
import ca.thanasi.unitconverter.digitalstorage.DigitalStorageFragment;
import ca.thanasi.unitconverter.energy.EnergyFragment;
import ca.thanasi.unitconverter.weight.WeightFragment;

public class SecondActivity extends AppCompatActivity {

    int position_clicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_type);

        setAndStyleToolbar();

        Bundle bundle = getIntent().getExtras();
        position_clicked = bundle.getInt("positionclicked");

        if (position_clicked == 0) {
            AreaFragment areaFragment = new AreaFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, areaFragment);
            ft.commit();
        } else if (position_clicked == 1) {
            CurrencyFragment currencyFragment = new CurrencyFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, currencyFragment);
            ft.commit();
        }else if (position_clicked == 2) {
            DigitalStorageFragment digitalStorageFragment = new DigitalStorageFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, digitalStorageFragment);
            ft.commit();
        } else if (position_clicked == 3) {
            EnergyFragment energyFragment = new EnergyFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, energyFragment);
            ft.commit();
        } else if (position_clicked == 4) {
            WeightFragment weightFragment = new WeightFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_placeholder, weightFragment);
            ft.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weight, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setAndStyleToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.primaryDark_blue));
        }

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}