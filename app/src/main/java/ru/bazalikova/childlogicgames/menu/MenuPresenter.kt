package ru.bazalikova.childlogicgames.menu

class MenuPresenter(
    private val view: IMenuView,
    private val model: IMenuModel,
    private val navigation: IMenuNavigation) {

    fun onViewCreated() {
        view.setMenuItems(
            model.getMenuItems(),
            model.getRowCount(),
            model.getColumnCount()
        )
    }

    fun onMenuBtnClicked(btnType: MenuType) {
        when (btnType) {
            MenuType.COUNTING -> navigation.openCounting()
            MenuType.FIFTEEN -> navigation.openFifteen()
            MenuType.TANGRAM -> navigation.openTangram()
        }
    }
}