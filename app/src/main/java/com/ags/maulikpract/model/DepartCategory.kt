package com.ags.maulikpract.model

data class DepartCategory(
    var id: Int = 0,
    var departName: String = "",
    val employee: ArrayList<Employee> = ArrayList()
)