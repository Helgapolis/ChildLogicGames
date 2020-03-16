package ru.bazalikova.puzzle.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bazalikova.puzzle.data.IPuzzleRepository
import ru.bazalikova.puzzle.data.PuzzleRepository
import ru.bazalikova.puzzle.presentation.PuzzlePresenter

@Module
abstract class PuzzleModule {

    @Module
    companion object{
        @Provides
        @PuzzleScope
        fun providePresenter(repository: IPuzzleRepository): PuzzlePresenter {
            return PuzzlePresenter(repository)
        }
    }

    @Binds abstract fun bindRepository(model: PuzzleRepository): IPuzzleRepository
}