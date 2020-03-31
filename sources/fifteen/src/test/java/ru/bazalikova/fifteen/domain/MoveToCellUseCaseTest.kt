package ru.bazalikova.fifteen.domain

import junit.framework.TestCase
import org.junit.Test
import ru.bazalikova.fifteen.data.FifteenFieldHelper

class MoveToCellUseCaseTest {
    private val moveToCellUseCase = MoveToCellMainUseCase()

    @Test
    fun `move second cell to empty field - and check that it is possible`(){
        val field = FifteenFieldHelper.testFifteenField()               // 7  1->0
        val result = moveToCellUseCase.invoke(field, 0, 1)       // 6  2  4
                                                                       // 8  3  5
        assert(result)
    }

    @Test
    fun `move second cell to empty field - and check that position is correct`(){
        val field = FifteenFieldHelper.testFifteenField()           // 7  1->0
        moveToCellUseCase.invoke(field, 0, 1)

        TestCase.assertEquals(field[0, 2], 1)                // 7  0  1
    }

    @Test
    fun `move fourth cell to non empty field - and check that it is not possible`(){
        val field = FifteenFieldHelper.testFifteenField()              // 7  1  0
        val result = moveToCellUseCase.invoke(field, 1, 1)       // 6  2/ 4
                                                                       // 8  3  5
        assert(!result)
    }

    @Test
    fun `move fourth cell to non empty field - and check that position is not changed`(){
        val field = FifteenFieldHelper.testFifteenField()          // 7  1  0
        moveToCellUseCase.invoke(field, 1, 1)               // 6  2/ 4
                                                                   // 8  3  5

        TestCase.assertEquals(field[1, 1], 2)
    }

    @Test
    fun `move row to empty field - and check that it is possible`(){
        val field = FifteenFieldHelper.testFifteenField()               // ->   7  1  0
                                                                        //      6  2  4
        val result = moveToCellUseCase.invoke(field, 0, 0)       //      8  3  5

        assert(result)                                                 //      0  7  1
    }

    @Test
    fun `move row to empty field - and check that empty field is changed position`(){
        val field = FifteenFieldHelper.testFifteenField()                  // ->   7  1  0
                                                                           //      6  2  4
        moveToCellUseCase.invoke(field, 0, 0)                       //      8  3  5


        TestCase.assertEquals(field[0, 0], 0)                      //      0  7  1
    }
}