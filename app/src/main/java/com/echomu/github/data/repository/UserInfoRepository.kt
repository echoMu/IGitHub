package com.echomu.github.data.repository

import com.echomu.github.data.local.user.UserLocalDataSource
import com.echomu.github.data.model.user.response.UserAccessTokenData
import com.echomu.github.data.model.user.response.UserInfoData
import com.echomu.github.data.remote.user.UserRemoteDataSource

/**
 * Created by echoMu.
 */
class UserInfoRepository(
        private val remoteDataSource: UserRemoteDataSource,
        private val localDataSource: UserLocalDataSource
) {

    fun isLogin(): Boolean =
            localDataSource.userId != -1

    fun cacheUsername(username: String) =
            localDataSource.cacheUsername(username)

    fun cachePassword(password: String) =
            localDataSource.cachePassword(password)

    suspend fun authorizations(): UserAccessTokenData =
            remoteDataSource.authorizations()

    suspend fun getUserInfo(): UserInfoData =
            remoteDataSource.fetchUserInfo()

    fun cacheUserId(userId: Int) =
            localDataSource.cacheUserId(userId)

    fun getName(): String =
            localDataSource.name

    fun cacheName(name: String) =
            localDataSource.cacheName(name)

    fun getAvatarUrl(): String =
            localDataSource.avatarUrl

    fun cacheAvatarUrl(avatarUrl: String) =
            localDataSource.cacheAvatarUrl(avatarUrl)

    fun logout() =
            localDataSource.clearUserInfoCache()

}