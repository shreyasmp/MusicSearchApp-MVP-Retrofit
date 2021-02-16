package shreyas.musicsearchapp.activities.lyrics;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import shreyas.musicsearchapp.R;
import shreyas.musicsearchapp.databinding.LyricsActivityBinding;
import shreyas.musicsearchapp.model.TrackDetails;
import shreyas.musicsearchapp.model.TrackLyrics;

import static shreyas.musicsearchapp.utils.AppConstants.NO_LYRICS_FOUND;
import static shreyas.musicsearchapp.utils.AppConstants.PATTERN_SQUARE_BRACKET;
import static shreyas.musicsearchapp.utils.AppConstants.TRACK_LYRICS;

/**
 * Created by shreyasmp on 9/7/17.
 * <p>
 * Lyrics Activity to display the album image, artist name , track name and lyrics
 * Lyrics here is not complete may be due to copyright issues.
 * What i have done here is, have provided a linkify to ellipsis in lyrics screen on clicking
 * takes the user to default intent api which is browser and opens up the entire lyrics page
 */

public class TrackLyricsActivity extends AppCompatActivity implements TrackLyricsView {

    private static final String TAG = TrackLyricsActivity.class.getSimpleName();
    private LyricsActivityBinding binding;

    private TrackLyricsActions actions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Data binding removes all the fuss of boiler plate code like find view by
        binding = DataBindingUtil.setContentView(this, R.layout.lyrics_activity);

        // introduced tool bar to navigate better back to list screen
        binding.lyricsToolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(binding.lyricsToolbar);

        // display back arrow for toolbar which navigates back to search activity
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material, null);
        upArrow.setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Parse and process gson xml track lyrics and details to display to activity
        TrackDetails trackDetails = new Gson().fromJson(getIntent().getStringExtra(TRACK_LYRICS), TrackDetails.class);
        actions = new TrackLyricsPresenter(this, trackDetails);
        actions.init();
    }

    // Set lyrics to view on lyrics activity and linkify ellipsis to url
    @Override
    public void displayLyrics(final TrackLyrics lyrics) {
        binding.trackLyrics.setText(lyrics.getLyrics());
        Linkify.TransformFilter filter = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher match, String url) {
                return lyrics.getUrl();
            }
        };

        Pattern pattern = Pattern.compile(PATTERN_SQUARE_BRACKET);
        String scheme = "";
        Linkify.addLinks(binding.trackLyrics, pattern, scheme, null, filter);
    }

    // display error message

    @Override
    public void displayErrorMessage() {
        binding.trackLyrics.setText(NO_LYRICS_FOUND);
    }

    // show lyrics not found if it was not found for particular track

    @Override
    public void displayLyricsNotFound(TrackLyrics lyrics) {
        binding.trackLyrics.setText(lyrics.getLyrics());
    }

    // show lyrics on views as required with artist name, album name, track name and lyrics

    @Override
    public void displayLyricsViews(TrackDetails trackDetails) {
        Picasso.with(this).load(trackDetails.getArtworkUrl100()).into(binding.artistAlbumImage);
        binding.trackName.setText(trackDetails.getTrackName());
        binding.artistName.setText(trackDetails.getArtistName());
        binding.albumName.setText(trackDetails.getCollectionName());
    }

    // navigate back button on toolbar takes to previous search activity

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
