package shreyas.musicsearchapp.activities.lyrics;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shreyas.musicsearchapp.model.TrackDetails;
import shreyas.musicsearchapp.model.TrackLyrics;
import shreyas.musicsearchapp.rest.lyrics.TrackLyricServiceCall;

import static shreyas.musicsearchapp.utils.AppConstants.ACTION;
import static shreyas.musicsearchapp.utils.AppConstants.ARTIST;
import static shreyas.musicsearchapp.utils.AppConstants.FORMAT;
import static shreyas.musicsearchapp.utils.AppConstants.FUNCTION;
import static shreyas.musicsearchapp.utils.AppConstants.LYRICS;
import static shreyas.musicsearchapp.utils.AppConstants.LYRIC_FUNCTION;
import static shreyas.musicsearchapp.utils.AppConstants.NO_LYRICS_FOUND;
import static shreyas.musicsearchapp.utils.AppConstants.SONG;
import static shreyas.musicsearchapp.utils.AppConstants.XML;

/**
 * Created by shreyasmp on 9/7/17.
 *
 *  Lyrics Presenter class which does the actual service call to fetch the selected list item lyrics
 *  and display to activity. Also the json response from lyrics wikia is not the standard json.
 *  Hence xml format is considered for getting the required data for processing
 *
 */

public class TrackLyricsPresenter implements TrackLyricsActions {

    private static final String TAG = TrackLyricsPresenter.class.getSimpleName();
    private final TrackLyricsView trackLyricsView;
    private TrackDetails trackDetails;
    private TrackLyricServiceCall serviceCall;
    private TrackLyrics lyrics;

    public TrackLyricsPresenter(TrackLyricsView trackLyricsView, TrackDetails trackDetails) {
        this.trackLyricsView = trackLyricsView;
        this.serviceCall = new TrackLyricServiceCall();
        this.trackDetails = trackDetails;
    }

    @Override
    public void init() {
        trackLyricsView.displayLyricsViews(trackDetails);
        displayLyrics();
    }

    private void displayLyrics() {

        // Map used for creating a xml based url
        Map<String, String> lyricsMap = new HashMap<>();
        lyricsMap.put(ACTION, LYRICS);
        lyricsMap.put(ARTIST, trackDetails.getArtistName());
        lyricsMap.put(SONG, trackDetails.getTrackName());
        lyricsMap.put(FORMAT, XML);
        lyricsMap.put(FUNCTION, LYRIC_FUNCTION);

        // service call using the Lyrics API from retrofit
        serviceCall.getClient().getTrackLyrics(lyricsMap).enqueue(new Callback<TrackLyrics>() {
            @Override
            public void onResponse(Call<TrackLyrics> call, Response<TrackLyrics> response) {
                lyrics = response.body();
                if(lyrics.getLyrics().equalsIgnoreCase(NO_LYRICS_FOUND)) {
                    trackLyricsView.displayLyricsNotFound(lyrics);
                } else {
                    trackLyricsView.displayLyrics(lyrics);
                }
            }

            @Override
            public void onFailure(Call<TrackLyrics> call, Throwable t) {
                trackLyricsView.displayErrorMessage();
            }
        });
    }

    @Override
    public void showErrorMessage() {
        trackLyricsView.displayErrorMessage();
    }

    @Override
    public void showLyrics() {
        trackLyricsView.displayLyrics(lyrics);
    }

    @Override
    public void showLyricsNotFound() {
        trackLyricsView.displayLyricsNotFound(lyrics);
    }

    @Override
    public void showLyricsOnViews() {
        trackLyricsView.displayLyricsViews(null);
    }

}
