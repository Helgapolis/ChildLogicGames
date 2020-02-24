package ru.bazalikova.childlogicgames.tangram

class TangramPresenter(var view: ITangramView, private val navigation: ITangramNavigation): TangramViewListener{

    fun onViewCreated() {
        view.showHome()
    }

    fun onCancelBtnClicked() {
        navigation.finish()
    }

    override fun onTangramBuild() {
        view.setGameOver()
    }
}