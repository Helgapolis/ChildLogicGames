package ru.bazalikova.childlogicgames.puzzle.presentation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import ru.bazalikova.puzzle.R
import ru.bazalikova.puzzle.presentation.PuzzleActivity
import ru.bazalikova.puzzle.presentation.PuzzleView

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
class PuzzleViewTest {
    lateinit var puzzleView: PuzzleView

    @Test
    fun `should game over text view came visible`() {
        val activity = Robolectric.buildActivity(PuzzleActivity::class.java).create().get()

        val view = LayoutInflater.from(activity).inflate(R.layout.activity_puzzle, null)
        puzzleView = view as PuzzleView

        puzzleView.setGameOver(true)

        assertEquals(View.VISIBLE, view.findViewById<TextView>(R.id.actPuzzleGoodResultTextView).visibility)
    }

    @Test
    fun `should game over text view came gone`(){
        val activity = Robolectric.buildActivity(PuzzleActivity::class.java).create().get()

        val view = LayoutInflater.from(activity).inflate(R.layout.activity_puzzle, null)
        puzzleView = view as PuzzleView

        puzzleView.setGameOver(false)

        assertEquals(View.GONE, view.findViewById<TextView>(R.id.actPuzzleGoodResultTextView).visibility)
    }
}