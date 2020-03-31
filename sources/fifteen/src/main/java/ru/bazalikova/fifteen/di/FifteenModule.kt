package ru.bazalikova.fifteen.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bazalikova.fifteen.domain.*
import ru.bazalikova.fifteen.presentation.FifteenPresenter

@Module
abstract class FifteenModule {
    @Module
    companion object{
        @Provides
        @FifteenScope
        fun providePresenter(getRandomFieldUseCase: GetRandomFieldUseCase, moveToCellUseCase: MoveToCellUseCase, checkGameOverUseCase: CheckGameOverUseCase): FifteenPresenter {
            return FifteenPresenter(getRandomFieldUseCase, moveToCellUseCase, checkGameOverUseCase)
        }
    }

    @Binds
    abstract fun bindGetRandomUseCase(getRandomFieldUseCase: GetRandomFieldMainUseCase): GetRandomFieldUseCase

    @Binds
    abstract fun bindMoveToCellUseCase(moveToCellUseCase: MoveToCellMainUseCase): MoveToCellUseCase

    @Binds
    abstract fun bindCheckGameOverUseCase(checkGameOverUseCase: CheckGameOverMainUseCase): CheckGameOverUseCase
}