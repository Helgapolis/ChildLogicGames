package ru.bazalikova.childlogicgames.menu.presentation

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.fifteen.presentation.FifteenActivity
import ru.bazalikova.menu.presentation.MenuActivity

@RunWith(AndroidJUnit4::class)
class FifteenActivityTest {
    @get:Rule
    val menuActivityRule: ActivityTestRule<MenuActivity> =
        ActivityTestRule(MenuActivity::class.java)

    @get:Rule
    val activityRule: ActivityTestRule<FifteenActivity> =
        ActivityTestRule(FifteenActivity::class.java)

    @Test
    fun shouldDisplayHeaderTextView() {
        activityRule.launchActivity(Intent())
        val context = activityRule.activity
        Espresso.onView(withId(R.id.actFifteenHeaderTextView)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    withText(context.getString(R.string.act_fifteen_header)),
                    isDisplayed()
                )
            )
        )
    }

    @Test
    fun shouldDisplayField() {
        activityRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.actFifteenFieldLayout)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun shouldDisplayCancelButton() {
        activityRule.launchActivity(Intent())
        val context = activityRule.activity
        Espresso.onView(withId(R.id.actFifteenCancelButton)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    withText(context.getString(R.string.btn_cancel)),
                    isDisplayed(),
                    isClickable()
                )
            )
        )
    }

    @Test
    fun shouldCloseFifteenActivity() {
        menuActivityRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.activityFifteenButton)).perform(click())
        Espresso.onView(withId(R.id.actFifteenCancelButton)).perform(click())
        val context = menuActivityRule.activity
        Espresso.onView(withId(R.id.activityFifteenText)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    withText(context.getString(R.string.act_menu_fifteen)),
                    isDisplayed()
                )
            )
        )
    }
}