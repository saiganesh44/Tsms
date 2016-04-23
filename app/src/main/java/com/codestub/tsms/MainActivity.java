package com.codestub.tsms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codestub.tsms.appbar.TsmsAppBar;
import com.codestub.tsms.conversationslist.ConversationsListAdapter;
import com.codestub.tsms.model.Conversation;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Conversation> conversationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAppBars();
        initializeData();
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
            ConversationsListAdapter adapter = new ConversationsListAdapter(conversationList);
            RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(llm);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }

    private void initializeData() {
        conversationList = new ArrayList<>();
        conversationList.add(new Conversation("1234563","sai"));
        conversationList.add(new Conversation("1234563","varshini"));
        conversationList.add(new Conversation("1234563","ganesh"));
        conversationList.add(new Conversation("1234563","vuradi"));
        conversationList.add(new Conversation("1234563","pittala"));
        conversationList.add(new Conversation("1234563","sampath"));
        conversationList.add(new Conversation("1234563","varun"));
        conversationList.add(new Conversation("1234563","sushma"));
        conversationList.add(new Conversation("1234563","sai"));
        conversationList.add(new Conversation("1234563","venu"));
        conversationList.add(new Conversation("1234563","vijay"));
        conversationList.add(new Conversation("1234563","me"));
    }

}
