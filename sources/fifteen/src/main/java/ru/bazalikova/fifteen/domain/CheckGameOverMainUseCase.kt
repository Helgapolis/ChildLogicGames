package ru.bazalikova.fifteen.domain

import ru.bazalikova.fifteen.data.FifteenField
import javax.inject.Inject

class CheckGameOverMainUseCase @Inject constructor() : CheckGameOverUseCase {
    override fun invoke(field: FifteenField): Boolean {
        var fieldValue = 1

        for (row in 0 until field.width)
        {
            for (column in 0 until field.height)
            {
                if (row == field.width -1 && column == field.height -1)
                {
                    fieldValue = 0
                }

                if (field[row, column] != fieldValue)
                {
                    return false
                }

                fieldValue++
            }
        }

        return true
    }
}