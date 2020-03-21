package ru.bazalikova.fifteen.data

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class GameTest {

    private val game = Game()

    @Before
    fun setUp(){
        val field = FifteenField(3, 3)
        field[0, 0] = 7; field[0, 1] = 1; field[0, 2] = 0
        field[1, 0] = 6; field[1, 1] = 2; field[1, 2] = 4
        field[2, 0] = 8; field[2, 1] = 3; field[2, 2] = 5

        game.field = field
    }

    @Test
    fun `should contains digits in range 0_8`(){
        val field = game.build()

        val list = mutableListOf<Int>()

        for (row in 0 until field.width) {
            for (column in 0 until field.height) {
                list.add(field[row, column])
            }
        }

        assert(list.contains(0))
        assert(list.contains(1))
        assert(list.contains(2))
        assert(list.contains(3))
        assert(list.contains(4))
        assert(list.contains(5))
        assert(list.contains(6))
        assert(list.contains(7))
        assert(list.contains(8))
    }

    @Test
    fun `move cell to empty field`(){                                   // 7  1->0
        val result = game.move(0, 1)                             // 6  2  4
                                                                       // 8  3  5

        val field = game.field
        assert(result)
        assertEquals(field[0, 0], 7)                       // 7  0  1
        assertEquals(field[0, 1], 0)                       // 6  2  4
        assertEquals(field[0, 2], 1)                       // 8  3  5
        assertEquals(field[1, 0], 6)
        assertEquals(field[1, 1], 2)
        assertEquals(field[1, 2], 4)
        assertEquals(field[2, 0], 8)
        assertEquals(field[2, 1], 3)
        assertEquals(field[2, 2], 5)
    }

    @Test
    fun `move cell to non empty field`(){
        val result = game.move(1, 1)

        val field = game.field
        assert(!result)
        assertEquals(field[0, 0], 7)                       // 7  1  0
        assertEquals(field[0, 1], 1)                       // 6  2/ 4
        assertEquals(field[0, 2], 0)                       // 8  3  5
        assertEquals(field[1, 0], 6)
        assertEquals(field[1, 1], 2)
        assertEquals(field[1, 2], 4)
        assertEquals(field[2, 0], 8)
        assertEquals(field[2, 1], 3)
        assertEquals(field[2, 2], 5)
    }

    @Test
    fun `move row to empty field`(){                                        // ->  7  1  0
        val result = game.move(0, 0)                                 //      6  2  4
                                                                           //      8  3  5
        val field = game.field
        assert(result)
        assertEquals(field[0, 0], 0)                           // 0  7  1
        assertEquals(field[0, 1], 7)                           // 6  2  4
        assertEquals(field[0, 2], 1)                           // 8  3  5
        assertEquals(field[1, 0], 6)
        assertEquals(field[1, 1], 2)
        assertEquals(field[1, 2], 4)
        assertEquals(field[2, 0], 8)
        assertEquals(field[2, 1], 3)
        assertEquals(field[2, 2], 5)
    }

    @Test
    fun `check game over with not sorted field`(){
        val result = game.checkGameOver()

        assert(!result)
    }

    @Test
    fun `check game over with sorted field`(){
        val field = game.field
        field[0, 0] = 1; field[0, 1] = 2; field[0, 2] = 3
        field[1, 0] = 4; field[1, 1] = 5; field[1, 2] = 6
        field[2, 0] = 7; field[2, 1] = 8; field[2, 2] = 0

        val result = game.checkGameOver()

        assert(result)
    }
}