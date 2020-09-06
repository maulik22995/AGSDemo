package com.ags.maulikpract.utils

import android.util.Patterns

fun String.isEmailAddressValidPattern(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}