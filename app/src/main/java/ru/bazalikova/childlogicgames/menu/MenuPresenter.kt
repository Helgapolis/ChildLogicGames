package ru.bazalikova.childlogicgames.menu

import javax.inject.Inject

class MenuPresenter @Inject constructor(private val model: IMenuModel) {

    private var view: IMenuView? = null
    private var navigation: IMenuNavigation? = null

    fun attachView(view: IMenuView)
    {
        this.view = view
    }

    fun detachView()
    {
        this.view = null
    }

    fun attachNavigation(navigation: IMenuNavigation)
    {
        this.navigation = navigation
    }

    fun detachNavigation()
    {
        this.navigation = null
    }

    fun onViewCreated() {
        view?.setMenuItems(
            model.getMenuItems(),
            model.getRowCount(),
            model.getColumnCount()
        )
    }

    fun onMenuBtnClicked(btnType: MenuType) {
        when (btnType) {
            MenuType.COUNTING -> navigation?.openCounting()
            MenuType.FIFTEEN -> navigation?.openFifteen()
            MenuType.TANGRAM -> navigation?.openTangram()
        }
    }
}