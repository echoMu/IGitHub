package com.echomu.github.ui.user.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.echomu.github.EXTRA_LOGOUT
import com.echomu.github.R
import com.echomu.github.databinding.ActivityPersonalCenterBinding
import com.echomu.github.ui.BaseActivity
import com.echomu.github.ui.main.activity.MainActivity
import com.echomu.github.ui.user.viewmodel.PersonalCenterViewModel
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by echoMu.
 */
class PersonalCenterActivity
    : BaseActivity<ActivityPersonalCenterBinding, PersonalCenterViewModel>(), PersonalCenterViewModel.Handlers {

    override val layoutRes: Int = R.layout.activity_personal_center
    override val viewModel by lifecycleScope.viewModel<PersonalCenterViewModel>(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@PersonalCenterActivity
            viewModel = this@PersonalCenterActivity.viewModel
            handlers = this@PersonalCenterActivity
        }
        viewModel.showTitle(getString(R.string.personal_center))
        viewModel.login()
    }

    override fun onNavigationIconClick(view: View) =
            finish()

    override fun onLogoutClick(view: View) {
        viewModel.logout()
        startActivity(Intent(this, MainActivity::class.java).apply {
            putExtra(EXTRA_LOGOUT, true)
        })
    }

}