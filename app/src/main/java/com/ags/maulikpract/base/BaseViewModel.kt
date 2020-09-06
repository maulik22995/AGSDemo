package com.ags.maulikpract.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ags.maulikpract.base.BaseState
import kotlinx.coroutines.cancel
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseViewModel : ViewModel(),KoinComponent {


    abstract fun provieState() : BaseState

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}