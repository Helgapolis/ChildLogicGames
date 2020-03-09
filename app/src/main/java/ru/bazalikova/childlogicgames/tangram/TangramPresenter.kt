package ru.bazalikova.childlogicgames.tangram

import javax.inject.Inject

class TangramPresenter @Inject constructor(): TangramViewListener{

    private var view: ITangramView? = null
    private var navigation: ITangramNavigation? = null

    fun attachView(view: ITangramView)
    {
        this.view = view
    }

    fun detachView()
    {
        this.view = null
    }

    fun attachNavigation(navigation: ITangramNavigation)
    {
        this.navigation = navigation
    }

    fun detachNavigation()
    {
        this.navigation = null
    }

    fun onViewCreated() {
        view?.showHome()
    }

    fun onCancelBtnClicked() {
        navigation?.finish()
    }

    override fun onTangramBuild() {
        view?.setGameOver()
    }
}