package com.codestub.tsms.contact;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.codestub.tsms.utils.BitmapUtils;
import com.codestub.tsms.utils.ConversionUtils;

import java.lang.ref.WeakReference;

/**
 * This class loads the contact details in background
 * Created by ganesh on 7/6/16.
 */
public class ContactDetailsLoader {

    //Defines a tag for identifying log entries
    private static final String TAG = "ContactDetailsLoader";

    public void loadDetails(ContactViewHolder viewHolder, Contact contact) {
        ContactDetailsTask task = new ContactDetailsTask(viewHolder);
        viewHolder.setAsyncTaskReference(task);
        task.execute(contact);
    }

    private class ContactDetailsTask extends AsyncTask<Contact, Void, Contact> {

        private final WeakReference<ContactViewHolder> viewHolderReference;

        public ContactDetailsTask(ContactViewHolder viewHolder) {
            viewHolderReference = new WeakReference<>(viewHolder);
        }

        @Override
        protected Contact doInBackground(Contact... params) {
            Contact contact = params[0];
            //call getPhoneNumber and getBitmapPhoto to load them in background
            contact.getPhoneNumber();
            contact.getBitmapPhoto();
            contact.getCircularBitmapPhoto();
            if(contact.getCircularBitmapPhoto() == null) {
                int bitmapWidth = (int) ConversionUtils.dpTopx(contact.getContext(), 50);
                int bitmapHeight = (int) ConversionUtils.dpTopx(contact.getContext(), 50);

                char c = contact.getDisplayName().toUpperCase().charAt(0);
                Bitmap bitmap;
                if (Character.isLetter(c)){
                    bitmap = BitmapUtils.getLetteredAvatar(contact.getContext(), c, bitmapWidth, bitmapHeight);
                } else {
                    bitmap = BitmapUtils.getLetteredAvatar(contact.getContext(), '#', bitmapWidth, bitmapHeight);
                }
                contact.setCircularBitmapPhoto(bitmap);
            }
            return contact;
        }

        @Override
        protected void onPostExecute(Contact contact) {
            if(contact != null) {
                ContactViewHolder viewHolder = getAttachedContactViewHolder();
                if(viewHolder != null) {
                    viewHolder.number.setText(contact.getPhoneNumber());
                    viewHolder.contactPhoto.setImageBitmap(contact.getCircularBitmapPhoto());
                }
            }
        }

        private ContactViewHolder getAttachedContactViewHolder() {
            final ContactViewHolder viewHolder = viewHolderReference.get();
            if(viewHolder != null) {
                final ContactDetailsTask task = (ContactDetailsTask) viewHolder.getAsyncTask();
                if(this.equals(task)) {
                    return viewHolder;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

        @Override
        protected void onPreExecute() {
            ContactViewHolder viewHolder = getAttachedContactViewHolder();
            if(viewHolder != null) {
                viewHolder.number.setText("");
            }
        }
    }
}
