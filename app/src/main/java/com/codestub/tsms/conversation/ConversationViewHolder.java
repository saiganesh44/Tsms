package com.codestub.tsms.conversation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.codestub.tsms.R;


/**
 * View holder for conversation bubble
 * Created by ganesh on 12/5/16.
 */
public class ConversationViewHolder extends RecyclerView.ViewHolder {

    TextView convoText;
    LinearLayout bubbleBackground;
    LinearLayout conversationLayout;

    public ConversationViewHolder(View itemView) {
        super(itemView);
        bubbleBackground = (LinearLayout) itemView.findViewById(R.id.bubbleBackground);
        convoText = (TextView) itemView.findViewById(R.id.convoText);
        conversationLayout = (LinearLayout) itemView.findViewById(R.id.conversationLayout);
    }
}
