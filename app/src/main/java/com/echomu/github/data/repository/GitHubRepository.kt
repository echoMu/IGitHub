package com.echomu.github.data.repository

import com.echomu.github.data.model.repository.RepositoryData
import com.echomu.github.data.remote.repository.RepositoryRemoteDataSource
import com.echomu.github.utils.Language
import java.time.LocalDateTime

/**
 * Created by echoMu.
 */
class GitHubRepository(
        private val remoteDataSource: RepositoryRemoteDataSource
) {

    fun getDefaultLanguageNames(): List<String> =
            listOf(
                    Language.KOTLIN.languageName,
                    Language.JAVA.languageName,
                    Language.SWIFT.languageName,
                    Language.JAVA_SCRIPT.languageName,
                    Language.PYTHON.languageName,
                    Language.GO.languageName,
                    Language.CSS.languageName
            )

    fun getMoreLanguageNames(): List<String> =
            listOf(
                    Language.PHP.languageName,
                    Language.RUBY.languageName,
                    Language.C_PLUS_PLUS.languageName,
                    Language.C.languageName,
                    Language.OTHER.languageName
            )

    suspend fun getRepositories(languageName: String): List<RepositoryData> =
            remoteDataSource.fetchRepositories(
                    languageName = languageName,
                    fromDateTime = LocalDateTime.now().minusMonths(1)
            )

}