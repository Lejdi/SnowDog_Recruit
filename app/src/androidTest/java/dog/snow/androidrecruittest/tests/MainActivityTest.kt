package dog.snow.androidrecruittest.tests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dog.snow.androidrecruittest.MainActivity
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.repository.AlbumRepository
import dog.snow.androidrecruittest.repository.PhotoRepository
import dog.snow.androidrecruittest.repository.UserRepository
import dog.snow.androidrecruittest.util.NetworkTools
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@HiltAndroidTest
class MainActivityTest {
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var userRepository: UserRepository

    @Inject
    lateinit var albumRepository: AlbumRepository

    @Inject
    lateinit var photoRepository: PhotoRepository

    @Before
    fun init(){
        hiltRule.inject()
        runBlocking {
            withContext(Dispatchers.IO){
                photoRepository.getAllFromNetwork()
                albumRepository.getAllFromNetwork()
                userRepository.getAllFromNetwork()
            }
        }
    }

    @Test
    fun is_everything_visible()
    {
        Espresso.onView(withId(R.id.main_activity_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.include_appbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.coordinator))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.container))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_items))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.et_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        if(!NetworkTools.isInternetAvailable(InstrumentationRegistry.getInstrumentation().targetContext)){
            Espresso.onView(withId(R.id.banner))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
        else{
            Espresso.onView(withId(R.id.banner))
                .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
        }
    }

    @Test
    fun navigate_to_details_test(){
        Espresso.onView(withId(R.id.rv_items))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_items))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        Espresso.onView(withId(R.id.iv_photo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_photo_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_username))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        pressBack()
        Espresso.onView(withId(R.id.rv_items))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun filter_test(){
        Espresso.onView(withId(R.id.et_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.et_search)).perform(ViewActions.typeText("testowanko"))
        Espresso.onView(withId(R.id.tv_empty))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.et_search)).perform(ViewActions.replaceText(""))
        Espresso.onView(withId(R.id.tv_empty))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }
}