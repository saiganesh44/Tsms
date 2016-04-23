package com.codestub.tsms.conversationslist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codestub.tsms.MainActivity;
import com.codestub.tsms.R;
import com.codestub.tsms.model.Conversation;

import java.util.List;

/**
 * This class lists the main conversationsList
 * Created by ganesh on 20/4/16.
 */
public class ConversationsListAdapter extends RecyclerView.Adapter<ConversationsListAdapter.ConversationsListViewHolder> {

    List<Conversation> conversationList;

    public ConversationsListAdapter(List<Conversation> conversationList) {
        this.conversationList = conversationList;
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    @Override
    public ConversationsListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.conversation_card, viewGroup, false);
        return new ConversationsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConversationsListViewHolder holder, int position) {
        holder.sender.setText(conversationList.get(position).getSenderPhNo());
        holder.abstractConvo.setText(conversationList.get(position).getAbstractConvo());
    }

    public static class ConversationsListViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView abstractConvo, sender;

        public ConversationsListViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.conversationCard);
            sender = (TextView) itemView.findViewById(R.id.sender);
            abstractConvo = (TextView) itemView.findViewById(R.id.abstractConvo);
        }
    }
}
