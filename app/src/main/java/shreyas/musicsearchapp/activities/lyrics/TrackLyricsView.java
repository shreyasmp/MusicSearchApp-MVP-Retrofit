package shreyas.musicsearchapp.activities.lyrics;

import shreyas.musicsearchapp.model.TrackDetails;
import shreyas.musicsearchapp.model.TrackLyrics;

/**
 * Created by shreyasmp on 9/7/17.
 *
 *  View interface for lyrics screen
 */

public interface TrackLyricsView {

    void displayLyrics(TrackLyrics lyrics);

    void displayErrorMessage();

    void displayLyricsNotFound(TrackLyrics lyrics);

    void displayLyricsViews(TrackDetails trackDetails);

}
