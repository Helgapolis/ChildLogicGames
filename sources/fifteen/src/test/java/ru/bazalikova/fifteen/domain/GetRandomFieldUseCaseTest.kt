package ru.bazalikova.fifteen.domain

import org.junit.Test

class GetRandomFieldUseCaseTest {

    @Test
    fun `should contains 0 digit after build game`() {
        `should contains parameter digit after build game`(0)
    }

    @Test
    fun `should contains 1 digit after build game`() {
        `should contains parameter digit after build game`(1)
    }

    @Test
    fun `should contains 2 digit after build game`() {
        `should contains parameter digit after build game`(2)
    }

    @Test
    fun `should contains 3 digit after build game`() {
        `should contains parameter digit after build game`(3)
    }

    @Test
    fun `should contains 4 digit after build game`() {
        `should contains parameter digit after build game`(4)
    }

    @Test
    fun `should contains 5 digit after build game`() {
        `should contains parameter digit after build game`(5)
    }

    @Test
    fun `should contains 6 digit after build game`() {
        `should contains parameter digit after build game`(6)
    }

    @Test
    fun `should contains 7 digit after build game`() {
        `should contains parameter digit after build game`(7)
    }

    @Test
    fun `should contains 8 digit after build game`() {
        `should contains parameter digit after build game`(8)
    }

    private fun `should contains parameter digit after build game`(digit: Int) {
        val field = GetRandomFieldMainUseCase().invoke(3)

        var result = false

        for (row in 0 until field.width) {
            for (column in 0 until field.height) {
                if (field[row, column] == digit) {
                    result = true
                    break
                }
            }

            if (result) {
                break
            }
        }

        assert(result)
    }
}