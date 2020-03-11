package ru.bazalikova.menu.di

import android.content.Context
import dagger.Component
import ru.bazalikova.coreapi.mediator.ProvidersFacade
import ru.bazalikova.menu.presentation.MenuActivity

@Component(dependencies = [ProvidersFacade::class], modules = [MenuModule::class])
@MenuScope
interface MenuComponent{
    val context: Context

    companion object{
        fun create(providersFacade: ProvidersFacade): MenuComponent {
            return DaggerMenuComponent.builder()
                .providersFacade(providersFacade)
                .build()
        }
    }

    fun inject(activity: MenuActivity)
}