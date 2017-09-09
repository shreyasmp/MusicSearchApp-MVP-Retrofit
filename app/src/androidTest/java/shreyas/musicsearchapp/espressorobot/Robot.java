package shreyas.musicsearchapp.espressorobot;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;

import shreyas.musicsearchapp.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by shreyasmp on 9/8/17.
 *
 * Class Robot used for creating espresso based test events that perform text match and click actions
 * The reusable components written here can be used for multiple combination of tests for each test cases
 * TDD approach is used here for the app
 *
 */

public class Robot {

    public Robot typeInSearchBox(String search) {
        onView(withId(R.id.music_search_field)).perform(typeText(search),
                ViewActions.closeSoftKeyboard());
        return this;
    }

    public Robot clickSearch() {
        onView(withId(R.id.search_button)).perform(click());
        return this;
    }

    public Robot clickClearSearch() {
        onView(withId(R.id.clear_search)).perform(click());
        return  this;
    }

    public Robot progressBarHidden(){
        onView(withId(R.id.progress_bar)).check(ViewAssertions.matches(not(isDisplayed())));
        return this;
    }

    public Robot errorDisplayed() {
        onView(withId(R.id.search_error_message)).check(ViewAssertions.matches(isDisplayed()));
        return this;
    }

    public Robot listErrorMessage() {
        onView(withId(R.id.search_error_message)).check(ViewAssertions.matches(withText(R.string.error_results)));
        return this;
    }

    public Robot listDisplayed() {
        onView(withId(R.id.music_search_list)).check(ViewAssertions.matches(isDisplayed()));
        return this;
    }

    public Robot hideList() {
        onView(withId(R.id.music_search_list)).check(ViewAssertions.matches(not(isDisplayed())));
        return this;
    }

    public Robot clickOnChildItem() {
        onView(withId(R.id.music_search_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        return this;
    }
}
