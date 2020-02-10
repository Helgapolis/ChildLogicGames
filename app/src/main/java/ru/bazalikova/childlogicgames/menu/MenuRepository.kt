package ru.bazalikova.childlogicgames.menu

import android.content.Context
import ru.bazalikova.childlogicgames.R

class MenuRepository(context: Context): IMenuRepository
{
    private val menuItems: List<MenuItem>
    private val rowCount: Int
    private val columnCount: Int

    init
    {
        menuItems = listOf(MenuItem(context.getString(R.string.act_menu_count_btn), MenuType.COUNTING),
            MenuItem(context.getString(R.string.act_menu_fifteen), MenuType.FIFTEEN),
            MenuItem(context.getString(R.string.act_menu_tangram), MenuType.TANGRAM))

        rowCount = context.resources.getInteger(R.integer.act_menu_row_count)
        columnCount = context.resources.getInteger(R.integer.act_menu_column_count)
    }

    override fun getMenuItems(): List<MenuItem>
    {
        return menuItems
    }

    override fun getRowCount(): Int
    {
        return rowCount
    }

    override fun getColumnCount(): Int
    {
        return columnCount
    }
}