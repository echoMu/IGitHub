package com.echomu.github.utils

import com.google.gson.Gson
import com.echomu.github.data.model.user.response.UserInfoData
import com.echomu.github.data.userInfoData
import com.echomu.github.data.userInfoDataJson
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by echoMu.
 */
@RunWith(JUnit4::class)
class GsonExtTest {

    @Test
    fun fromJson_success() {
        assertEquals(userInfoData.id, Gson().fromJson<UserInfoData>(userInfoDataJson).id)
    }

    @Test
    fun fromJson_failure() {
        assertNotEquals("-1", Gson().fromJson<UserInfoData>(userInfoDataJson).id)
    }

}