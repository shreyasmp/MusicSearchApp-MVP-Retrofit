package shreyas.musicsearchapp.rest.search;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shreyas.musicsearchapp.model.TrackSearchResults;

/**
 * Created by shreyasmp on 9/7/17.
 *
 * Search API based on retrofit
 */

public interface TrackSearchAPI {

    @GET("search/")
    Call<TrackSearchResults> getTrackSearchList(@Query("term") String term);
}
