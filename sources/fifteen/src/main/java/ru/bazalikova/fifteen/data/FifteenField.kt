package ru.bazalikova.fifteen.data

class FifteenField(override val height: Int, override val width: Int) : Matrix<Int> {
    private val map = mutableMapOf<Cell, Int>()

    override fun get(row: Int, column: Int): Int {
        return map[Cell(row, column)] ?: 0
    }

    override fun get(cell: Cell): Int {
        return map[cell] ?: 0
    }

    override fun set(row: Int, column: Int, value: Int) {
        map[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: Int) {
        map[cell] = value
    }
}