package shreyas.musicsearchapp.activities.search;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shreyas.musicsearchapp.model.TrackSearchResults;
import shreyas.musicsearchapp.rest.idling.EspressoIdling;
import shreyas.musicsearchapp.rest.search.TrackSearchServiceCall;

/**
 * Created by shreyasmp on 9/7/17.
 * <p>
 * Search presenter , this class does the actual service call and displays the track list to search activity
 */

public class TrackSearchPresenter implements TrackSearchActions {

    private static final String TAG = TrackSearchPresenter.class.getSimpleName();
    private final TrackSearchView trackSearchView;
    private final TrackSearchServiceCall serviceCall;
    private TrackSearchResults results;

    public TrackSearchPresenter(TrackSearchView trackSearchView) {
        this.trackSearchView = trackSearchView;
        this.serviceCall = new TrackSearchServiceCall();
    }

    // search for the track from artist name enter in text box
    @Override
    public void searchTrack(String search) {
        trackSearchView.setLoader(true);
        trackSearchView.hideErrorMessage();

        // Informing espresso that network call is triggered
        EspressoIdling.incrementCounter();

        // making a service call using track search api to fetch track list

        serviceCall.getClient()
                .getTrackSearchList(search)
                .enqueue(new Callback<TrackSearchResults>() {
                    @Override
                    public void onResponse(Call<TrackSearchResults> call, Response<TrackSearchResults> response) {

                        // decrementing counter for idle resource time
                        EspressoIdling.decrementCounter();
                        results = response.body();
                        if (results.getTracks().isEmpty()) {
                            trackSearchView.setTrackListVisibility(false);
                            trackSearchView.setLoader(false);
                            trackSearchView.showErrorMessage();
                        } else {
                            trackSearchView.setLoader(false);
                            trackSearchView.setTrackListVisibility(true);
                            trackSearchView.showTrackResults(results);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrackSearchResults> call, Throwable t) {
                        trackSearchView.setLoader(false);
                        trackSearchView.showErrorMessage();
                    }
                });

    }

    @Override
    public void showError() {
        trackSearchView.showErrorMessage();
    }

    @Override
    public void hideError() {
        trackSearchView.hideErrorMessage();
    }

    @Override
    public void clearSearch() {
        trackSearchView.clearSearchBox();
    }

    @Override
    public void setLoader() {
        trackSearchView.setLoader(true);
        trackSearchView.setLoader(false);
    }

    @Override
    public void setListVisiblity() {
        trackSearchView.setTrackListVisibility(true);
        trackSearchView.setTrackListVisibility(false);
    }

    @Override
    public void showSearchList() {
        trackSearchView.showTrackResults(results);
    }

}
