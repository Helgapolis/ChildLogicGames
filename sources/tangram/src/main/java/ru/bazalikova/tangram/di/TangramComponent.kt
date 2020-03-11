package ru.bazalikova.tangram.di

import dagger.Component
import ru.bazalikova.tangram.presentation.TangramActivity

@Component(modules = [TangramModule::class])
@TangramScope
interface TangramComponent {
    companion object {
        fun create(): TangramComponent {
            return DaggerTangramComponent.create()
        }
    }

    fun inject(activity: TangramActivity)
}