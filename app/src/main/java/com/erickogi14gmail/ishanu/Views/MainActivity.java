package com.erickogi14gmail.ishanu.Views;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.erickogi14gmail.ishanu.Data.Db.DbOperations;
import com.erickogi14gmail.ishanu.Data.Db.PrefrenceManager;
import com.erickogi14gmail.ishanu.Data.Models.DataGen;
import com.erickogi14gmail.ishanu.Data.Models.MyAccountModel;
import com.erickogi14gmail.ishanu.Data.Models.ProductModel;
import com.erickogi14gmail.ishanu.Interfaces.DrawerItemListener;
import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Utils.MainActivityDrawer;
import com.erickogi14gmail.ishanu.Views.Login.LoginActivity;
import com.erickogi14gmail.ishanu.Views.SalesForms.MyStepperAdapter;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements StepperLayout.StepperListener {
    private StepperLayout mStepperLayout;
    private MyStepperAdapter mStepperAdapter;
    private DbOperations dbOperations;
    private PrefrenceManager prefrenceManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimationss() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimationss();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbOperations = new DbOperations(MainActivity.this);

        prefrenceManager = new PrefrenceManager(MainActivity.this);
        if (prefrenceManager.isFirstTime()) {
            for (ProductModel productModel : DataGen.genData(this, "convertcsv.json")) {
                dbOperations.insertItem(productModel, 1);
            }
            for (ProductModel productModel : DataGen.genData(this, "returnscsv.json")) {
                dbOperations.insertItem(productModel, 2);
            }

            prefrenceManager.setIsFirstTime(false);

        }

        if (!prefrenceManager.getReturns()[1].equals("null") || !prefrenceManager.getSales()[1].equals("null")) {
            alertDialogDelete("You have an unfinished sales process .If you wish to continue with the previous process click continue .If you wish to start a new process click clear NB* This will clear all data of the previous unfinished process");
        }
        MyAccountModel myAccountModel = prefrenceManager.getAccount();


        mStepperLayout = findViewById(R.id.stepperLayout);
        mStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(this);
        mStepperLayout.setOffscreenPageLimit(1);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        fab.hide();

        HashMap<String,String> details=new HashMap<>();
        details.put("name", myAccountModel.getName());
        details.put("email", myAccountModel.getEmail());
        Intent intent = new Intent(MainActivity.this, BaseActivity.class);

        MainActivityDrawer.getDrawer(MainActivity.this, toolbar, 1, details, "null", new DrawerItemListener() {
            @Override
            public void sellClicked() {


            }

            @Override
            public void reportsClicked() {
                intent.putExtra("id", 1);
                startActivity(intent);

            }

            @Override
            public void notificationsClicked() {


            }

            @Override
            public void accountClicked() {

                intent.putExtra("id", 2);
                startActivity(intent);
            }

            @Override
            public void settingsClicked() {

            }

            @Override
            public void logoutClicked() {

                prefrenceManager.setIsLoggedIn(false);
                finish();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }

            @Override
            public void helpClicked() {
                intent.putExtra("id", 3);
                startActivity(intent);
            }

            @Override
            public void aboutClicked() {
                intent.putExtra("id", 4);
                startActivity(intent);
            }
        });
    }

    private void alertDialogDelete(final String message) {
        final DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:

                    mStepperLayout.setCurrentStepPosition(1);

                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    prefrenceManager.clearReturnsData();
                    prefrenceManager.clearSalesData();
                    dialog.dismiss();


                    break;
            }
        };


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage(message).setPositiveButton("Continue", dialogClickListener)
                .setNegativeButton("Clear", dialogClickListener).show();

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


        // StepperLayout.setCurrentStepPosition(int)
        Toast.makeText(this, "Thank you for testing ISHANU APP ", Toast.LENGTH_SHORT).show();
        mStepperLayout.setCurrentStepPosition(0);
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
