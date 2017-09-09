package shreyas.musicsearchapp.activities.search;

/**
 * Created by shreyasmp on 9/7/17.
 *
 * Actions interface for Search screen
 *
 */

public interface TrackSearchActions {

    void searchTrack(String search);

    void showError();

    void hideError();

    void clearSearch();

    void setLoader();

    void setListVisiblity();

    void showSearchList();
}
