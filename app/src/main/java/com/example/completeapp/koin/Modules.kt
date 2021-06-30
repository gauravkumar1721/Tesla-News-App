package com.example.completeapp.koin

import com.example.completeapp.data.DataFetch
import com.example.completeapp.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MyViewModel(get()) }
}
val DataFetchModule = module {
    single { DataFetch.getInstance() }
}


