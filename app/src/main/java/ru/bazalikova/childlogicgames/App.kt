package ru.bazalikova.childlogicgames

import android.app.Application
import ru.bazalikova.childlogicgames.di.AppComponent
import ru.bazalikova.coreapi.mediator.AppWithFacade

open class App: Application(), AppWithFacade {

    companion object{
        private var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()
        getFacade().inject(this)
    }

    override fun getFacade(): AppComponent {
        return appComponent ?: AppComponent.create(this).also{
            appComponent = it
        }
    }
}