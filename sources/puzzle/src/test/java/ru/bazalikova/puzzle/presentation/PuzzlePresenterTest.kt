package ru.bazalikova.puzzle.presentation

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import ru.bazalikova.puzzle.data.IPuzzleRepository

class PuzzlePresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: IPuzzleView

    @Mock
    lateinit var repository: IPuzzleRepository

    private lateinit var  presenter: PuzzlePresenter

    @Test
    fun `puzzle views should be set after view is created`() {
        presenter = PuzzlePresenter(repository)
        presenter.attachView(view)

        val expectedPrefix = "test"
        val expectedCount = 24

        Mockito.`when`(repository.puzzlePrefix()).thenReturn(expectedPrefix)
        Mockito.`when`(repository.puzzleCount()).thenReturn(expectedCount)

        presenter.onViewCreated()

        Mockito.verify(view).addPuzzleViews(expectedPrefix, expectedCount)
    }

    @Test
    fun `should show example after view is created`() {
        presenter = PuzzlePresenter(repository)
        presenter.attachView(view)

        val expectedExpression = "2 + 2 = ?"
        val answers = listOf(4, 5, 6, 7)

        Mockito.`when`(repository.getExpression()).thenReturn(expectedExpression)
        Mockito.`when`(repository.getAnswers()).thenReturn(answers)

        presenter.onViewCreated()

        Mockito.verify(view).setExample(expectedExpression, answers.map { it.toString() })
    }

    @Test
    fun `should show right answer by click on answer button`() {
        presenter = PuzzlePresenter(repository)
        presenter.attachView(view)

        Mockito.`when`(repository.checkAnswer(Mockito.anyString())).thenReturn(true)

        val expectedAnswer = "4"
        val expectedBtnIndex = 0

        presenter.onViewCreated()

        presenter.onAnswerBtnClicked(expectedBtnIndex, expectedAnswer)

        Mockito.verify(view).setAnswerBtnType(expectedBtnIndex, IPuzzleView.AnswerType.RIGHT)
    }

    @Test
    fun `should show incorrect answer by click on answer button`() {
        presenter = PuzzlePresenter(repository)
        presenter.attachView(view)

        Mockito.`when`(repository.checkAnswer(Mockito.anyString())).thenReturn(false)

        val expectedAnswer = "6"
        val expectedBtnIndex = 2

        presenter.onViewCreated()

        presenter.onAnswerBtnClicked(expectedBtnIndex, expectedAnswer)

        Mockito.verify(view).setAnswerBtnType(expectedBtnIndex, IPuzzleView.AnswerType.INCORRECT)
    }

    @Test
    fun `click on next button - and check that next button came invisible`() {
        presenter = PuzzlePresenter(repository)
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.onNextBtnClicked()

        Mockito.verify(view).setNextButton(false)
    }
}