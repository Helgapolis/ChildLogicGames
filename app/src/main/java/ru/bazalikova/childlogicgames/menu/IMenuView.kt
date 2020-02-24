package ru.bazalikova.childlogicgames.menu

interface IMenuView {
    fun setMenuItems(
        menuItems: List<MenuItem>,
        rowCount: Int,
        columnCount: Int
    )
}