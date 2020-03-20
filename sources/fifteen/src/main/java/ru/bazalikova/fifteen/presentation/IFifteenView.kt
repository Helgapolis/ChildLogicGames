package ru.bazalikova.fifteen.presentation

import ru.bazalikova.fifteen.data.FifteenField

interface IFifteenView {
    fun setField(field: FifteenField)
    fun setRepeatButton(isVisible: Boolean)
    fun setGameOver(isVisible: Boolean)
}