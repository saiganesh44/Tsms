package com.codestub.tsms.conversation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.codestub.tsms.Constants;
import com.codestub.tsms.R;
import com.codestub.tsms.appbar.TsmsAppBar;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Intent intent = getIntent();
        byte[] byteArray = intent.getByteArrayExtra(Constants.CONTACT_PHOTO_BYTES.toString());
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView photo = (ImageView) findViewById(R.id.contantPhoto);
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
    }
}
