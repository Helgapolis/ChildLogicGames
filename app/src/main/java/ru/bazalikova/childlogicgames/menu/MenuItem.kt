package ru.bazalikova.childlogicgames.menu

data class MenuItem(val caption: String, val type: MenuType)

enum class MenuType {
    COUNTING,
    FIFTEEN,
    TANGRAM
}