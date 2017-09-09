package shreyas.musicsearchapp.rest.lyrics;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import shreyas.musicsearchapp.model.TrackLyrics;

/**
 * Created by shreyasmp on 9/7/17.
 *
 *  Retrofit based API for lyrics
 */

public interface TrackLyricsAPI {

    @GET("/api.php")
    Call<TrackLyrics> getTrackLyrics(@QueryMap Map<String, String> trackLyrics);
}
