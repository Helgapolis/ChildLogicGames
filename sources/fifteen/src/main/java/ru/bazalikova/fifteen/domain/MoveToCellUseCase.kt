package ru.bazalikova.fifteen.domain

import ru.bazalikova.fifteen.data.FifteenField

interface MoveToCellUseCase {
    fun invoke(field: FifteenField, x: Int, y: Int) : Boolean
}