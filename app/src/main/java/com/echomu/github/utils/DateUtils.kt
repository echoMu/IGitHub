package com.echomu.github.utils

import java.time.format.DateTimeFormatter

/**
 * Created by echoMu.
 */
fun dateFormatForRepository(): DateTimeFormatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd")