package ru.bazalikova.menu.data

data class MenuItem(val caption: String, val type: MenuType)

enum class MenuType {
    COUNTING,
    FIFTEEN,
    TANGRAM
}