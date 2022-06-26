package com.echomu.github.ui.main.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.echomu.github.data.repository.GitHubRepository
import com.echomu.github.ui.BaseViewModel
import com.echomu.github.utils.otherwise
import com.echomu.github.utils.yes

/**
 * Created by echoMu.
 */
class MainViewModel(
        val repository: GitHubRepository
) : BaseViewModel() {

    private val _languageNames = MutableLiveData<MutableList<String>>().apply {
        value = mutableListOf<String>().apply { addAll(repository.getDefaultLanguageNames()) }
    }
    val languageNames: LiveData<MutableList<String>> = _languageNames

    private var _isShowAdd = MutableLiveData<Boolean>().apply { value = true }
    val isShowAdd: LiveData<Boolean> = _isShowAdd

    var index = 0

    fun getDefaultLanguageNames(): List<String> =
            repository.getDefaultLanguageNames()

    fun getDefaultLanguageNamesCount(): Int =
            repository.getDefaultLanguageNames().size

    private fun getAllLanguageNames(): Int =
            repository.getDefaultLanguageNames().size + repository.getMoreLanguageNames().size

    fun getLastLanguageNameIndex(): Int =
            _languageNames.value?.lastIndex ?: 0

    fun getLastLanguageName(): String =
            _languageNames.value
                    ?.let {
                        it[it.lastIndex]
                    }
                    ?: ""

    fun addLanguageName() =
            _languageNames.value?.let {
                it.add(repository.getMoreLanguageNames()[index])
                index++
                notifyLanguageNamesUpdate(it)
                (it.size == getAllLanguageNames())
                        .yes { _isShowAdd.value = false }
                        .otherwise { _isShowAdd.value = true }
            }

    private fun notifyLanguageNamesUpdate(languageNames: MutableList<String>) {
        _languageNames.value = languageNames
    }

    interface Handlers {

        fun onPersonalCenterClick(view: View)

        fun onAddClick(view: View)

    }

}