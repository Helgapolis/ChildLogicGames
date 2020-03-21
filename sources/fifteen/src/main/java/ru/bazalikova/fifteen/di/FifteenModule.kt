package ru.bazalikova.fifteen.di

import dagger.Module
import dagger.Provides
import ru.bazalikova.fifteen.data.Game
import ru.bazalikova.fifteen.presentation.FifteenPresenter

@Module
abstract class FifteenModule {
    @Module
    companion object{
        @Provides
        @FifteenScope
        fun providePresenter(game: Game): FifteenPresenter {
            return FifteenPresenter(game)
        }
    }
}