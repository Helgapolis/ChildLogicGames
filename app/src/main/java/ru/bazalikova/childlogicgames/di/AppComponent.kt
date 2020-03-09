package ru.bazalikova.childlogicgames.di

import android.content.Context
import dagger.Component
import ru.bazalikova.childlogicgames.App
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    companion object {

        fun create(context: Context): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
        }
    }

    fun getContext(): Context

    fun inject(ap: App)
}