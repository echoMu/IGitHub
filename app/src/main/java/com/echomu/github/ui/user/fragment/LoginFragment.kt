package com.echomu.github.ui.user.fragment

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.lifecycle.Observer
import com.echomu.github.FRAGMENT_TAG_LOGIN
import com.echomu.github.R
import com.echomu.github.databinding.FragmentLoginBinding
import com.echomu.github.ui.BaseFragment
import com.echomu.github.ui.main.activity.MainActivity
import com.echomu.github.ui.user.viewmodel.LoginViewModel
import com.echomu.github.utils.startActivity
import com.echomu.github.utils.yes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import com.echomu.github.ui.user.activity.GithubOauthActivity

/**
 * Created by echoMu.
 */
class LoginFragment private constructor()
    : BaseFragment<FragmentLoginBinding, LoginViewModel>(), LoginViewModel.Handlers {

    override val layoutRes: Int = R.layout.fragment_login
    override val viewModel by lifecycleScope.viewModel<LoginViewModel>(this)
    override val transactionTag: String = FRAGMENT_TAG_LOGIN

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) =
            with(binding) {
                lifecycleOwner = this@LoginFragment
                viewModel = this@LoginFragment.viewModel
                handlers = this@LoginFragment
            }.also {
                registerLoadingProgressBarEvent()
                registerSnackbarEvent()
            }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun onLoginClick(view: View) {
        startActivity<GithubOauthActivity>()
    }

    companion object {
        //单例模式构造
        fun newInstance() = LoginFragment()
    }

}