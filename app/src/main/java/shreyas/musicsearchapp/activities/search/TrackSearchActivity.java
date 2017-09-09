package shreyas.musicsearchapp.activities.search;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.gson.Gson;

import shreyas.musicsearchapp.R;
import shreyas.musicsearchapp.activities.lyrics.TrackLyricsActivity;
import shreyas.musicsearchapp.adapter.TrackResultsAdapter;
import shreyas.musicsearchapp.databinding.SearchActivityBinding;
import shreyas.musicsearchapp.listener.TrackOnClickListener;
import shreyas.musicsearchapp.listener.TrackTextOnFocusListener;
import shreyas.musicsearchapp.model.TrackDetails;
import shreyas.musicsearchapp.model.TrackSearchResults;
import shreyas.musicsearchapp.rest.idling.EspressoIdling;
import shreyas.musicsearchapp.watcher.TrackTextWatcher;

import static android.view.View.VISIBLE;
import static shreyas.musicsearchapp.utils.AppConstants.TRACK_LYRICS;

/**
 * Created by shreyasmp on 9/7/17.
 *
 * Search activity used for typing artist or track name which hits the apple music itunes api
 * to get the list of all the songs related to that artist or song related.
 *  TDD approach using MVP model
 *
 */

public class TrackSearchActivity extends AppCompatActivity implements TrackSearchView, TrackOnClickListener{

    private static final String TAG = TrackSearchActivity.class.getSimpleName();
    private SearchActivityBinding binding;
    private TrackSearchActions trackSearchActions;
    private TrackResultsAdapter trackResultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trackSearchActions = new TrackSearchPresenter(this);
        binding = DataBindingUtil.setContentView(this, R.layout.search_activity);

        displayTracksViews();
        clearSearchBox();
    }

    // show tracks list

    private void displayTracksViews() {
        binding.searchToolbar.setTitle(getResources().getString(R.string.app_name));
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trackSearchActions.searchTrack(binding.musicSearchField.getText().toString());
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });

        binding.musicSearchField.setText("");
        binding.musicSearchList.setVisibility(VISIBLE);
        binding.progressBar.setVisibility(View.GONE);
        binding.searchErrorMessage.setVisibility(View.GONE);

        // show divider line between each list items

        binding.musicSearchList.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.musicSearchList.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.musicSearchList.getContext(), layoutManager.VERTICAL);
        binding.musicSearchList.addItemDecoration(dividerItemDecoration);

        // set adapter

        trackResultsAdapter = new TrackResultsAdapter(getApplicationContext(), this);
        binding.musicSearchList.setAdapter(trackResultsAdapter);

    }

    // on click of any list item transition to next activity where lyrics are shown

    @Override
    public void onItemClick(TrackDetails trackDetails) {
        Intent intent = new Intent(this, TrackLyricsActivity.class);
        intent.putExtra(TRACK_LYRICS, new Gson().toJson(trackDetails));
        startActivity(intent);
    }

    // show track results in list

    @Override
    public void showTrackResults(TrackSearchResults results) {
        if(results != null) {
            trackResultsAdapter.setTrackDetailsArrayListData(results.getTracks());
            binding.musicSearchList.scrollToPosition(0);
        }
    }

    // set track list visibility if tracks are found only

    @Override
    public void setTrackListVisibility(boolean visibility) {
        if(visibility) {
            binding.musicSearchList.setVisibility(VISIBLE);
        } else {
            binding.musicSearchList.setVisibility(View.INVISIBLE);
        }
    }

    // show progress loader if network call is made to fetch the track list

    @Override
    public void setLoader(boolean loader) {
        if(loader) {
            binding.progressBar.setVisibility(VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    // show error message if network call fails

    @Override
    public void showErrorMessage() {
        binding.searchErrorMessage.setVisibility(VISIBLE);
    }

    // hide error message

    @Override
    public void hideErrorMessage() {
        binding.searchErrorMessage.setVisibility(View.INVISIBLE);
    }


    // clear button at search field for clearing the input based on focus listener and text watcher

    @Override
    public void clearSearchBox() {
        binding.musicSearchField.setOnFocusChangeListener(new TrackTextOnFocusListener(this, binding.musicSearchField, binding.clearSearch));

        binding.clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.musicSearchField.getText().clear();
            }
        });

        binding.musicSearchField.addTextChangedListener(new TrackTextWatcher(this, binding.musicSearchField, binding.clearSearch));
    }

    // resource idling for espresso to make espresso know to sync between calls

    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        return EspressoIdling.getIdlingResource();
    }
}
