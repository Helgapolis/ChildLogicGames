package ru.bazalikova.fifteen.data

data class Cell(val row: Int, val column: Int)

interface Matrix<E> {
    val height: Int
    val width: Int

    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}