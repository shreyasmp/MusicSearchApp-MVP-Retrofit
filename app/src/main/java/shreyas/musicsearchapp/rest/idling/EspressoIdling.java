package shreyas.musicsearchapp.rest.idling;

import android.support.test.espresso.IdlingResource;

/**
 * Created by shreyasmp on 9/8/17.
 *
 *  This class holds the counter and idle resource tracking for tests that are run
 *  Reference {@link IdlingResource}
 */

public class EspressoIdling {

    public static TrackIdlingResource trackIdlingResource = new TrackIdlingResource("TRACK");

    public static void incrementCounter() {
        trackIdlingResource.incrementCounter();
    }

    public static void decrementCounter() {
        trackIdlingResource.decrementCounter();
    }

    public static IdlingResource getIdlingResource() {
        return trackIdlingResource;
    }
}
