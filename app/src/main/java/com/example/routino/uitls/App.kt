package com.example.routino.uitls

import android.app.Application
import androidx.room.Room
import com.example.routino.data.db.AppDatabase
import com.example.routino.data.repository.home.homeRepository
import com.example.routino.data.repository.home.homeRepositoryImpl
import com.example.routino.ui.HomeFragment.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(home)
        }
    }

    val home = module {

        single {
            Room.databaseBuilder(
                this@App,
                AppDatabase::class.java,
                "routino"
            ).allowMainThreadQueries().build()
        }

        single {
            val database = get<AppDatabase>()
            database.AppDAO()
        }

        factory<homeRepository> { homeRepositoryImpl(get()) }
        viewModel { HomeViewModel(get()) }
    }


}