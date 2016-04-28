package com.codestub.tsms.conversationslist;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codestub.tsms.R;


/**
 * This class lists the main conversationsList
 * Created by ganesh on 20/4/16.
 */
public class ConversationsListAdapter extends RecyclerView.Adapter<ConversationsListAdapter.ConversationsListViewHolder> {

    ConversationsListProvider provider;

    public ConversationsListAdapter(Activity activity) {
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
    }

    public static class ConversationsListViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView abstractConvo, sender, date;

        public ConversationsListViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.conversationCard);
            sender = (TextView) itemView.findViewById(R.id.sender);
            abstractConvo = (TextView) itemView.findViewById(R.id.abstractConvo);
            date = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
