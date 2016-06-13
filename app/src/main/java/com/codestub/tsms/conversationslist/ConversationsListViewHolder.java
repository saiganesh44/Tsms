package com.codestub.tsms.conversationslist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codestub.tsms.R;

/**
 * This class holds the template of Conversations list view card
 * Created by ganesh on 3/5/16.
 */
public class ConversationsListViewHolder extends RecyclerView.ViewHolder{
    CardView cv;
    TextView abstractConvo, sender, date;
    ImageView photo;
    RelativeLayout conversationsListMsgLayout;

    public ConversationsListViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.conversationCard);
        sender = (TextView) itemView.findViewById(R.id.sender);
        abstractConvo = (TextView) itemView.findViewById(R.id.abstractConvo);
        date = (TextView) itemView.findViewById(R.id.date);
        photo = (ImageView) itemView.findViewById(R.id.senderPhoto);
        conversationsListMsgLayout = (RelativeLayout) itemView.findViewById(R.id.conversationsListMsgLayout);
    }
}
