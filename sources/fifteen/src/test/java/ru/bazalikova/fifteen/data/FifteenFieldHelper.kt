package ru.bazalikova.fifteen.data

class FifteenFieldHelper {
    companion object {
        fun testFifteenField() : FifteenField {
            val field = FifteenField(3, 3)

            field[0, 0] = 7; field[0, 1] = 1; field[0, 2] = 0
            field[1, 0] = 6; field[1, 1] = 2; field[1, 2] = 4
            field[2, 0] = 8; field[2, 1] = 3; field[2, 2] = 5

            return field
        }

        fun sortedFifteenField() : FifteenField {
            val field = FifteenField(3, 3)
            field[0, 0] = 1; field[0, 1] = 2; field[0, 2] = 3
            field[1, 0] = 4; field[1, 1] = 5; field[1, 2] = 6
            field[2, 0] = 7; field[2, 1] = 8; field[2, 2] = 0

            return field
        }
    }
}