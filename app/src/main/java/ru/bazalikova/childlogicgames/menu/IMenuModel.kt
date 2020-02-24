package ru.bazalikova.childlogicgames.menu

interface IMenuModel {
    fun getMenuItems(): List<MenuItem>
    fun getRowCount(): Int
    fun getColumnCount(): Int
}