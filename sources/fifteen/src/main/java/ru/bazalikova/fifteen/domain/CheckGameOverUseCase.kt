package ru.bazalikova.fifteen.domain

import ru.bazalikova.fifteen.data.FifteenField

interface CheckGameOverUseCase {
    fun invoke(field: FifteenField) : Boolean
}