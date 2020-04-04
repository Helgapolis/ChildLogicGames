package ru.bazalikova.fifteen.presentation

import ru.bazalikova.fifteen.IFifteenMediator
import ru.bazalikova.fifteen.data.Cell
import ru.bazalikova.fifteen.data.FifteenField
import ru.bazalikova.fifteen.domain.CheckGameOverUseCase
import ru.bazalikova.fifteen.domain.GetRandomFieldUseCase
import ru.bazalikova.fifteen.domain.MoveToCellUseCase
import javax.inject.Inject

class FifteenPresenter @Inject constructor(
    val getRandomFieldUseCase: GetRandomFieldUseCase,
    val moveToCellUseCase: MoveToCellUseCase,
    val checkGameOverUseCase: CheckGameOverUseCase
) {
    private var view: IFifteenView? = null
    private var mediator: IFifteenMediator? = null

    companion object{
        const val fieldCount = 3
    }

    private var field: FifteenField? = null

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
        val field = getRandomFieldUseCase.invoke(fieldCount)
        view?.setField(field)

        this.field = field
    }

    fun onStart() {
        /* do nothing */
    }

    fun onStop() {
        /* do nothing */
    }

    fun move(cell: Cell) {
        val field = this.field ?: return

        if (moveToCellUseCase.invoke(field, cell.row, cell.column))
        {
            view?.setField(field)
        }

        if (checkGameOverUseCase.invoke(field))
        {
            view?.setGameOver(true)
            view?.setRepeatButton(true)
        }
    }

    fun onRepeatButtonClick() {
        view?.setGameOver(false)
        view?.setRepeatButton(false)

        val field = getRandomFieldUseCase.invoke(fieldCount)
        view?.setField(field)

        this.field = field
    }

    fun onCancelButtonClick()
    {
        mediator?.finish()
    }
}