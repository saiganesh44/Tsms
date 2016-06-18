package com.codestub.tsms.newconversation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.codestub.tsms.R;

/**
 * Activity Class for New Conversation
 * Created by ganesh on 28/5/16.
 */
public class NewConversationActivity extends AppCompatActivity {
    // Defines a tag for identifiying log entries
    final static String TAG = "NewConversationActivity";
    private static FloatingActionButton fabSelectContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newconversation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.contact_list_toolbar);
        setSupportActionBar(toolbar);

        fabSelectContact = (FloatingActionButton) findViewById(R.id.fabSelectContact);
        if(fabSelectContact != null) {
            fabSelectContact.hide();
        }
    }

    public static void showSelectFab(boolean value) {
        if(value) {
            fabSelectContact.show();
        } else {
            fabSelectContact.hide();
        }
    }
}
