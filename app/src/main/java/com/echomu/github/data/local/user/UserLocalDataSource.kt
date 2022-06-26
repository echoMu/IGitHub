package com.echomu.github.data.local.user

import com.echomu.github.utils.int
import com.echomu.github.utils.string
import com.tencent.mmkv.MMKV

/**
 * Created by echoMu.
 */
class UserLocalDataSource(
        val mmkv: MMKV
) {

    var accessToken by mmkv.string("user_access_token", "")
    var userId by mmkv.int("user_id", -1)
    var username by mmkv.string("username", "")
    var password by mmkv.string("password", "")
    var name by mmkv.string("name", "")
    var avatarUrl by mmkv.string("avatar_url", "")

    fun clearUserInfoCache() =
            mmkv.removeValuesForKeys(arrayOf(
                    "user_access_token",
                    "user_id",
                    "username",
                    "password",
                    "name",
                    "avatar_url"
            ))

    fun cacheUserId(userId: Int) {
        this.userId = userId
    }

    fun cacheUsername(username: String) {
        this.username = username
    }

    fun cachePassword(password: String) {
        this.password = password
    }

    fun cacheName(name: String) {
        this.name = name
    }

    fun cacheAvatarUrl(avatarUrl: String) {
        this.avatarUrl = avatarUrl
    }

}