package ru.bazalikova.childlogicgames.tangram

class TangramPresenter(var view: ITangramView, private val navigation: ITangramNavigation): ITangramPresenter
{
    override fun onViewCreated()
    {
        view.showHome()
    }

    override fun onCancelBtnClicked()
    {
        navigation.finish()
    }

    override fun onTangramBuild()
    {
        view.setGameOver()
    }
}