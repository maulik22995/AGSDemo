package com.ags.maulikpract.model

import android.text.TextUtils
import android.view.View

import android.widget.EditText
import android.widget.TextView

import androidx.databinding.BindingAdapter
import com.ags.maulikpract.utils.isEmailAddressValidPattern


data class Employee(
    var email: String = "",
    var deptId: Int = 0,
    var isValidate: Boolean = false,
    var isEnabled : Boolean = false
)

@BindingAdapter("emailValidater")
fun emailValidator(tvEmail: TextView, email: String) {
    if (email.length == 0) {
        tvEmail.visibility = View.VISIBLE
        tvEmail.text = "Please Enter Email"
    }else if(!email.isEmailAddressValidPattern()){
        tvEmail.visibility = View.VISIBLE
        tvEmail.text = "Please Enter valid Email"
    }else{
        tvEmail.visibility = View.GONE
    }
}