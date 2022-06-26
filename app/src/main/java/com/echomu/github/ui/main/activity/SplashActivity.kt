package com.echomu.github.ui.main.activity

import android.os.Bundle
import androidx.lifecycle.Observer
import com.echomu.github.R
import com.echomu.github.databinding.ActivitySplashBinding
import com.echomu.github.ui.BaseActivity
import com.echomu.github.ui.main.viewmodel.SplashViewModel
import com.echomu.github.ui.user.activity.RegisterAndLoginActivity
import com.echomu.github.utils.otherwise
import com.echomu.github.utils.startActivity
import com.echomu.github.utils.yes
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by echoMu.
 */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val layoutRes: Int = R.layout.activity_splash
    override val viewModel by lifecycleScope.viewModel<SplashViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        with(viewModel) {
            navigateToPage()
            isNavigateToMainActivity.observe(this@SplashActivity, Observer {
                it
                        .yes { startActivity<MainActivity>() }
                        .otherwise { startActivity<RegisterAndLoginActivity>() }
                finish()
            })
        }
    }

}