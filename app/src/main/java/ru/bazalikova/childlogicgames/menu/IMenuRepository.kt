package ru.bazalikova.childlogicgames.menu

interface IMenuRepository
{
    fun getMenuItems(): List<MenuItem>
    fun getRowCount(): Int
    fun getColumnCount(): Int
}