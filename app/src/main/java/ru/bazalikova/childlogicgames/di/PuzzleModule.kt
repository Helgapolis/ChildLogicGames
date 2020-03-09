package ru.bazalikova.childlogicgames.di

import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.bazalikova.childlogicgames.puzzle.IPuzzleModel
import ru.bazalikova.childlogicgames.puzzle.PuzzleActivity
import ru.bazalikova.childlogicgames.puzzle.PuzzleModel
import ru.bazalikova.childlogicgames.puzzle.PuzzlePresenter

@Component(dependencies = [AppComponent::class], modules = [PuzzleModule::class])
@PuzzleScope
interface PuzzleComponent{
        val context: Context

    companion object{
        fun create(appComponent: AppComponent): PuzzleComponent{
            return DaggerPuzzleComponent.builder()
                .appComponent(appComponent)
                .build()
        }
    }

    fun inject(activity: PuzzleActivity)
}

@Module
abstract class PuzzleModule {

    @Module
    companion object{
        @Provides
        @PuzzleScope
        fun providePresenter(model: IPuzzleModel): PuzzlePresenter{
            return PuzzlePresenter(model)
        }
    }

    @Binds abstract fun bindModel(model: PuzzleModel): IPuzzleModel
}