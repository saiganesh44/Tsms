package com.codestub.tsms.conversation;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.codestub.tsms.R;

/**
 * Adapter class for conversation among two entities
 * Created by ganesh on 8/5/16.
 */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationViewHolder>{

    ConversationProvider provider;
    Activity activity;

    public ConversationAdapter(Activity activity, String threadID) {
        this.activity = activity;
        provider = new ConversationProvider(activity, threadID);
    }

    @Override
    public ConversationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.conversation, viewGroup, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationViewHolder holder, int position) {
        ConversationBubble bubble = provider.getItem(position);
        setAlignment(holder, bubble.isMe);
        holder.convoText.setText(bubble.getBody());
        holder.convoText.setTextColor(Color.WHITE);
    }

    private void setAlignment(ConversationViewHolder holder, boolean isMe) {
        holder.bubbleBackground.setBackgroundResource(R.drawable.messagebubble);

        LinearLayout.LayoutParams bubbleLayoutParams = (LinearLayout.LayoutParams) holder.bubbleBackground.getLayoutParams();

        RelativeLayout.LayoutParams relativeLayoutParams = (RelativeLayout.LayoutParams) holder.conversationLayout.getLayoutParams();
        if(isMe) {
            bubbleLayoutParams.gravity = Gravity.START;
            relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.conversationLayout.setPadding(195,1,5,1);
        } else {
            bubbleLayoutParams.gravity = Gravity.END;
            relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.conversationLayout.setPadding(5,1,195,1);
        }
        holder.bubbleBackground.setLayoutParams(bubbleLayoutParams);
        holder.conversationLayout.setLayoutParams(relativeLayoutParams);
    }

    @Override
    public int getItemCount() {
        return provider.size();
    }
}
