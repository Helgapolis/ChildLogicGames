package ru.bazalikova.menu.data

interface IMenuModel {
    fun getMenuItems(): List<MenuItem>
    fun getRowCount(): Int
    fun getColumnCount(): Int
}