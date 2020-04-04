package ru.bazalikova.fifteen.presentation

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import ru.bazalikova.fifteen.data.Cell
import ru.bazalikova.fifteen.data.FifteenFieldHelper
import ru.bazalikova.fifteen.domain.CheckGameOverUseCase
import ru.bazalikova.fifteen.domain.GetRandomFieldUseCase
import ru.bazalikova.fifteen.domain.MoveToCellUseCase

class FifteenPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: IFifteenView

    @Mock
    lateinit var getRandomFieldUseCase: GetRandomFieldUseCase

    @Mock
    lateinit var moveToCellUseCase: MoveToCellUseCase

    @Mock
    lateinit var checkGameOverUseCase: CheckGameOverUseCase

    private lateinit var presenter: FifteenPresenter

    @Test
    fun `field should be set after view is created`() {
        val expectedField = FifteenFieldHelper.testFifteenField()
        Mockito.`when`(getRandomFieldUseCase.invoke(Mockito.anyInt())).thenReturn(expectedField)

        presenter = FifteenPresenter(getRandomFieldUseCase, moveToCellUseCase, checkGameOverUseCase)
        presenter.attachView(view)
        presenter.onViewCreated()

        Mockito.verify(view).setField(expectedField)
    }

    @Test
    fun `move second field to new position - and check that field is updated`() {
        val testField = FifteenFieldHelper.testFifteenField()
        Mockito.`when`(getRandomFieldUseCase.invoke(Mockito.anyInt())).thenReturn(testField)
        Mockito.`when`(moveToCellUseCase.invoke(testField, 0, 1)).thenReturn(true)

        presenter = FifteenPresenter(getRandomFieldUseCase, moveToCellUseCase, checkGameOverUseCase)
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.move(Cell(0, 1))

        Mockito.verify(view, Mockito.times(2)).setField(testField)
    }

    @Test
    fun `click on repeat button - and check that repeat button came invisible`() {
        presenter = FifteenPresenter(getRandomFieldUseCase, moveToCellUseCase, checkGameOverUseCase)
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.onRepeatButtonClick()

        Mockito.verify(view).setRepeatButton(false)
    }

    @Test
    fun `click on repeat button - and check that game over button came invisible`() {
        presenter = FifteenPresenter(getRandomFieldUseCase, moveToCellUseCase, checkGameOverUseCase)
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.onRepeatButtonClick()

        Mockito.verify(view).setGameOver(false)
    }

    @Test
    fun `click on repeat button - and check that field is updated`() {
        val expectedField = FifteenFieldHelper.testFifteenField()
        Mockito.`when`(getRandomFieldUseCase.invoke(Mockito.anyInt())).thenReturn(expectedField)

        presenter = FifteenPresenter(getRandomFieldUseCase, moveToCellUseCase, checkGameOverUseCase)
        presenter.attachView(view)
        presenter.onViewCreated()

        presenter.onRepeatButtonClick()

        Mockito.verify(view, Mockito.times(2)).setField(expectedField)
    }
}