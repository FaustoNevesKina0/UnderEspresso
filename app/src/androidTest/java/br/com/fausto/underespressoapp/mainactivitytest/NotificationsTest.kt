package br.com.fausto.underespressoapp.mainactivitytest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.fausto.underespressoapp.MainActivity
import br.com.fausto.underespressoapp.R
import br.com.fausto.underespressoapp.utils.ToastMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotificationsTest {

    @get:Rule
    val activityToBeTested: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun launch_dialog() {
        onView(withId(R.id.btnDialog)).perform(click())
        onView(withId(R.id.alertDialogText))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun launch_toast() {
        onView(withId(R.id.btnToast)).perform(click())
        onView(withText("This is a Toast message!")).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}