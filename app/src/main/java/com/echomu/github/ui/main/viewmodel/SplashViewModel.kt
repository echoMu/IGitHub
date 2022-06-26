package com.echomu.github.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.echomu.github.data.repository.UserInfoRepository
import com.echomu.github.ui.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by echoMu.
 */
class SplashViewModel(
        private val repository: UserInfoRepository
) : BaseViewModel() {

    private val _isNavigateToMainActivity = MutableLiveData<Boolean>()
    var isNavigateToMainActivity: LiveData<Boolean> = _isNavigateToMainActivity

    fun navigateToPage() {
        viewModelScope.launch {
            delay(1000)
            _isNavigateToMainActivity.value = repository.isLogin()
        }
    }

}