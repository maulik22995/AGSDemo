package com.ags.maulikpract.di

import com.ags.maulikpract.view.MainActivityState
import org.koin.dsl.module

val stateModule = module {

    factory {
        MainActivityState()
    }
}
