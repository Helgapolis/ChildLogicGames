package ru.bazalikova.childlogicgames.menu_fifteen

import android.content.Intent
import android.os.Build
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.fifteen.presentation.FifteenActivity
import ru.bazalikova.menu.presentation.MenuActivity

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class MenuFifteenActivityTets {

    @Test
    fun `should launch fifteen activity`() {
        val activity = buildActivity(MenuActivity::class.java).create().resume().get()
        val expectedIntent = Intent(activity, FifteenActivity::class.java)

        val fifteenButton = activity.findViewById<AppCompatImageButton>(R.id.activityFifteenButton)
        fifteenButton.callOnClick()

        val actualIntent = (Shadows.shadowOf(activity) as ShadowActivity).nextStartedActivity
        assertTrue(expectedIntent.filterEquals(actualIntent))
    }

    @Test
    fun `should finish fifteen activity`() {
        val activity = buildActivity(FifteenActivity::class.java).create().resume().get()

        val fifteenButton = activity.findViewById<AppCompatButton>(R.id.actFifteenCancelButton)
        fifteenButton.callOnClick()

        val isFinished = activity.isFinishing
        assertTrue(isFinished)
    }
}