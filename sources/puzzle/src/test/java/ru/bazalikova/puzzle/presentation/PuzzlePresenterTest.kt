package ru.bazalikova.puzzle.presentation

import org.junit.Before
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

    @Before
    fun setUp() {
        presenter = PuzzlePresenter(repository)
    }

    @Test
    fun onViewCreated() {
        presenter.attachView(view)

        val expectedPrefix = "test"
        val expectedCount = 24

        val expectedException = "2 + 2 = ?"
        val answers = listOf(4, 5, 6, 7)

        Mockito.`when`(repository.puzzlePrefix()).thenReturn(expectedPrefix)
        Mockito.`when`(repository.puzzleCount()).thenReturn(expectedCount)
        Mockito.`when`(repository.getExpression()).thenReturn(expectedException)
        Mockito.`when`(repository.getAnswers()).thenReturn(answers)

        presenter.onViewCreated()
        Mockito.verify(view).addPuzzleViews(expectedPrefix, expectedCount)
        Mockito.verify(view).setExample(expectedException, answers.map { it.toString() })
    }

    @Test
    fun `should show right answer by onAnswerBtnClick`() {
        presenter.attachView(view)

        Mockito.`when`(repository.checkAnswer(Mockito.anyString())).thenReturn(true)

        val expectedAnswer = "4"
        val expectedBtnIndex = 0

        presenter.onViewCreated()
        presenter.onAnswerBtnClicked(expectedBtnIndex, expectedAnswer)
        Mockito.verify(view).setAnswerBtnType(expectedBtnIndex, IPuzzleView.AnswerType.RIGHT)
    }

    @Test
    fun `should show incorrect answer by onAnswerBtnClick`() {
        presenter.attachView(view)

        Mockito.`when`(repository.checkAnswer(Mockito.anyString())).thenReturn(false)

        val expectedAnswer = "6"
        val expectedBtnIndex = 2

        presenter.onViewCreated()
        presenter.onAnswerBtnClicked(expectedBtnIndex, expectedAnswer)
        Mockito.verify(view).setAnswerBtnType(expectedBtnIndex, IPuzzleView.AnswerType.INCORRECT)
    }

    @Test
    fun onNextBtnClicked() {
        presenter.attachView(view)

        presenter.onViewCreated()
        presenter.onNextBtnClicked()
        Mockito.verify(view).setNextButton(false)
    }
}