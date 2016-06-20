package com.codestub.tsms.contact;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codestub.tsms.R;

/**
 * Adapter class for Contacts list
 * Created by ganesh on 28/5/16.
 */
public class ContactsAdapter extends CursorAdapter {

    private LayoutInflater mInflater;
    private ContactDetailsLoader mDetailsLoader;

    public ContactsAdapter(Context context, ContactDetailsLoader detailsLoader) {
        super(context, null, 0);

        //Initializing inflater obj
        mInflater = LayoutInflater.from(context);
        mDetailsLoader = detailsLoader;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        final View itemLayout = mInflater.inflate(R.layout.contact_list_item, viewGroup, false);

        final ContactViewHolder viewHolder = new ContactViewHolder();
        viewHolder.displayName = (TextView) itemLayout.findViewById(R.id.display_name);
        viewHolder.number = (TextView) itemLayout.findViewById(R.id.number);
        viewHolder.contactPhoto = (ImageView) itemLayout.findViewById(R.id.contactPhoto);
        viewHolder.contactSelectedIcon = (ImageView) itemLayout.findViewById(R.id.contactSelectedIcon);

        itemLayout.setTag(viewHolder);
        return itemLayout;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        final ContactViewHolder viewHolder = (ContactViewHolder) view.getTag();
        Contact contact = new Contact(context, cursor);

        viewHolder.displayName.setText(contact.getDisplayName());

        //load the contact details
        mDetailsLoader.loadDetails(viewHolder, contact);
    }
}
