package com.echomu.github.ui.repository.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.echomu.github.EXTRA_LANGUAGE
import com.echomu.github.FRAGMENT_TAG_REPOSITORY
import com.echomu.github.R
import com.echomu.github.databinding.FragmentRepositoryBinding
import com.echomu.github.databinding.LayoutErrorBinding
import com.echomu.github.ui.BaseFragment
import com.echomu.github.ui.BaseViewModel
import com.echomu.github.ui.repository.adapter.RepositoryAdapter
import com.echomu.github.ui.repository.viewmodel.RepositoryViewModel
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

/**
 * Created by echoMu.
 */
class RepositoryFragment private constructor()
    : BaseFragment<FragmentRepositoryBinding, RepositoryViewModel>(), BaseViewModel.Handlers {

    override val layoutRes: Int = R.layout.fragment_repository
    override val viewModel by lifecycleScope.viewModel<RepositoryViewModel>(this)
    override val transactionTag: String = FRAGMENT_TAG_REPOSITORY

    private lateinit var language: String
    private val adapter = RepositoryAdapter()
    private var errorView: View? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        language = getLanguageFromArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUI()
        initObservers()
        initData()
    }

    override fun onRetryClick(view: View) {
        viewModel.getRepositories(language)
    }

    private fun getLanguageFromArgs(): String =
            arguments?.run { getString(EXTRA_LANGUAGE, "") } ?: ""

    private fun initUI() {
        with(binding) {
            lifecycleOwner = this@RepositoryFragment
            viewModel = this@RepositoryFragment.viewModel
        }
        initRecyclerView()
        initErrorView()
    }

    private fun initRecyclerView() {
        with(binding.rvRepository) {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RepositoryFragment.adapter
        }
    }

    private fun initErrorView() {
        binding.vsError.setOnInflateListener { _, inflated ->
            DataBindingUtil.bind<LayoutErrorBinding>(inflated)?.run {
                lifecycleOwner = this@RepositoryFragment
                viewModel = this@RepositoryFragment.viewModel
                handlers = this@RepositoryFragment
            }
        }
    }

    private fun initObservers() {
        viewModel.isShowErrorView.observe(viewLifecycleOwner, Observer {
            handleErrorView(it)
        })
    }

    private fun handleErrorView(isShowErrorView: Boolean) {
        if (isShowErrorView) {
            errorView
                    ?.run { visibility = View.VISIBLE }
                    ?: binding.vsError.viewStub?.inflate()?.also { errorView = it }
        } else {
            errorView?.visibility = View.GONE
        }
    }

    private fun initData() =
            with(viewModel) {
                getRepositories(language)
                repositories.observe(viewLifecycleOwner, Observer {
                    adapter.setItems(it)
                })
            }

    companion object {
        //单例模式
        fun newInstance(language: String): RepositoryFragment =
                RepositoryFragment().apply {
                    arguments = Bundle().apply {
                        putString(EXTRA_LANGUAGE, language)
                    }
                }
    }

}