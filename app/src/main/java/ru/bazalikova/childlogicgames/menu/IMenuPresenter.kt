package ru.bazalikova.childlogicgames.menu

interface IMenuPresenter
{
    fun onViewCreated()
    fun onStart()
    fun onStop()
    fun onMenuBtnClicked(btnType: MenuType)
}