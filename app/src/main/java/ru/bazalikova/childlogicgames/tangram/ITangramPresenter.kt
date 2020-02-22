package ru.bazalikova.childlogicgames.tangram

interface ITangramPresenter: TangramViewListener
{
    fun onViewCreated()
    fun onCancelBtnClicked()
}