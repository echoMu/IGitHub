package com.echomu.github.data.remote.user

import com.echomu.github.BuildConfig
import com.echomu.github.data.model.user.response.UserAccessTokenData
import com.echomu.github.data.model.user.response.UserInfoData
import retrofit2.Retrofit
import retrofit2.http.*

/**
 * Created by echoMu.
 */
class UserRemoteDataSource(
        retrofit: Retrofit
) {

    private val service: Service = retrofit.create(Service::class.java)

    suspend fun authorizations(): UserAccessTokenData =
            service.authorizations(BuildConfig.GITHUB_CLIENT_ID,"http://localhost/api/public/api/auth/github/callback")

    suspend fun fetchUserInfo(): UserInfoData =
            service.fetchUserInfo()

    interface Service {

        @GET("login/oauth/authorize")
        suspend fun authorizations(@Query("client_id") clientID:String, @Query("redirect_uri") redirectUri:String): UserAccessTokenData

        @GET("users/echoMu")
        suspend fun fetchUserInfo(): UserInfoData

    }

}