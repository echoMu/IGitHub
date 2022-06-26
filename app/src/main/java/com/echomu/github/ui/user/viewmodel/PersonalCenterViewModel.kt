package com.echomu.github.ui.user.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.echomu.github.data.remote.ExceptionHandler
import com.echomu.github.data.repository.UserInfoRepository
import com.echomu.github.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

/**
 * Created by echoMu.
 */
class PersonalCenterViewModel(
        private val repository: UserInfoRepository
) : BaseViewModel() {

    private val _avatarUrl = MutableLiveData<String>().apply {
        value = repository.getAvatarUrl()
    }
    val avatarUrl: LiveData<String> = _avatarUrl

    private val _name = MutableLiveData<String>().apply {
        value = repository.getName()
    }
    val name: LiveData<String> = _name

    fun showTitle(title: String) {
        _title.value = title
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun login() =
            launchUI {
                launchFlow {
                    repository.run {
                        repository.getUserInfo()
                    }
                }
                        .flowOn(Dispatchers.IO)
                        .onStart { uiLiveEvent.showLoadingProgressBarEvent.call() }
                        .catch {
                            val responseThrowable = ExceptionHandler.handleException(it)
                            uiLiveEvent.showSnackbarEvent.value = "${responseThrowable.errorCode}:${responseThrowable.errorMessage}"
                        }
                        .onCompletion { uiLiveEvent.dismissLoadingProgressBarEvent.call() }
                        .collect {
                            repository.run {
                                _name.value = it.login
                                _avatarUrl.value = it.avatarUrl
                                cacheUserId(it.id)
                                cacheName(it.login)
                                cacheAvatarUrl(it.avatarUrl)
                            }
                        }
            }

    fun logout() =
            repository.logout()

    interface Handlers : BaseViewModel.Handlers {

        fun onLogoutClick(view: View)

    }

}