package ru.bazalikova.childlogicgames.puzzle

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.children
import junit.framework.TestCase.assertTrue
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric.buildActivity
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadow.api.Shadow
import ru.bazalikova.childlogicgames.R
import ru.bazalikova.puzzle.data.PuzzleRepository
import ru.bazalikova.puzzle.presentation.PuzzleActivity
import ru.bazalikova.puzzle.presentation.PuzzlePresenter
import ru.bazalikova.puzzle.presentation.PuzzleView

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], shadows = [ShadowPuzzleRepository::class])
class PuzzleTest {

    private lateinit var view: PuzzleView
    private lateinit var presenter: PuzzlePresenter
    private lateinit var repository: PuzzleRepository

    private fun buildComponent() {
        val activity = buildActivity(PuzzleActivity::class.java).create().resume().get()

        view = LayoutInflater.from(activity).inflate(R.layout.activity_puzzle, null) as PuzzleView
        repository = PuzzleRepository(activity)
        presenter = PuzzlePresenter(repository)
        presenter.attachView(view)

        view.onFinishInflate(presenter)
        presenter.onViewCreated()
    }

    private fun passLesson() {
        buildComponent()

        while (!repository.isLastSample()) {
            val correctAnswer =
                (Shadow.extract(repository) as ShadowPuzzleRepository).getCorrectAnswer().toString()

            val button1 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw1).text.toString()
            val button2 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw2).text.toString()
            val button3 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw3).text.toString()
            val button4 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw4).text.toString()

            val buttons = listOf(button1, button2, button3, button4)
            val buttonIndex = buttons.indexOf(correctAnswer)

            presenter.onAnswerBtnClicked(buttonIndex, correctAnswer)
            presenter.onNextBtnClicked()
        }
    }

    @Test
    fun `should show example from repository`() {
        buildComponent()

        val text = view.findViewById<TextView>(R.id.actPuzzleExprTxt).text

        assertEquals(repository.getExpression(), text)
    }

    @Test
    fun `should show answers from repository`() {
        buildComponent()

        val button1 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw1).text.toString()
        val button2 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw2).text.toString()
        val button3 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw3).text.toString()
        val button4 = view.findViewById<Button>(R.id.actPuzzleBtnAnsw4).text.toString()

        val buttons = listOf(button1, button2, button3, button4)
        assertEquals(buttons.sorted(), repository.getAnswers().sorted().map { it.toString() })
    }

    @Test
    fun `should all puzzles be hidden`() {
        buildComponent()

        val puzzlesContent = view.findViewById<FrameLayout>(R.id.actPuzzleContentLayout)
        val children = puzzlesContent.children
        assertTrue(children.map { it.visibility == View.GONE }.count() == children.count())
    }

    @Test
    fun `should be correct answer not null`() {
        buildComponent()

        val correctAnswer = (Shadow.extract(repository) as ShadowPuzzleRepository).getCorrectAnswer()

        assertTrue(correctAnswer != null)
    }

    @Test
    fun `should show all puzzles after pass lesson`() {
        passLesson()

        val puzzlesContent = view.findViewById<FrameLayout>(R.id.actPuzzleContentLayout)
        val children = puzzlesContent.children
        assertTrue(children.map { it.visibility == View.VISIBLE }.count() == children.count())
    }
}