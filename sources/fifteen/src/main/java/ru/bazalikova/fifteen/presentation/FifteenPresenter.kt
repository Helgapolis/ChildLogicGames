package ru.bazalikova.fifteen.presentation

import ru.bazalikova.fifteen.IFifteenMediator
import ru.bazalikova.fifteen.data.Cell
import ru.bazalikova.fifteen.data.Game
import javax.inject.Inject

class FifteenPresenter @Inject constructor(private val game: Game) {
    private var view: IFifteenView? = null
    private var mediator: IFifteenMediator? = null

    fun attachView(view: IFifteenView)
    {
        this.view = view
    }

    fun detachView()
    {
        this.view = null
    }

    fun attachMediator(mediator: IFifteenMediator?)
    {
        this.mediator = mediator
    }

    fun detachMediator()
    {
        this.mediator = null
    }

    fun onViewCreated() {
        val field = game.build()
        view?.setField(field)
    }

    fun onStart() {
        /* do nothing */
    }

    fun onStop() {
        /* do nothing */
    }

    fun move(cell: Cell) {
        if (game.move(cell.row, cell.column))
        {
            view?.setField(game.field)
        }

        if (game.checkGameOver())
        {
            view?.setGameOver(true)
            view?.setRepeatButton(true)
        }
    }

    fun onRepeatButtonClick() {
        view?.setGameOver(false)
        view?.setRepeatButton(false)

        val field = game.build()
        view?.setField(field)
    }

    fun onCancelButtonClick()
    {
        mediator?.finish()
    }
}