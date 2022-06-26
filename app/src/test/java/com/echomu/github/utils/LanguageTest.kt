package com.echomu.github.utils

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by echoMu.
 */
@RunWith(JUnit4::class)
class LanguageTest {

    @Test
    fun getLanguage_success() {
        assertEquals(Language.KOTLIN, Language.of("Kotlin"))
    }

    @Test
    fun getLanguage_failure() {
        assertNotEquals(Language.JAVA, Language.of("Kotlin"))
    }

}