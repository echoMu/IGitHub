package com.echomu.github.ui.main.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.echomu.github.EXTRA_LOGOUT
import com.echomu.github.R
import com.echomu.github.databinding.ActivityMainBinding
import com.echomu.github.ui.BaseActivity
import com.echomu.github.ui.main.viewmodel.MainViewModel
import com.echomu.github.ui.repository.fragment.RepositoryFragment
import com.echomu.github.ui.user.activity.PersonalCenterActivity
import com.echomu.github.ui.user.activity.RegisterAndLoginActivity
import com.echomu.github.utils.registerOnTabSelectedListener
import com.echomu.github.utils.startActivity
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel
import androidx.lifecycle.Observer
import com.echomu.github.utils.toastShort

/**
 * Created by echoMu.
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainViewModel.Handlers {

    override val layoutRes: Int = R.layout.activity_main
    override val viewModel by lifecycleScope.viewModel<MainViewModel>(this)

    private lateinit var tlRepository: TabLayout
    private lateinit var vpRepository: ViewPager2

    private lateinit var repositoryFragments: MutableList<RepositoryFragment>
    private lateinit var adapter: OrderFragmentStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
            handlers = this@MainActivity
        }

        initView()
        initData()
        grantPermission()
    }

    private fun grantPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //没有权限则申请权限
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1);
            } else {
                //有权限直接执行,docode()不用做处理
            }
        } else {
            //小于6.0，不用申请权限，直接执行
        }
    }

    private fun initView() {
        tlRepository = binding.tlRepository
        vpRepository = binding.vpRepository

        repositoryFragments = mutableListOf<RepositoryFragment>().apply {
            viewModel.getDefaultLanguageNames().forEach { add(RepositoryFragment.newInstance(it)) }
        }
        adapter = OrderFragmentStateAdapter(this, repositoryFragments)
        viewModel.getDefaultLanguageNames().forEach {
            tlRepository.addTab(tlRepository.newTab().setText(it))
        }

        tlRepository.addOnTabSelectedListener(registerOnTabSelectedListener {
            onTabSelected { vpRepository.currentItem = it?.position ?: 0 }
        })

        vpRepository.adapter = adapter
        vpRepository.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) =
                    tlRepository.setScrollPosition(position, 0.0F, true)
        })
    }

    private fun initData() =
            with(viewModel) {
                languageNames.observe(this@MainActivity, Observer {
                    if (it.size > getDefaultLanguageNamesCount()) {
                        tlRepository.addTab(tlRepository.newTab().setText(getLastLanguageName()))
                        repositoryFragments.add(RepositoryFragment.newInstance(getLastLanguageName()))
                        adapter.notifyItemInserted(getLastLanguageNameIndex())
                        vpRepository.currentItem = getLastLanguageNameIndex()
                    }
                })
            }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent
                ?.getBooleanExtra(EXTRA_LOGOUT, false)
                ?.takeIf { it }
                ?.let {
                    startActivity<RegisterAndLoginActivity>()
                    finish()
                }
    }

    override fun onPersonalCenterClick(view: View) =
            startActivity<PersonalCenterActivity>()

    override fun onAddClick(view: View) {
        viewModel.addLanguageName()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //执行代码,这里是已经申请权限成功了,可以不用做处理
                } else {
                    toastShort("权限申请失败")
                }
        }
    }
}

class OrderFragmentStateAdapter(fragmentActivity: FragmentActivity,
                                private val repositoryFragments: List<RepositoryFragment>)
    : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment =
            repositoryFragments[position]

    override fun getItemCount(): Int = repositoryFragments.size

}