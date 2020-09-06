package com.ags.maulikpract.view

import com.ags.maulikpract.base.BaseState
import com.ags.maulikpract.base.BaseViewModel
import com.ags.maulikpract.model.Department

class MainActivityViewModel(private val state : MainActivityState) : BaseViewModel() {
    override fun provieState(): BaseState = state

    fun getAllDepartment(){
        val data = ArrayList<Department>()
        data.add(Department(-1,"Select Department"))
        data.add(Department(0,"Android"))
        data.add(Department(1,"iOs"))
        data.add(Department(2,"Web"))
        state.departmentList.value = data
    }
}