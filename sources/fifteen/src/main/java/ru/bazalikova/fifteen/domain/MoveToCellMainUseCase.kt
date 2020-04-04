package ru.bazalikova.fifteen.domain

import ru.bazalikova.fifteen.data.FifteenField
import javax.inject.Inject

class MoveToCellMainUseCase @Inject constructor(): MoveToCellUseCase {
    override fun invoke(field: FifteenField, x: Int, y: Int) : Boolean {
        var res = false
        // Координаты пустой клетки
        var px0 = -1
        var py0 = -1

        // Ищем пустую клетку на поле
        for (row in 0 until field.width) {
            for (column in 0 until field.height) {
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
}