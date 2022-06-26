package com.echomu.github.ui.user.activity

import android.os.Bundle
import com.echomu.github.R
import com.echomu.github.databinding.ActivityRegisterAndLoginBinding
import com.echomu.github.ui.BaseActivity
import com.echomu.github.ui.NoViewModel
import com.echomu.github.ui.user.fragment.LoginFragment

/**
 * Created by echoMu.
 */
class RegisterAndLoginActivity : BaseActivity<ActivityRegisterAndLoginBinding, NoViewModel>() {

    override val layoutRes: Int = R.layout.activity_register_and_login
    override val viewModel = NoViewModel()
    override val containId: Int = R.id.fl_content

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment(LoginFragment.newInstance())
    }

}