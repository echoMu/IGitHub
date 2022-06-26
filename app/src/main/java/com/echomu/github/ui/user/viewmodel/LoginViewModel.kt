package com.echomu.github.ui.user.viewmodel

import android.text.Editable
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
class LoginViewModel(
        private val repository: UserInfoRepository
) : BaseViewModel() {

    interface Handlers {

        fun onLoginClick(view: View)

    }

}