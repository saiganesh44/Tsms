package com.codestub.tsms.contact;

import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.codestub.tsms.utils.BuildUtils;

/**
 * This class contains the fields and queries required in querying contacts
 * Created by ganesh on 28/5/16.
 */
public interface ContactsQuery {

    // an identifier for the loader
    int QUERY_ID = 1;

    // A content URI for the contacts table
    Uri CONTENT_URI = Contacts.CONTENT_URI;

    // The search/filter query uri.
    Uri FILTER_URI = Contacts.CONTENT_FILTER_URI;

    // The desired sort order for the returned cursor. In android 3.0 and later, the primary sort key
    // allows for localization. In earlier versions use display name as the sort key.
    String SORT_ORDER = BuildUtils.hasHoneyComb() ? Contacts.SORT_KEY_PRIMARY : Contacts.DISPLAY_NAME;

    // The selection clause for the CursorLoader query. The search criteria defined here restricts results to contacts
    // that have a display name and are linked to visible groups. Notice that the search on the strings provided by
    // the user is implemented by appending the search string to CONTENT_FILTER_URI.
    // Having HAS_PHONE_NUMBER check in selection would return the contacts which have phone number
    String SELECTION = (BuildUtils.hasHoneyComb() ? Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME) + "<>''"
            + " AND " + Contacts.HAS_PHONE_NUMBER + "=1";

    // The Projection for the CursorLoader Query. This is the list of columns that the Contacts
    // provider should return in the cursor.
    String[] PROJECTION = {

            //the contacts row id
            Contacts._ID,

            //A pointer to the contact that is guaranteed to be more permanent than _ID.
            //Given a contact's current _ID value and LOOKUP_KEY, the contacts provider
            // can generate a permanent contact URI
            Contacts.LOOKUP_KEY,

            //In platform version 3.0 and later, the Contacts table contains DISPLAY_NAME_PRIMARY,
            //which either contains the contact's displayable name or some other userful identifier
            //such as an email address. This column isn't available in earlier version of android.
            // so you must use DISPLAY_NAME instead
            BuildUtils.hasHoneyComb() ? Contacts.DISPLAY_NAME_PRIMARY : Contacts.DISPLAY_NAME,

            Contacts.HAS_PHONE_NUMBER,

            //In android 3.0 and later, the thumbnail image is pointed to by PHOTO_THUMBNAIL_URI.
            // In earlier versions, there is no direct pointer: instead you generate the pointer from
            // the contact's ID value and constants defined in android.provider.ContactsContract.Contacts
            BuildUtils.hasHoneyComb() ? Contacts.PHOTO_THUMBNAIL_URI : Contacts._ID,

            // The sort order column for the returned cursor, used by the AlphabetIndexer
            SORT_ORDER

    };

    // The query column numbers which map to each value in the projection
    int ID = 0;
    int LOOKUP_KEY = 1;
    int DISPLAY_NAME = 2;
    int HAS_PHONE_NUMBER = 3;
    int PHOTO_THUMBNAIL_DATA = 4;
    int SORT_KEY = 5;
}
