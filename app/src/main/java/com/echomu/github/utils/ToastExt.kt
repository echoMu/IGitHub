package com.echomu.github.utils

import android.widget.Toast
import com.echomu.github.AndroidGenericFrameworkApplication

/**
 * Created by echoMu.
 */
fun toastShort(text: String) =
        Toast.makeText(AndroidGenericFrameworkApplication.instance, text, Toast.LENGTH_SHORT).show()

fun toastLong(text: String) =
        Toast.makeText(AndroidGenericFrameworkApplication.instance, text, Toast.LENGTH_LONG).show()
