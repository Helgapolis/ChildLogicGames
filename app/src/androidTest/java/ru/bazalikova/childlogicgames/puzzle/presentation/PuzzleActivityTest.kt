package ru.bazalikova.childlogicgames.puzzle.presentation

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.menu.presentation.MenuActivity
import ru.bazalikova.puzzle.presentation.PuzzleActivity

@RunWith(AndroidJUnit4::class)
class PuzzleActivityTest {
    @get:Rule
    val menuActivityRule: ActivityTestRule<MenuActivity> =
        ActivityTestRule(MenuActivity::class.java)

    @get:Rule
    val activityRule: ActivityTestRule<PuzzleActivity> = ActivityTestRule(PuzzleActivity::class.java)

    @Test
    fun shouldDisplayExpression() {
        activityRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.actPuzzleExprTxt)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.isDisplayed(),
                    hasExpressionText()
                )
            )
        )
    }

    private fun hasExpressionText(): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                /* do nothing */
            }

            override fun matchesSafely(item: View?): Boolean {
                val textView = item as? TextView ?: return false
                val text = textView.text
                return text != null && text.isNotEmpty() && text.contains("?")
            }
        }
    }

    @Test
    fun shouldDisplayAnswer() {
        activityRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.actPuzzleBtnAnsw1)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.isDisplayed(),
                    ViewMatchers.isClickable()
                )
            )
        )
    }

    @Test
    fun answerShouldChangeBackgroundColor() {
        activityRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.actPuzzleBtnAnsw1)).perform(click()).check(
            ViewAssertions.matches(
                hasBackgroundColor()
            )
        )
    }

    private fun hasBackgroundColor(): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description?) {
                /* do nothing */
            }

            override fun matchesSafely(item: View?): Boolean {

                val context = item?.context
                val viewColor = (item?.background as ColorDrawable).color
                val transparentColor: Int?

                transparentColor = if (Build.VERSION.SDK_INT <= 22) {
                    context?.resources?.getColor(android.R.color.transparent)
                } else {
                    context?.getColor(android.R.color.transparent)
                }

                return viewColor != transparentColor
            }
        }
    }

    @Test
    fun shouldDisplayCancelButton() {
        activityRule.launchActivity(Intent())
        val context = activityRule.activity

        Espresso.onView(ViewMatchers.withId(R.id.actPuzzleCancelButton)).check(
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
    fun shouldClosePuzzleActivity() {
        menuActivityRule.launchActivity(Intent())
        Espresso.onView(ViewMatchers.withId(R.id.activityPuzzleButton)).perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.actPuzzleCancelButton)).perform(click())
        val context = menuActivityRule.activity
        Espresso.onView(ViewMatchers.withId(R.id.activityPuzzleText)).check(
            ViewAssertions.matches(
                Matchers.allOf(
                    ViewMatchers.withText(context.getString(R.string.act_menu_count_btn)),
                    ViewMatchers.isDisplayed()
                )
            )
        )
    }
}