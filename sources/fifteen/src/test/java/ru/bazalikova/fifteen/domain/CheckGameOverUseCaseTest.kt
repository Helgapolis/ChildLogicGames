package ru.bazalikova.fifteen.domain

import org.junit.Test
import ru.bazalikova.fifteen.data.FifteenFieldHelper

class CheckGameOverUseCaseTest {

    private val checkGameOverUseCase = CheckGameOverMainUseCase()

    @Test
    fun `check game over with not sorted field`(){
        val field = FifteenFieldHelper.testFifteenField()

        val result = checkGameOverUseCase.invoke(field)

        assert(!result)
    }

    @Test
    fun `check game over with sorted field`() {
        val field = FifteenFieldHelper.sortedFifteenField()

        val result = checkGameOverUseCase.invoke(field)

        assert(result)
    }
}