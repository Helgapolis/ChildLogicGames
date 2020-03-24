package ru.bazalikova.fifteen.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import ru.bazalikova.fifteen.R

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class FifteenViewTest {

    lateinit var fifteenView: FifteenView

    @Test
    fun `should repeat button came visible`() {
        val repeatButton = setRepeatButton(true)
        assertEquals(View.VISIBLE, repeatButton.visibility)
    }

    @Test
    fun `should repeat button came gone`() {
        val repeatButton = setRepeatButton(false)
        assertEquals(View.GONE, repeatButton.visibility)
    }

    private fun setRepeatButton(isVisible: Boolean): AppCompatButton {
        val activity = buildActivity(FifteenActivity::class.java).create().get()
        val view =  LayoutInflater.from(activity).inflate(R.layout.activity_fifteen, null)
        fifteenView = view  as FifteenView
        fifteenView.setRepeatButton(isVisible)
        return view.findViewById<AppCompatButton>(R.id.actFifteenRepeatButton)
    }

    @Test
    fun `should game over text view came visible`() {
        val textView = setGameOver(true)
        assertEquals(View.VISIBLE, textView.visibility)
    }

    @Test
    fun `should game over text view came gone`() {
        val textView = setGameOver(false)
        assertEquals(View.GONE, textView.visibility)
    }

    private fun setGameOver(isVisible: Boolean): TextView {
        val activity = buildActivity(FifteenActivity::class.java).create().get()
        val view =  LayoutInflater.from(activity).inflate(R.layout.activity_fifteen, null)
        fifteenView = view as FifteenView
        fifteenView.setGameOver(isVisible)

        return view.findViewById<TextView>(R.id.actFifteenGoodResultTextView)
    }
}