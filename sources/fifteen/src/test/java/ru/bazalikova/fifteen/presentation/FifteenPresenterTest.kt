package ru.bazalikova.fifteen.presentation

import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import ru.bazalikova.fifteen.data.Cell
import ru.bazalikova.fifteen.data.FifteenFieldHelper
import ru.bazalikova.fifteen.data.Game

class FifteenPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: IFifteenView

    private lateinit var game: Game

    private lateinit var  presenter: FifteenPresenter

    @Test
    fun `field should be set after view is created`() {
        game = Game()
        presenter = FifteenPresenter(game)
        presenter.attachView(view)
        presenter.onViewCreated()

        Mockito.verify(view).setField(game.field)
    }

    @Test
    fun `move second field to new position - and check that field is updated`() {
        game = Game()
        presenter = FifteenPresenter(game)
        presenter.attachView(view)
        presenter.onViewCreated()

        game.field = FifteenFieldHelper.testFifteenField()

        presenter.move(Cell(0, 1))

        Mockito.verify(view).setField(game.field)
    }

    @Test
    fun `click on repeat button - and check that repeat button came invisible`() {
        game = Game()
        presenter = FifteenPresenter(game)
        presenter.attachView(view)
        presenter.onViewCreated()
        game.field = FifteenFieldHelper.sortedFifteenField()

        presenter.onRepeatButtonClick()

        Mockito.verify(view).setRepeatButton(false)
    }

    @Test
    fun `click on repeat button - and check that game over button came invisible`() {
        game = Game()
        presenter = FifteenPresenter(game)
        presenter.attachView(view)
        presenter.onViewCreated()
        game.field = FifteenFieldHelper.sortedFifteenField()

        presenter.onRepeatButtonClick()

        Mockito.verify(view).setGameOver(false)
    }

    @Test
    fun `click on repeat button - and check that field is updated`() {
        game = Game()
        presenter = FifteenPresenter(game)
        presenter.attachView(view)
        presenter.onViewCreated()
        game.field = FifteenFieldHelper.sortedFifteenField()

        presenter.onRepeatButtonClick()

        Mockito.verify(view).setField(game.field)
    }
}