package ru.bazalikova.childlogicgames.menu

data class MenuItem(val caption: String, val type: MenuType)

enum class MenuType(val type: Int) {
    COUNTING(0),
    FIFTEEN(1),
    TANGRAM(2)
}