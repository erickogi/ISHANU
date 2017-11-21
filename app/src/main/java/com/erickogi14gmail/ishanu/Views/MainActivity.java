package com.erickogi14gmail.ishanu.Views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.erickogi14gmail.ishanu.Data.Db.DbOperations;
import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.DataGen;
import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.Interfaces.DrawerItemListener;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.MainActivityDrawer;
import com.erickogi14gmail.ishanu.Views.SalesForms.MyStepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements StepperLayout.StepperListener {
    private StepperLayout mStepperLayout;
    private MyStepperAdapter mStepperAdapter;
    private DbOperations dbOperations;
    private PrefrenceManager prefrenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbOperations = new DbOperations(MainActivity.this);

        prefrenceManager = new PrefrenceManager(MainActivity.this);
        if (prefrenceManager.isFirstTime()) {
            for (ProductModel productModel : DataGen.genData(this)) {
                dbOperations.insertItem(productModel);
            }
            prefrenceManager.setIsFirstTime(false);

        }


        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        fab.hide();

        HashMap<String,String> details=new HashMap<>();
        details.put("name","Eric Kogi");
        details.put("email","erickogi14@gmail.com");

        MainActivityDrawer.getDrawer(MainActivity.this, toolbar, 1, details, "null", new DrawerItemListener() {
            @Override
            public void sellClicked() {

            }

            @Override
            public void reportsClicked() {

            }

            @Override
            public void notificationsClicked() {

            }

            @Override
            public void accountClicked() {

            }

            @Override
            public void settingsClicked() {

            }

            @Override
            public void logoutClicked() {

            }

            @Override
            public void helpClicked() {

            }

            @Override
            public void aboutClicked() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onCompleted(View completeButton) {

    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {

    }

    @Override
    public void onReturn() {

    }
}
