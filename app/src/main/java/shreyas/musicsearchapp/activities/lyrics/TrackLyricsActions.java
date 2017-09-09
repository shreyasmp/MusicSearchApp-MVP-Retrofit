package shreyas.musicsearchapp.activities.lyrics;

/**
 * Created by shreyasmp on 9/7/17.
 *
 *  Actions interface for lyrics screen
 *
 */

public interface TrackLyricsActions {

    void init();

    void showErrorMessage();

    void showLyrics();

    void showLyricsNotFound();

    void showLyricsOnViews();
}
