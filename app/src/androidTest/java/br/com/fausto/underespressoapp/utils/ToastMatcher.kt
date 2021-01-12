package br.com.fausto.underespressoapp.utils

import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

class ToastMatcher : TypeSafeMatcher<Root>() {
    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    override fun matchesSafely(item: Root): Boolean {
        val titleView = (item.decorView.layoutParams as WindowManager.LayoutParams).title
//        val type = item.windowLayoutParams.get().type;
        if (titleView == "Toast") {
            val windowToken = item.decorView.windowToken;
            val appToken = item.decorView.applicationWindowToken;
            if (windowToken == appToken) {
                return true;
            }
        }
        return false
    }
}