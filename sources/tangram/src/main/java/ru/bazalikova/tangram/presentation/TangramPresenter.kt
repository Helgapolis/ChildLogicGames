package ru.bazalikova.tangram.presentation

import ru.bazalikova.tangram.ITangramMediator
import javax.inject.Inject

class TangramPresenter @Inject constructor(): TangramViewListener {

    private var view: ITangramView? = null
    private var mediator: ITangramMediator? = null

    fun attachView(view: ITangramView)
    {
        this.view = view
    }

    fun detachView()
    {
        this.view = null
    }

    fun attachMediator(mediator: ITangramMediator)
    {
        this.mediator = mediator
    }

    fun detachMediator()
    {
        this.mediator = null
    }

    fun onViewCreated() {
        view?.showHome()
    }

    fun onCancelBtnClicked() {
        mediator?.finish()
    }

    override fun onTangramBuild() {
        view?.setGameOver()
    }
}