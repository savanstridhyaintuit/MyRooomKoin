package com.savan.myroomkoin.di

import androidx.room.Room
import com.savan.myroomkoin.database.EmployeeDatabase
import com.savan.myroomkoin.repository.EmpRepository
import com.savan.myroomkoin.viewmodel.EmpViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val empAppModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            EmployeeDatabase::class.java,
            "employeeDatabase"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single { get<EmployeeDatabase>().empDao() }
    single { get<EmployeeDatabase>().photoDao() }
}

val repositoryModule = module {
    single { EmpRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { EmpViewModel(get()) }
}

private val loadFeatures by lazy {
    loadKoinModules(listOf(empAppModule, repositoryModule, viewModelModule))
}

fun injectFeature() = loadFeatures