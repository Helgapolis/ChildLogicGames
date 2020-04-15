package ru.bazalikova.childlogicgames.menu.presentation

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.menu.presentation.MenuActivity

@RunWith(AndroidJUnit4::class)
class MenuActivityTest {
    @get:Rule
    val activityRule: ActivityTestRule<MenuActivity> = ActivityTestRule(MenuActivity::class.java)

    @Test
    fun shouldDisplayPuzzleItem() {
        activityRule.launchActivity(Intent())

        val context = activityRule.activity
        Espresso.onView(withId(R.id.activityPuzzleText)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.act_menu_count_btn)),
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }

    @Test
    fun shouldDisplayTangramItem() {
        activityRule.launchActivity(Intent())

        val context = activityRule.activity
        Espresso.onView(withId(R.id.activityTangramText)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.act_menu_tangram)),
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }

    @Test
    fun shouldDisplayFifteenItem() {
        activityRule.launchActivity(Intent())

        val context = activityRule.activity
        Espresso.onView(withId(R.id.activityFifteenText)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.act_menu_fifteen)),
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }

    @Test
    fun shouldOpenPuzzleActivity() {
        activityRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.activityPuzzleButton)).perform(click())
        Espresso.onView(withId(R.id.actPuzzleExprTxt)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun shouldOpenTangramActivity() {
        activityRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.activityTangramButton)).perform(click())
        Espresso.onView(withId(R.id.tangramViewHeaderTextView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }

    @Test
    fun shouldOpenFifteenActivity() {
        activityRule.launchActivity(Intent())
        Espresso.onView(withId(R.id.activityFifteenButton)).perform(click())
        Espresso.onView(withId(R.id.actFifteenHeaderTextView)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
    }
}