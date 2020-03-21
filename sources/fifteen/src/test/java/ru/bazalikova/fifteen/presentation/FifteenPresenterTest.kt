package ru.bazalikova.fifteen.presentation

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import ru.bazalikova.fifteen.data.Cell
import ru.bazalikova.fifteen.data.FifteenField
import ru.bazalikova.fifteen.data.Game

class FifteenPresenterTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var view: IFifteenView

    lateinit var game: Game

    lateinit var  presenter: FifteenPresenter

    @Before
    fun setUp() {
        game = Game()
        presenter = FifteenPresenter(game)
    }

    @Test
    fun onViewCreated() {
        presenter.attachView(view)
        presenter.onViewCreated()

        Mockito.verify(view).setField(game.field)
    }

    @Test
    fun move() {
        presenter.attachView(view)
        presenter.onViewCreated()

        val field = FifteenField(3, 3)
        field[0, 0] = 7; field[0, 1] = 1; field[0, 2] = 0
        field[1, 0] = 6; field[1, 1] = 2; field[1, 2] = 4
        field[2, 0] = 8; field[2, 1] = 3; field[2, 2] = 5

        game.field = field
        presenter.move(Cell(0, 1))

        Mockito.verify(view).setField(game.field)
    }

    @Test
    fun onRepeatButtonClick() {
        presenter.attachView(view)
        presenter.onViewCreated()

        val field = FifteenField(3, 3)
        field[0, 0] = 1; field[0, 1] = 2; field[0, 2] = 3
        field[1, 0] = 4; field[1, 1] = 5; field[1, 2] = 6
        field[2, 0] = 7; field[2, 1] = 8; field[2, 2] = 0

        game.field = field
        presenter.onRepeatButtonClick()

        Mockito.verify(view).setRepeatButton(false)
        Mockito.verify(view).setGameOver(false)
        Mockito.verify(view).setField(game.field)
    }
}