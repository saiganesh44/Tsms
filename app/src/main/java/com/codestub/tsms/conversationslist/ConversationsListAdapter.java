package com.codestub.tsms.conversationslist;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.media.RatingCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codestub.tsms.R;
import com.codestub.tsms.utils.BitmapUtils;
import com.codestub.tsms.utils.ConversionUtils;


/**
 * This class lists the main conversationsList
 * Created by ganesh on 20/4/16.
 */
public class ConversationsListAdapter extends RecyclerView.Adapter<ConversationsListAdapter.ConversationsListViewHolder> {

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
    public void onBindViewHolder(ConversationsListViewHolder holder, int position) {
        Conversation conversation = provider.getItem(position);
        holder.sender.setText(conversation.getSenderPhNo());
        holder.abstractConvo.setText(conversation.getAbstractConvo());
        holder.date.setText(conversation.getDate());
        Bitmap contactPhoto = conversation.getContact().getBitmapPhoto();
        if(contactPhoto != null) {
            Bitmap bitmap = conversation.getContact().getBitmapPhoto();
            holder.photo.setImageBitmap(BitmapUtils.getCircularBitmap(bitmap));
        } else {
            int width = holder.photo.getLayoutParams().width;
            int height = holder.photo.getLayoutParams().height;
            int bitmapWidth = (int) ConversionUtils.dpTopx(activity, width);
            int bitmapHeight = (int) ConversionUtils.dpTopx(activity, height);

            Bitmap bitmap;
            char c = conversation.getSenderPhNo().toUpperCase().charAt(0);
            if (Character.isLetter(c)){
                bitmap = BitmapUtils.getLetteredAvatar(activity, c, bitmapWidth, bitmapHeight);
            } else {
                bitmap = BitmapUtils.getLetteredAvatar(activity, '#', bitmapWidth, bitmapHeight);
            }
            holder.photo.setImageBitmap(bitmap);
        }
    }

    public static class ConversationsListViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView abstractConvo, sender, date;
        ImageView photo;

        public ConversationsListViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.conversationCard);
            sender = (TextView) itemView.findViewById(R.id.sender);
            abstractConvo = (TextView) itemView.findViewById(R.id.abstractConvo);
            date = (TextView) itemView.findViewById(R.id.date);
            photo = (ImageView) itemView.findViewById(R.id.contantPhoto);
        }
    }
}
