package com.erickogi14gmail.ishanu.Views;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;

import com.erickogi14gmail.ishanu.R;
import com.erickogi14gmail.ishanu.Views.Records.FrgamentRecords;
import com.erickogi14gmail.ishanu.Views.Support.FragmentAbout;
import com.erickogi14gmail.ishanu.Views.Support.FragmentSupport;
import com.erickogi14gmail.ishanu.Views.Support.Fragment_Account;

public class BaseActivity extends AppCompatActivity {
    public static Fragment fragment = null;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setReturnTransition(slide);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.hide();


        //  Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setLogo(R.drawable.ic_school_icon100dp);

        Intent intent = getIntent();


        int id = intent.getIntExtra("id", 0);


        switch (id) {
            case 1:
                fragment = new FrgamentRecords();
                this.setTitle("Records");
                //popOutFragments();
                setUpView();
                break;

            case 2:

                fragment = new Fragment_Account();
                this.setTitle("My Account");
                //popOutFragments();
                setUpView();

                break;

            case 3:

                fragment = new FragmentSupport();
                this.setTitle("Help");
                popOutFragments();
                setUpView();
                break;

            case 4:
                fragment = new FragmentAbout();
                this.setTitle("About");
                popOutFragments();
                setUpView();

                break;

            case 5:


                break;

            case 6:

                break;

            case 7:

                break;

        }
    }

    void setUpView() {
        if (fragment != null) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_layout, fragment)
                    .addToBackStack(null).commit();
        }

    }

    void popOutFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

