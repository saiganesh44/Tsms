package com.codestub.tsms;

import android.app.Activity;
import android.content.Intent;
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
import com.codestub.tsms.conversationslist.ConversationsListProvider;
import com.codestub.tsms.newconversation.NewConversationActivity;
import com.codestub.tsms.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {

    private ConversationsListProvider provider;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("mainactivity","oncreate called");
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_main);
        initAppBars();
        checkPermissions();
        loadRecyclerView();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent newConversationActivity = new Intent(mActivity, NewConversationActivity.class);
                    mActivity.startActivity(newConversationActivity);
                    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //      .setAction("Action", null).show();
                }
            });
        }
    }

    private void initAppBars() {
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

    private void checkPermissions() {
        String[] requiredPermissions = new String[]{Permissions.READ_SMS_PERMISSION, Permissions.READ_CONTACTS_PERMISSION};
        PermissionUtils.require(this,requiredPermissions, RequestCodes.MAIN_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCodes.MAIN_ACTIVITY_REQUEST_CODE : {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    loadRecyclerView();
                }
            }
        }
    }
}
