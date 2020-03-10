package ru.bazalikova.menu.data

import android.content.Context
import ru.bazalikova.menu.R
import javax.inject.Inject

class MenuModel @Inject constructor(context: Context) :
    IMenuModel {
    private val menuItems: List<MenuItem> =
        listOf(
            MenuItem(
                context.getString(R.string.act_menu_count_btn),
                MenuType.COUNTING
            ),
            MenuItem(
                context.getString(R.string.act_menu_fifteen),
                MenuType.FIFTEEN
            ),
            MenuItem(
                context.getString(R.string.act_menu_tangram),
                MenuType.TANGRAM
            )
        )

    private val rowCount: Int = context.resources.getInteger(R.integer.act_menu_row_count)
    private val columnCount: Int = context.resources.getInteger(R.integer.act_menu_column_count)

    override fun getMenuItems(): List<MenuItem> {
        return menuItems
    }

    override fun getRowCount(): Int {
        return rowCount
    }

    override fun getColumnCount(): Int {
        return columnCount
    }
}