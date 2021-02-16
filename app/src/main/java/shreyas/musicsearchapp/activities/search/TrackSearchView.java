package shreyas.musicsearchapp.activities.search;

import shreyas.musicsearchapp.model.TrackSearchResults;

/**
 * Created by shreyasmp on 9/7/17.
 * <p>
 * View interface for Search screen
 */

public interface TrackSearchView {

    void showTrackResults(TrackSearchResults results);

    void setTrackListVisibility(boolean visibility);

    void setLoader(boolean loader);

    void showErrorMessage();

    void hideErrorMessage();

    void clearSearchBox();
}
