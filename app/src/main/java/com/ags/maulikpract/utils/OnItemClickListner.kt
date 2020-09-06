package com.ags.maulikpract.utils

import android.view.View

interface OnItemClickListener {
    fun onItemClick(view : View,data : Any,position: Int)
}