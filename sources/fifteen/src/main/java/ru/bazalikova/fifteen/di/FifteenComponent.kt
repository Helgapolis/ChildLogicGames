package ru.bazalikova.fifteen.di

import dagger.Component
import ru.bazalikova.fifteen.presentation.FifteenActivity

@Component(modules = [FifteenModule::class])
@FifteenScope
interface FifteenComponent{

    companion object{
        fun create(): FifteenComponent {
            return DaggerFifteenComponent.create()
        }
    }

    fun inject(activity: FifteenActivity)
}