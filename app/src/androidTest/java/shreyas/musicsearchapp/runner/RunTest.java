package shreyas.musicsearchapp.runner;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import shreyas.musicsearchapp.activities.search.TrackSearchActivity;
import shreyas.musicsearchapp.espressorobot.Robot;

/**
 * Created by shreyasmp on 9/8/17.
 *
 *  Class to run UI Tests on the app, with TDD approach used for developing this app.
 *  Have made use of reusable test events from Robot class for performing various tests.
 *  Since there is a overhead of network call, Espresso has to be informed to sync actions
 *  when performing tests
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RunTest {

    @Rule
    public IntentsTestRule<TrackSearchActivity> searchActivityTrackerTestRule =
            new IntentsTestRule<>(TrackSearchActivity.class);

    private Robot robo = new Robot();

    // Register the idling resource before start of tests
    @Before
    public void registerResource() {
        Espresso.registerIdlingResources(searchActivityTrackerTestRule.getActivity().getIdlingResource());
    }

    @SmallTest
    public void initialScreenShow() {
        robo.progressBarHidden().hideList();
    }

    // Test to run with specified artisit name and click on first item in list

    @Test
    public void searchArtist() {
                robo
                .typeInSearchBox("Linkin Park")
                .clickSearch()
                .listDisplayed()
                        .clickOnChildItem();
    }

    // Test to run with specified unknown text and check for error message and its text

    @Test
    public void searchSomething() {
                robo.progressBarHidden()
                .typeInSearchBox("#$@#$$@$@$@")
                .clickSearch()
                .progressBarHidden()
                .errorDisplayed()
                .listErrorMessage();
    }

    // Test to enter specified artist name and clear search box and enter a new artist name
    // And select first item from list

    @Test
    public void checkClearAndSearch(){
        robo.typeInSearchBox("Led Zeppelin")
                .clickClearSearch()
                .typeInSearchBox("Pink Floyd")
                .clickSearch()
                .listDisplayed()
                .clickOnChildItem();
    }

    // Test to run with specified artisit name and click on first item in list

    @Test
    public void clickOnListChildItem() {
        robo.typeInSearchBox("Breaking Benjamin")
                .clickSearch()
                .listDisplayed()
                .clickOnChildItem();
    }

    // Unregister the idling resource after tests

    @After
    public void unRegisterIdlingResource() {
        Espresso.unregisterIdlingResources(searchActivityTrackerTestRule.getActivity().getIdlingResource());
    }

}
