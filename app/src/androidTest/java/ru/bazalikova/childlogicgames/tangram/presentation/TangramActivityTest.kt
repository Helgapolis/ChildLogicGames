package ru.bazalikova.childlogicgames.tangram.presentation

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.menu.presentation.MenuActivity
import ru.bazalikova.tangram.presentation.TangramActivity

@RunWith(AndroidJUnit4::class)
class TangramActivityTest {
    @get:Rule
    val menuActivityRule: ActivityTestRule<MenuActivity> =
        ActivityTestRule(MenuActivity::class.java)

    @get:Rule
    val activityRule: ActivityTestRule<TangramActivity> =
        ActivityTestRule(TangramActivity::class.java)

    @Test
    fun shouldDisplayHeaderTextView() {
        activityRule.launchActivity(Intent())
        val context = activityRule.activity
        Espresso.onView(ViewMatchers.withId(R.id.tangramViewHeaderTextView)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.act_tangram_house_header)),
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }

    @Test
    fun shouldDisplayCancelButton() {
        activityRule.launchActivity(Intent())
        val context = activityRule.activity
        Espresso.onView(ViewMatchers.withId(R.id.tangramViewCancelButton)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.btn_cancel)),
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.isClickable()
                )
            )
        )
    }

    @Test
    fun shouldCloseTangramActivity() {
        menuActivityRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.activityTangramButton)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tangramViewCancelButton)).perform(ViewActions.click())
        val context = menuActivityRule.activity
        Espresso.onView(ViewMatchers.withId(R.id.activityTangramText)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.act_menu_tangram)),
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }
}