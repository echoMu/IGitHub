package com.echomu.github.data

import com.google.common.truth.Truth.assertThat
import com.echomu.github.data.remote.repository.RepositoryRemoteDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.LocalDateTime

/**
 * Created by echoMu.
 */
@RunWith(JUnit4::class)
class RepositoryRemoteDataSourceTest {

    @get:Rule
    val mockWebServer = MockWebServer()

    private lateinit var remoteDataSource: RepositoryRemoteDataSource

    @Before
    fun setUp() {
        remoteDataSource = RepositoryRemoteDataSource(
                Retrofit.Builder()
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(mockWebServer.url("/").toString())
                        .build()
        )
    }

    @Test
    fun fetchRepositories() {
        runBlocking {
            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(repositoryDataJson))
            remoteDataSource.fetchRepositories(
                    languageName = "Kotlin",
                    fromDateTime = LocalDateTime.now().minusMonths(1)
            ).first().run {
                assertThat(id).isEqualTo(repositoryData.id)
                assertThat(name).isEqualTo(repositoryData.name)
                assertThat(description).isEqualTo(repositoryData.description)
                assertThat(language).isEqualTo(repositoryData.language)
                assertThat(starCount).isEqualTo(repositoryData.starCount)
                assertThat(forkCount).isEqualTo(repositoryData.forkCount)
            }
        }
    }

}