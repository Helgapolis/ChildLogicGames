package ru.bazalikova.menu.presentation

import ru.bazalikova.menu.data.MenuItem

interface IMenuView {
    fun setMenuItems(
        menuItems: List<MenuItem>,
        rowCount: Int,
        columnCount: Int
    )
}