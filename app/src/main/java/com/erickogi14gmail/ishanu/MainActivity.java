package com.erickogi14gmail.ishanu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.erickogi14gmail.ishanu.Interfaces.DrawerItemListener;
import com.erickogi14gmail.ishanu.Utils.MainActivityDrawer;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

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
}
