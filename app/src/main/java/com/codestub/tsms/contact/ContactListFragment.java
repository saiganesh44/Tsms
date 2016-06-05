package com.codestub.tsms.contact;

import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.codestub.tsms.R;
import com.codestub.tsms.utils.BuildUtils;

/**
 * Class for Contacts list
 * Created by ganesh on 28/5/16.
 */
public class ContactListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    // Defines a tag for identifying log entries
    private static final String TAG = "ContactListFragment";

    private String mSearchTerm;
    private ContactsAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate called");
        super.onCreate(savedInstanceState);

        // Let the fragment know that it supports menu items
        setHasOptionsMenu(true);

        mAdapter = new ContactsAdapter(getActivity());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.d(TAG,"onCreateOptionsMenu called");
        // Inflate the menu items
        inflater.inflate(R.menu.contact_list_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);

        // Retrive the SearchView  from search menu item
        final SearchView searchView = (SearchView) searchItem.getActionView();

        // Retrive the system search manager service
        final SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        // Assign the searchable info to searchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                // Nothing to happen on user search submit
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // When actionbar search text is changed, update the search filter
                // and restart the loader to do a new query using the new string
                String newFilter = TextUtils.isEmpty(newText) ? null : newText;

                // when searchTerm and newFilter are null, don't do anything
                if(mSearchTerm == null && newFilter == null) {
                    return true;
                }

                // when newFilter equals the search term don't do anything
                if(mSearchTerm != null && mSearchTerm.equals(newFilter)) {
                    return true;
                }

                mSearchTerm = newFilter;
                getLoaderManager().restartLoader(ContactsQuery.QUERY_ID, null, ContactListFragment.this);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG,"onOptionsItemSelected called");
        switch (item.getItemId()) {
            case R.id.menu_search :
                // On platforms earlier than 3.0 triggers the search activity
                if(!BuildUtils.hasHoneyComb()) {
                    getActivity().onSearchRequested();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView called");
        return inflater.inflate(R.layout.contact_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated called");
        super.onActivityCreated(savedInstanceState);

        setListAdapter(mAdapter);
        getLoaderManager().initLoader(ContactsQuery.QUERY_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader called");

        //if this is the loader for finding contacts in the contacts provider
        if(id == ContactsQuery.QUERY_ID) {
            Uri contentUri;

            //There are two types of queries, one which displays all contacts and
            // one which filters contacts by a search query. if mSearchTerm is set
            // then a search term has been entered and the later should be used
            if(mSearchTerm == null) {
                contentUri = ContactsQuery.CONTENT_URI;
            } else {
                contentUri = Uri.withAppendedPath(ContactsQuery.FILTER_URI, Uri.encode(mSearchTerm));
            }

            return new CursorLoader(getActivity(), contentUri,
                    ContactsQuery.PROJECTION, ContactsQuery.SELECTION, null, ContactsQuery.SORT_ORDER);
        }
        Log.e(TAG, "onCreateLoader: Incorrect id provided (" + id + ")");
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished called");

        // This swaps the new cursor into the adapter
        if(loader.getId() == ContactsQuery.QUERY_ID) {
            mAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderRest called");

        // When the loader is being reset, clear the cursor from the adapter.
        // This allows the cursor resources to be freed.
        if(loader.getId() == ContactsQuery.QUERY_ID) {
            mAdapter.swapCursor(null);
        }
    }


}
