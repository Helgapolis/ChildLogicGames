package ru.bazalikova.puzzle.di

import android.content.Context
import dagger.Component
import ru.bazalikova.coreapi.mediator.ProvidersFacade
import ru.bazalikova.puzzle.presentation.PuzzleActivity

@Component(dependencies = [ProvidersFacade::class], modules = [PuzzleModule::class])
@PuzzleScope
interface PuzzleComponent{
    val context: Context

    companion object{
         fun create(providersFacade: ProvidersFacade): PuzzleComponent {
             return DaggerPuzzleComponent.builder()
                 .providersFacade(providersFacade)
                 .build()
         }
    }

    fun inject(activity: PuzzleActivity)
}