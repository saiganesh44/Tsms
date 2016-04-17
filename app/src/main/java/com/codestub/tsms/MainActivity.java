package com.codestub.tsms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.codestub.tsms.appbar.TsmsAppBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAppBar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    private void initAppBar() {
        TsmsAppBar TsmsAppBar = (TsmsAppBar) findViewById(R.id.toolbar);
        setSupportActionBar(TsmsAppBar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null ) {
            actionBar.setTitle(R.string.app_name);
        }
    }

}
