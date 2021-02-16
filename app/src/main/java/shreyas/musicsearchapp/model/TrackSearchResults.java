package shreyas.musicsearchapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by shreyasmp on 9/7/17.
 * <p>
 * Track search results of outer response model
 */

public class TrackSearchResults {

    @SerializedName("resultCount")
    private int resultCount;

    @SerializedName("results")
    private ArrayList<TrackDetails> tracks = null;

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public ArrayList<TrackDetails> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<TrackDetails> tracks) {
        this.tracks = tracks;
    }
}
