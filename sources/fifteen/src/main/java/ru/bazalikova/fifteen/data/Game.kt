package ru.bazalikova.fifteen.data

import javax.inject.Inject

class Game @Inject constructor() {
    companion object{
        const val fieldCount = 3
    }

    var field = FifteenField(fieldCount, fieldCount)

    fun build(): FifteenField {
        for (fieldIndex in 0 until fieldCount*fieldCount){
            field[fieldIndex % fieldCount, fieldIndex / fieldCount] = 0
        }

        for (fieldIndex in 0 until fieldCount*fieldCount){
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

    fun move(x: Int, y: Int): Boolean{
        var res = false
        // Координаты пустой клетки
        var px0 = -1
        var py0 = -1

        // Ищем пустую клетку на поле
        for (row in 0 until fieldCount) {
            for (column in 0 until fieldCount) {
                if (field[row, column] == 0) {
                    px0 = row
                    py0 = column
                }
            }
        }

        if (px0 == x || py0 == y) {
            if (!(px0 == x && py0 == y)) {
                if (px0 == x) {
                    if (py0 < y) {
                        for (i in py0 + 1..y) {
                            field[x, i - 1] = field[x, i]
                        }
                    } else {
                        for (i in py0 downTo y + 1) {
                            field[x, i] = field[x, i - 1]
                        }
                    }
                }
                if (py0 == y) {
                    if (px0 < x) {
                        for (i in px0 + 1..x) {
                            field[i - 1, y] = field[i, y]
                        }
                    } else {
                        for (i in px0 downTo x + 1) {
                            field[i, y] = field[i - 1, y]
                        }
                    }
                }
                field[x, y] = 0
                res = true
            } else {
                res = false
            }
        }

        return res
    }

    fun checkGameOver(): Boolean{
        var fieldValue = 1

        for (row in 0 until fieldCount)
        {
            for (column in 0 until fieldCount)
            {
                if (row == fieldCount -1 && column == fieldCount -1)
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