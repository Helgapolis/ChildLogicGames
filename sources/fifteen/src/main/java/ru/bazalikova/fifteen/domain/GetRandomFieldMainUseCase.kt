package ru.bazalikova.fifteen.domain

import ru.bazalikova.fifteen.data.FifteenField
import javax.inject.Inject

class GetRandomFieldMainUseCase @Inject constructor() : GetRandomFieldUseCase {
    override fun invoke(fieldCount: Int) : FifteenField {
        val field = FifteenField(fieldCount, fieldCount)

        for (fieldIndex in 0 until fieldCount * fieldCount){
            field[fieldIndex % fieldCount, fieldIndex / fieldCount] = 0
        }

        for (fieldIndex in 0 until fieldCount * fieldCount){
            var x = (Math.random() * fieldCount).toInt()
            var y = (Math.random() * fieldCount).toInt()

            while (field[x, y] != 0)
            {
                x = (Math.random() * fieldCount).toInt()
                y = (Math.random() * fieldCount).toInt()
            }

            field[x, y] = fieldIndex
        }

        return field
    }
}