package ru.bazalikova.childlogicgames.menu

class MenuPresenter(private val view: IMenuView, private val repository: IMenuRepository, private val navigation: IMenuNavigation): IMenuPresenter
{
    override fun onViewCreated()
    {
        view.setMenuItems(repository.getMenuItems(), repository.getRowCount(), repository.getColumnCount())
    }

    override fun onStart()
    {
        /* do nothing */
    }

    override fun onStop()
    {
        /* do nothing */
    }

    override fun onMenuBtnClicked(btnType: MenuType)
    {
        when (btnType)
        {
            MenuType.COUNTING -> navigation.openCounting()
            MenuType.FIFTEEN -> navigation.openFifteen()
            MenuType.TANGRAM -> navigation.openTangram()
        }
    }
}