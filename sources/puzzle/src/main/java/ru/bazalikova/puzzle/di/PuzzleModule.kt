package ru.bazalikova.puzzle.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bazalikova.puzzle.data.IPuzzleModel
import ru.bazalikova.puzzle.data.PuzzleModel
import ru.bazalikova.puzzle.presentation.PuzzlePresenter

@Module
abstract class PuzzleModule {

    @Module
    companion object{
        @Provides
        @PuzzleScope
        fun providePresenter(model: IPuzzleModel): PuzzlePresenter {
            return PuzzlePresenter(model)
        }
    }

    @Binds abstract fun bindModel(model: PuzzleModel): IPuzzleModel
}