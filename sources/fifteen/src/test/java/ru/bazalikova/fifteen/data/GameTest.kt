package ru.bazalikova.fifteen.data

import junit.framework.TestCase.assertEquals
import org.junit.Test

class GameTest {

    private val game = Game()

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
        val field = game.build()

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

    @Test
    fun `move second cell to empty field - and check that it is possible`(){
        game.field = FifteenFieldHelper.testFifteenField()              // 7  1->0
        val result = game.move(0, 1)                             // 6  2  4
                                                                       // 8  3  5
        assert(result)
    }

    @Test
    fun `move second cell to empty field - and check that position is correct`(){
        game.field = FifteenFieldHelper.testFifteenField()           // 7  1->0
        game.move(0, 1)

        val field = game.field
        assertEquals(field[0, 2], 1)                             // 7  0  1
    }

    @Test
    fun `move fourth cell to non empty field - and check that it is not possible`(){
        game.field = FifteenFieldHelper.testFifteenField()              // 7  1  0
        val result = game.move(1, 1)                             // 6  2/ 4
                                                                       // 8  3  5
        assert(!result)
    }

    @Test
    fun `move fourth cell to non empty field - and check that position is not changed`(){
        game.field = FifteenFieldHelper.testFifteenField()          // 7  1  0
        game.move(1, 1)                                      // 6  2/ 4
                                                                   // 8  3  5
        val field = game.field

        assertEquals(field[1, 1], 2)
    }

    @Test
    fun `move row to empty field - and check that it is possible`(){
        game.field = FifteenFieldHelper.testFifteenField()               // ->   7  1  0
                                                                         //      6  2  4
        val result = game.move(0, 0)                              //      8  3  5

        assert(result)                                                 //      0  7  1
    }

    @Test
    fun `move row to empty field - and check that empty field is changed position`(){
        game.field = FifteenFieldHelper.testFifteenField()                  // ->   7  1  0
                                                                           //      6  2  4
        game.move(0, 0)                                             //      8  3  5

        val field = game.field
        assertEquals(field[0, 0], 0)                                //      0  7  1
    }

    @Test
    fun `check game over with not sorted field`(){
        game.field = FifteenFieldHelper.testFifteenField()

        val result = game.checkGameOver()

        assert(!result)
    }

    @Test
    fun `check game over with sorted field`() {
        game.field = FifteenFieldHelper.sortedFifteenField()

        val result = game.checkGameOver()

        assert(result)
    }
}