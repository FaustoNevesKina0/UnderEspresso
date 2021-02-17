package br.com.fausto.underespressoapp.mainactivitytest

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.fausto.underespressoapp.tools.EspressoCounter
import br.com.fausto.underespressoapp.MainActivity
import br.com.fausto.underespressoapp.R
import br.com.fausto.underespressoapp.SecondaryActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IntentsTest {

    @get:Rule
    val activityToBeTested: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun startCounting() {
        IdlingRegistry.getInstance().register(EspressoCounter.countingIdlingResource)
    }

    @After
    fun stopCounting() {
        IdlingRegistry.getInstance().unregister(EspressoCounter.countingIdlingResource)
    }

    @Test
    fun startSecondaryActivity() {
        Intents.init()
        Espresso.onView(withId(R.id.textToSend)).perform(ViewActions.typeText("123456-abcdefg"))
        Espresso.closeSoftKeyboard()
        val matcher: Matcher<Intent> =
            IntentMatchers.hasComponent(SecondaryActivity::class.java.name)
        val result: Instrumentation.ActivityResult =
            Instrumentation.ActivityResult(Activity.RESULT_OK, null)
        Intents.intending(matcher).respondWith(result)
        Espresso.onView(withId(R.id.btnIntent))
            .perform(click())
        Intents.intended(matcher)
        Intents.release()
    }
}