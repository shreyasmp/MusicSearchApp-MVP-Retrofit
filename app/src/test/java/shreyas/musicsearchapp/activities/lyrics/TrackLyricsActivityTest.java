package shreyas.musicsearchapp.activities.lyrics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import shreyas.musicsearchapp.model.TrackDetails;
import shreyas.musicsearchapp.model.TrackLyrics;

import static org.mockito.Mockito.verify;

/**
 * Created by shreyasmp on 9/8/17.
 * <p>
 * Track Lyrics Activity Test including Junit test cases
 */

@RunWith(MockitoJUnitRunner.class)
public class TrackLyricsActivityTest {

    private TrackLyricsPresenter trackLyricsPresenter;

    @Mock
    private TrackLyricsView trackLyricsView;
    @Mock
    private TrackDetails trackDetails;

    @Before
    public void setup() throws IOException {
        trackLyricsPresenter = new TrackLyricsPresenter(trackLyricsView, trackDetails);
    }

    @Test
    public void showErrorMessage() throws IOException {
        trackLyricsPresenter.showErrorMessage();

        verify(trackLyricsView).displayErrorMessage();
    }

    @Test
    public void showLyrics() throws IOException {
        trackLyricsPresenter.showLyrics();

        TrackLyrics lyrics = null;
        verify(trackLyricsView).displayLyrics(lyrics);
    }

    @Test
    public void showLyricsNotFound() throws IOException {
        trackLyricsPresenter.showLyricsNotFound();

        TrackLyrics lyrics = null;
        verify(trackLyricsView).displayLyricsNotFound(lyrics);
    }

    @Test
    public void showLyricsOnViews() throws IOException {
        trackLyricsPresenter.showLyricsOnViews();

        TrackDetails trackDetails = null;
        verify(trackLyricsView).displayLyricsViews(trackDetails);
    }
}
