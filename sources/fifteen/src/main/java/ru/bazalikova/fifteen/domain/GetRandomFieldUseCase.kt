package ru.bazalikova.fifteen.domain

import ru.bazalikova.fifteen.data.FifteenField

interface GetRandomFieldUseCase {
    fun invoke(fieldCount: Int) : FifteenField
}