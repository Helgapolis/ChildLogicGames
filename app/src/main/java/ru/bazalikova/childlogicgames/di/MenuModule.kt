package ru.bazalikova.childlogicgames.di

import android.content.Context
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.bazalikova.childlogicgames.menu.IMenuModel
import ru.bazalikova.childlogicgames.menu.MenuActivity
import ru.bazalikova.childlogicgames.menu.MenuModel
import ru.bazalikova.childlogicgames.menu.MenuPresenter

@Component(dependencies = [AppComponent::class], modules = [MenuModule::class])
@MenuScope
interface MenuComponent{
    val context: Context

    companion object{
        fun create(appComponent: AppComponent): MenuComponent{
            return DaggerMenuComponent.builder()
                .appComponent(appComponent)
                .build()
        }
    }

    fun inject(activity: MenuActivity)
}

@Module
abstract class MenuModule {

    @Module
    companion object{
        @Provides
        @MenuScope
        fun providePresenter(model: IMenuModel): MenuPresenter {
            return MenuPresenter(model)
        }
    }

    @Binds
    abstract fun bindModel(model: MenuModel): IMenuModel
}