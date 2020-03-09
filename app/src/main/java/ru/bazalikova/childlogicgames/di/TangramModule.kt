package ru.bazalikova.childlogicgames.di

import dagger.Component
import dagger.Module
import ru.bazalikova.childlogicgames.tangram.TangramActivity

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

@Module
class TangramModule