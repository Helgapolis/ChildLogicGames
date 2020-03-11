package ru.bazalikova.menu.presentation

import ru.bazalikova.menu.IMenuMediator
import ru.bazalikova.menu.data.MenuType
import javax.inject.Inject

class MenuPresenter @Inject constructor() {

    private var view: IMenuView? = null
    private var mediator: IMenuMediator? = null

    fun attachView(view: IMenuView)
    {
        this.view = view
    }

    fun detachView()
    {
        this.view = null
    }

    fun attachMediator(mediator: IMenuMediator)
    {
        this.mediator = mediator
    }

    fun detachMediator()
    {
        this.mediator = null
    }

    fun onMenuBtnClicked(btnType: MenuType) {
        when (btnType) {
            MenuType.COUNTING -> mediator?.openCounting()
            MenuType.FIFTEEN -> mediator?.openFifteen()
            MenuType.TANGRAM -> mediator?.openTangram()
        }
    }
}