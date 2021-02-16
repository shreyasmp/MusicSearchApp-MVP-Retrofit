package shreyas.musicsearchapp.activities.search;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import shreyas.musicsearchapp.model.TrackSearchResults;

import static org.mockito.Mockito.verify;

/**
 * Created by shreyasmp on 9/8/17.
 * <p>
 * Track Search activity junit tests
 */

@RunWith(MockitoJUnitRunner.class)
public class TrackSearchActivityTest {

    private TrackSearchPresenter trackSearchPresenter;

    @Mock
    private TrackSearchView trackSearchView;

    @Before
    public void setup() throws IOException {
        trackSearchPresenter = new TrackSearchPresenter(trackSearchView);
    }

    @Test
    public void showError() throws IOException {
        trackSearchPresenter.showError();

        verify(trackSearchView).showErrorMessage();
    }

    @Test
    public void hideError() throws IOException {
        trackSearchPresenter.hideError();

        verify(trackSearchView).hideErrorMessage();
    }

    @Test
    public void clearSearch() throws IOException {
        trackSearchPresenter.clearSearch();

        verify(trackSearchView).clearSearchBox();
    }

    @Test
    public void setLoaderTrue() throws IOException {
        trackSearchPresenter.setLoader();

        verify(trackSearchView).setLoader(true);
    }

    @Test
    public void setLoaderFalse() throws IOException {
        trackSearchPresenter.setLoader();

        verify(trackSearchView).setLoader(false);
    }

    @Test
    public void setListVisibilityTrue() throws IOException {
        trackSearchPresenter.setListVisiblity();

        verify(trackSearchView).setTrackListVisibility(true);
    }

    @Test
    public void setListVisibilityFalse() throws IOException {
        trackSearchPresenter.setListVisiblity();

        verify(trackSearchView).setTrackListVisibility(false);
    }

    @Test
    public void showSearchList() throws IOException {
        trackSearchPresenter.showSearchList();

        TrackSearchResults results = null;

        verify(trackSearchView).showTrackResults(results);
    }
}
