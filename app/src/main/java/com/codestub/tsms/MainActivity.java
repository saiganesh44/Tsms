package com.codestub.tsms;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.codestub.tsms.appbar.TsmsAppBar;
import com.codestub.tsms.conversationslist.ConversationsListAdapter;
import com.codestub.tsms.conversationslist.Conversation;
import com.codestub.tsms.conversationslist.ConversationsListProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ConversationsListProvider provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mainactivity","oncreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAppBars();
        loadRecyclerView();
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

    private void initAppBars() {
        //init application bar
        TsmsAppBar applicationBar = (TsmsAppBar) findViewById(R.id.applicationBar);
        setSupportActionBar(applicationBar);
        ActionBar applicationActionBar = getSupportActionBar();
        if(applicationActionBar != null) {
            applicationActionBar.setTitle(R.string.app_name);
        }
        //init conversations bar
        TsmsAppBar tsmsAppBar = (TsmsAppBar) findViewById(R.id.conversationBar);
        setSupportActionBar(tsmsAppBar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null ) {
            actionBar.setTitle(R.string.conversations_list);
        }
    }

    private void loadRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.conversationsList);
        if(recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            ConversationsListAdapter adapter = new ConversationsListAdapter(this);
            RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(llm);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCodes.READ_SMS_REQUEST_CODE : {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadRecyclerView();
                }
            }
        }
    }
}
