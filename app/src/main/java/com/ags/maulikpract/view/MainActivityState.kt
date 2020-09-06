package com.ags.maulikpract.view

import androidx.lifecycle.MutableLiveData
import com.ags.maulikpract.base.BaseState
import com.ags.maulikpract.model.Department
import com.ags.maulikpract.utils.initWith

class MainActivityState : BaseState {
    val isDropDownOpen = MutableLiveData<Boolean>().initWith(false)
    var departmentList = MutableLiveData<MutableList<Department>>()

}