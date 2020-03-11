package ru.bazalikova.menu.di

import dagger.Component
import ru.bazalikova.menu.presentation.MenuActivity

@Component(modules = [MenuModule::class])
@MenuScope
interface MenuComponent{

    companion object{
        fun create(): MenuComponent {
            return DaggerMenuComponent.create()
        }
    }

    fun inject(activity: MenuActivity)
}