package ru.bazalikova.menu.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.bazalikova.menu.data.IMenuModel
import ru.bazalikova.menu.data.MenuModel
import ru.bazalikova.menu.presentation.MenuPresenter

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