package com.codestub.tsms.conversation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.codestub.tsms.Constants;
import com.codestub.tsms.R;
import com.codestub.tsms.appbar.TsmsAppBar;
import com.codestub.tsms.conversationslist.ConversationsListAdapter;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Intent intent = getIntent();
        byte[] byteArray = intent.getByteArrayExtra(Constants.CONTACT_PHOTO_BYTES.toString());
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView photo = (ImageView) findViewById(R.id.senderPhoto);
        TextView sender = (TextView) findViewById(R.id.sender);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(photo != null) {
                photo.setTransitionName(Constants.TRANSITION_PHOTO.toString());
            } if (sender != null) {
                sender.setTransitionName(Constants.TRANSITION_SENDER.toString());
            }
        }
        if(photo != null) {
            photo.setImageBitmap(bitmap);
        }
        if(sender != null) {
            sender.setText(intent.getStringExtra(Constants.TRANSITION_SENDER.toString()));
        }

        TsmsAppBar conversationAppBar = (TsmsAppBar) findViewById(R.id.conversationAppBar);
        setSupportActionBar(conversationAppBar);
        loadRecyclerView();
    }

    private void loadRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.conversationView);
        if(recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            ConversationAdapter adapter = new ConversationAdapter(this, getIntent().getStringExtra(Constants.THREAD_ID.toString()));
            //RecyclerView.LayoutManager llm = new LinearLayoutManager(getApplicationContext());
            LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
            llm.setReverseLayout(true);
            recyclerView.setLayoutManager(llm);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(adapter);
        }
    }
}
