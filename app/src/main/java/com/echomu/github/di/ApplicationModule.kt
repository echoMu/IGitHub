package com.echomu.github.di

import com.echomu.github.AndroidGenericFrameworkConfiguration
import com.echomu.github.data.local.user.UserLocalDataSource
import com.echomu.github.data.remote.repository.RepositoryRemoteDataSource
import com.echomu.github.data.remote.user.UserRemoteDataSource
import com.echomu.github.data.repository.GitHubRepository
import com.echomu.github.data.repository.UserInfoRepository
import com.echomu.github.ui.main.activity.MainActivity
import com.echomu.github.ui.main.activity.SplashActivity
import com.echomu.github.ui.main.viewmodel.MainViewModel
import com.echomu.github.ui.main.viewmodel.SplashViewModel
import com.echomu.github.ui.repository.fragment.RepositoryFragment
import com.echomu.github.ui.repository.viewmodel.RepositoryViewModel
import com.echomu.github.ui.user.activity.PersonalCenterActivity
import com.echomu.github.ui.user.fragment.LoginFragment
import com.echomu.github.ui.user.viewmodel.LoginViewModel
import com.echomu.github.ui.user.viewmodel.PersonalCenterViewModel
import com.tencent.mmkv.MMKV
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by echoMu.
 */
val applicationModule = module {
    single {
        UserLocalDataSource(MMKV.mmkvWithID(
                AndroidGenericFrameworkConfiguration.MMKV_ID,
                MMKV.SINGLE_PROCESS_MODE,
                AndroidGenericFrameworkConfiguration.MMKV_CRYPT_KEY
        ))
    }

    single { UserRemoteDataSource(get()) }

    single { RepositoryRemoteDataSource(get()) }
}

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
                .connectTimeout(AndroidGenericFrameworkConfiguration.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(AndroidGenericFrameworkConfiguration.READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
                .client(get())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(String.format("%1\$s://%2\$s/", SCHEMA_HTTPS, AndroidGenericFrameworkConfiguration.HOST))
                .build()
    }
}

val repositoryModule = module {
    single { UserInfoRepository(get(), get()) }

    single { GitHubRepository(get()) }
}

val mainModule = module {
    scope<SplashActivity> {
        viewModel { SplashViewModel(get()) }
    }

    scope<MainActivity> {
        viewModel { MainViewModel(get()) }
    }
}

val userModule = module {
    scope<LoginFragment> {
        viewModel { LoginViewModel(get()) }
    }

    scope<PersonalCenterActivity> {
        viewModel { PersonalCenterViewModel(get()) }
    }
}

val githubRepositoryModule = module {
    scope<RepositoryFragment> {
        viewModel { RepositoryViewModel(get()) }
    }
}

val applicationModules = listOf(
        applicationModule,
        networkModule,
        repositoryModule,
        mainModule,
        userModule,
        githubRepositoryModule
)

private const val SCHEMA_HTTPS = "https"