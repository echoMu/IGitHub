package com.echomu.github.utils

import android.app.Activity
import android.content.Intent
import com.echomu.github.ui.BaseActivity

/**
 * Created by echoMu.
 */
inline fun <reified T : BaseActivity<*, *>> Activity.startActivity() =
        startActivity(Intent(this, T::class.java))