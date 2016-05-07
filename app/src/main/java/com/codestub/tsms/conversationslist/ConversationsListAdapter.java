package com.codestub.tsms.conversationslist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codestub.tsms.Constants;
import com.codestub.tsms.MainActivity;
import com.codestub.tsms.R;
import com.codestub.tsms.conversation.ConversationActivity;
import com.codestub.tsms.utils.BitmapUtils;
import com.codestub.tsms.utils.ConversionUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/**
 * This class lists the main conversationsList
 * Created by ganesh on 20/4/16.
 */
public class ConversationsListAdapter extends RecyclerView.Adapter<ConversationsListViewHolder> {

    ConversationsListProvider provider;
    Activity activity;

    public ConversationsListAdapter(Activity activity) {
        this.activity = activity;
        this.provider = new ConversationsListProvider(activity);
    }

    @Override
    public int getItemCount() {
        return provider.size();
    }

    @Override
    public ConversationsListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.conversation_card, viewGroup, false);
        return new ConversationsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ConversationsListViewHolder holder, int position) {
        final ConversationTile conversationTile = provider.getItem(position);
        holder.sender.setText(conversationTile.getSenderPhNo());
        holder.abstractConvo.setText(conversationTile.getAbstractConvo());
        holder.date.setText(conversationTile.getDate());
        Bitmap bitmap = conversationTile.getContact().getBitmapPhoto();
        if(bitmap != null) {
            bitmap = BitmapUtils.getCircularBitmap(bitmap);
            holder.photo.setImageBitmap(bitmap);
        } else {
            int width = holder.photo.getLayoutParams().width;
            int height = holder.photo.getLayoutParams().height;
            int bitmapWidth = (int) ConversionUtils.dpTopx(activity, width);
            int bitmapHeight = (int) ConversionUtils.dpTopx(activity, height);

            char c = conversationTile.getSenderPhNo().toUpperCase().charAt(0);
            if (Character.isLetter(c)){
                bitmap = BitmapUtils.getLetteredAvatar(activity, c, bitmapWidth, bitmapHeight);
            } else {
                bitmap = BitmapUtils.getLetteredAvatar(activity, '#', bitmapWidth, bitmapHeight);
            }
            holder.photo.setImageBitmap(bitmap);
        }
        final Bitmap contactPhoto = bitmap;

        holder.conversationsListMsgLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,ConversationActivity.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    contactPhoto.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra(Constants.CONTACT_PHOTO_BYTES.toString(), byteArray);
                    intent.putExtra(Constants.TRANSITION_SENDER.toString(), conversationTile.getSenderPhNo());

                    View photo = holder.photo;
                    photo.setTransitionName(Constants.TRANSITION_PHOTO.toString());
                    Pair<View, String> pair1 = Pair.create(photo, photo.getTransitionName());

                    View sender = holder.sender;
                    sender.setTransitionName(Constants.TRANSITION_SENDER.toString());
                    Pair<View, String> pair2 = Pair.create(sender, sender.getTransitionName());

                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair1, pair2);
                    activity.startActivity(intent, options.toBundle());
                } else {
                    activity.startActivity(intent);
                }
                //activity.startActivity(intent);
                //activity.overridePendingTransition(android.support.design.R.anim.design_snackbar_in, android.support.design.R.anim.abc_fade_out);
            }
        });
    }
}
