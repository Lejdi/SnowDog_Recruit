package dog.snow.androidrecruittest.tests

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.SplashActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SplashScreenTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun is_everything_visible()
    {
        Espresso.onView(withId(R.id.splash_motion_container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_logo_sd_symbol))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.iv_logo_sd_text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}