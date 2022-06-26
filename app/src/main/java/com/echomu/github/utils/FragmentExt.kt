package com.echomu.github.utils

import androidx.fragment.app.Fragment
import com.echomu.github.ui.BaseActivity

/**
 * Created by echoMu.
 */
inline fun <reified T : BaseActivity<*, *>> Fragment.startActivity() =
        startActivity(android.content.Intent(context, T::class.java))