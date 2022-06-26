package com.echomu.github.ui.repository.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.echomu.github.data.model.repository.RepositoryData
import com.echomu.github.data.repository.GitHubRepository
import com.echomu.github.ui.BaseViewModel
import com.echomu.github.ui.UIState

/**
 * Created by echoMu.
 */
class RepositoryViewModel(
        private val repository: GitHubRepository
) : BaseViewModel() {

    private val _isShowRepositoryView = MutableLiveData<Boolean>()
    val isShowRepositoryView: LiveData<Boolean> = _isShowRepositoryView

    private val _repositories = MutableLiveData<List<RepositoryData>>()
    val repositories: LiveData<List<RepositoryData>> = _repositories

    fun getRepositories(languageName: String) =
            launch(
                    uiState = UIState(isShowLoadingView = true, isShowErrorView = true),
                    block = { repository.getRepositories(languageName) },
                    success = {
                        if (it.isNotEmpty()) {
                            _repositories.value = it
                            _isShowRepositoryView.value = true
                        }
                    }
            )

}