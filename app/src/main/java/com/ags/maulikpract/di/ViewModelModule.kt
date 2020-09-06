package com.ags.maulikpract.di

import com.ags.maulikpract.view.MainActivityViewModel
import org.koin.dsl.module


val viewModelModule = module {

    factory {
        MainActivityViewModel(get())
    }
}
